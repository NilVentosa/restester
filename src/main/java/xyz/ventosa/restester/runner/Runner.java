package xyz.ventosa.restester.runner;

import org.apache.http.HttpResponse;
import xyz.ventosa.restester.runner.result.Status;
import xyz.ventosa.restester.runner.result.TestCaseResult;
import xyz.ventosa.restester.runner.result.TestPlanResult;
import xyz.ventosa.restester.runner.result.TestSuiteResult;
import xyz.ventosa.restester.runner.parsed.TestCase;
import xyz.ventosa.restester.runner.parsed.TestPlan;
import xyz.ventosa.restester.runner.parsed.TestSuite;

import xyz.ventosa.restester.util.Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getSimpleName());

    public TestPlanResult run(TestPlan testPlan) {
        LOGGER.log(Level.INFO, "Running plan: {0}", testPlan.getName());
        double startTime = System.currentTimeMillis();

        TestPlanResult result = new TestPlanResult(testPlan);

        for (TestSuite suite: testPlan.getTestSuites()) {
            TestSuiteResult testSuiteResult = this.run(suite);
            result.getTestSuiteResults().add(testSuiteResult);
        }

        result.setStatus();
        result.setExecutionTime(Util.millisecondsSince(startTime));

        return result;
    }

    private TestSuiteResult run(TestSuite suite) {
        LOGGER.log(Level.INFO, "Running suite: {0}", suite.getName());
        double startTime = System.currentTimeMillis();

        TestSuiteResult result = new TestSuiteResult(suite);

        for (TestCase testCase: suite.getTestCases()) {
            TestCaseResult testCaseResult = this.run(testCase);
            result.getTestCaseResults().add(testCaseResult);
            result.setExecutionTime(Util.round(result.getExecutionTime(), 3) + testCaseResult.getExecutionTime());
        }

        result.setStatus();
        result.setExecutionTime(Util.millisecondsSince(startTime));

        return result;
    }

    private TestCaseResult run(TestCase testCase) {

        LOGGER.log(Level.INFO, "Running test case: {0}", testCase.getName());
        double startTime = System.currentTimeMillis();

        TestCaseResult result = new TestCaseResult(testCase.getName());

        HttpResponse response;
        try {
            response = testCase.getTestRequest().send();
        } catch (IOException e) {
            result.setFailureReason(e.toString());
            result.setStatus(Status.ERROR);
            return result;
        }

        result = Asserter.allAssertions(testCase, response);

        result.setExecutionTime(Util.millisecondsSince(startTime));
        return result;
    }
}
