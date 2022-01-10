package io.ventosa.restester.runner;

import io.ventosa.restester.TestCase;
import io.ventosa.restester.TestPlan;
import io.ventosa.restester.TestSuite;
import io.ventosa.restester.http.Http;
import io.ventosa.restester.http.HttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private TestPlan testPlan;
    private TestPlanResult testPlanResult;

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getSimpleName());

    public TestPlanResult run(TestPlan testPlan) {
        LOGGER.log(Level.INFO, "Running plan: {0}", testPlan.getName());
        this.testPlan = testPlan;
        this.testPlanResult = new TestPlanResult(testPlan);

        for (TestSuite suite: testPlan.getTestSuites()) {
            TestSuiteResult testSuiteResult = this.run(suite);
            this.testPlanResult.getTestSuiteResults().add(testSuiteResult);
        }

        this.testPlanResult.setExecuted(true);
        this.printResults();
        return this.testPlanResult;
    }

    private TestSuiteResult run(TestSuite suite) {
        LOGGER.log(Level.INFO, "Running suite: {0}", suite.getName());
        TestSuiteResult result = new TestSuiteResult(suite);

        for (TestCase testCase: suite.getTestCases()) {
            TestCaseResult testCaseResult = this.run(testCase);
            result.getTestCaseResults().add(testCaseResult);
            if (testCaseResult.isPassed()) {
                result.setPassed(result.getPassed() + 1);
            } else {
                result.setFailed(result.getFailed() + 1);
            }
            result.setRemaining(result.getRemaining() - 1);
        }

        result.setExecuted(true);
        return result;
    }

    private TestCaseResult run(TestCase testCase) {
        LOGGER.log(Level.INFO, "Running test case: {0}", testCase.getName());
        TestCaseResult result = new TestCaseResult(testCase.getName());

        try {
            HttpResponse response = Http.send(testCase.getTestRequest());
            if (testCase.getTestResponse().getCode() == response.getCode()) {
                result.setPassed(true);
            } else {
                result.setPassed(false);
                result.setFailureReason(String.format("Expected: %s, and found %s", testCase.getTestResponse().getCode(), response.getCode()));
            }
        } catch (IOException e) {
            result.setPassed(false);
            result.setFailureReason(String.format("Error trying to send a request: %s", e));
        } finally {
            result.setExecuted(true);
        }

        return result;
    }

    private void printResults() {
        LOGGER.log(Level.INFO, "\n\n");
        LOGGER.log(Level.INFO, "----------------------------------------------------");
        LOGGER.log(Level.INFO, "--- R E S U L T S");
        LOGGER.log(Level.INFO, "----------------------------------------------------");
        for (TestSuiteResult testSuiteResult: testPlanResult.getTestSuiteResults()) {
            LOGGER.log(Level.INFO, "--- SUITE: {0}", testSuiteResult.getName());
            LOGGER.log(Level.INFO, "------ Tests run: {0}, Tests passed: {1}, Tests failed: {2}", new Object[]{
                    testSuiteResult.getFailed()+testSuiteResult.getPassed(),
                    testSuiteResult.getPassed(),
                    testSuiteResult.getFailed()});
        }
        LOGGER.log(Level.INFO, "----------------------------------------------------\n\n");
    }

}
