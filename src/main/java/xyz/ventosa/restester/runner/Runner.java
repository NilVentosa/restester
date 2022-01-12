package xyz.ventosa.restester.runner;

import xyz.ventosa.restester.TestCase;
import xyz.ventosa.restester.TestPlan;
import xyz.ventosa.restester.TestSuite;
import xyz.ventosa.restester.http.Http;
import xyz.ventosa.restester.http.HttpResponse;

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

}
