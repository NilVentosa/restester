package xyz.ventosa.restester.runner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import xyz.ventosa.restester.test.*;

import xyz.ventosa.restester.util.HttpUtil;
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
            result.setExecutionTime(result.getExecutionTime() + testCaseResult.getExecutionTime());
        }

        result.setExecuted(true);
        return result;
    }

    private TestCaseResult run(TestCase testCase) {
        LOGGER.log(Level.INFO, "Running test case: {0}", testCase.getName());
        long startTime = System.currentTimeMillis();

        HttpResponse response;
        try {
            response = testCase.getTestRequest().send();
        } catch (IOException e) {
            TestCaseResult result = new TestCaseResult(testCase.getName());
            result.setFailureReason(e.toString());
            return result;
        }

        TestCaseResult result = runAssertions(testCase, response);
        result.setExecutionTime((double) (System.currentTimeMillis() - startTime)/1000);
        return result;
    }

    private TestCaseResult runAssertions(TestCase testCase, HttpResponse response) {
        TestCaseResult result = new TestCaseResult(testCase.getName());
        ExpectedResponse expected = testCase.getTestResponse();

        if (expected.getCode() != -1) {
            if (expected.getCode() == response.getStatusLine().getStatusCode()) {
                result.setPassed(true);
            } else {
                result.setPassed(false);
                result.setFailureReason(String.format("Expected: %s, and found %s",
                        testCase.getTestResponse().getCode(),
                        response.getStatusLine().getStatusCode()));
                result.setExecuted(true);
                return result;
            }
        }
        return result;
    }
}
