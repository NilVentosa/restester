package xyz.ventosa.restester.runner;

import xyz.ventosa.restester.test.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteResult extends Result {

    private List<TestCaseResult> testCaseResults = new ArrayList<>();
    private TestSuite suite;
    private int failed = 0;
    private int passed = 0;
    private int remaining = 0;

    public TestSuiteResult(TestSuite suite) {
        this.setName(suite.getName());
        this.setSuite(suite);
        this.setRemaining(suite.getTestCases().size());
    }

    public List<TestCaseResult> getTestCaseResults() {
        return testCaseResults;
    }

    public void setTestCaseResults(List<TestCaseResult> testCaseResults) {
        this.testCaseResults = testCaseResults;
    }

    public TestSuite getSuite() {
        return suite;
    }

    public void setSuite(TestSuite suite) {
        this.suite = suite;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
