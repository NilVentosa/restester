package xyz.ventosa.restester.runner;

import org.apache.http.HttpResponse;
import xyz.ventosa.restester.test.*;

import xyz.ventosa.restester.util.Util;;

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

        this.testPlanResult.setStatus();
        return this.testPlanResult;
    }

    private TestSuiteResult run(TestSuite suite) {
        LOGGER.log(Level.INFO, "Running suite: {0}", suite.getName());
        TestSuiteResult result = new TestSuiteResult(suite);

        for (TestCase testCase: suite.getTestCases()) {
            TestCaseResult testCaseResult = this.run(testCase);
            result.getTestCaseResults().add(testCaseResult);
            result.setExecutionTime(Util.round(result.getExecutionTime(), 3) + testCaseResult.getExecutionTime());
        }

        result.setStatus();
        return result;
    }

    private TestCaseResult run(TestCase testCase) {
        LOGGER.log(Level.INFO, "Running test case: {0}", testCase.getName());
        double startTime = System.currentTimeMillis();

        HttpResponse response;
        try {
            response = testCase.getTestRequest().send();
        } catch (IOException e) {
            TestCaseResult result = new TestCaseResult(testCase.getName());
            result.setFailureReason(e.toString());
            return result;
        }

        TestCaseResult result = runAssertions(testCase, response);
        result.setExecutionTime(Util.millisecondsSince(startTime));
        return result;
    }

    private TestCaseResult runAssertions(TestCase testCase, HttpResponse response) {
        TestCaseResult result = new TestCaseResult(testCase.getName());
        ExpectedResponse expected = testCase.getTestResponse();

        if (expected.getCode() == -1) {
            return result;
        }

        if (expected.getCode() == response.getStatusLine().getStatusCode()) {
            result.setStatus(Status.PASSED);
            return result;
        }

        result.setFailed(String.format("Expected: %s, and found %s",
                testCase.getTestResponse().getCode(),
                response.getStatusLine().getStatusCode()));
        return result;
    }
}
