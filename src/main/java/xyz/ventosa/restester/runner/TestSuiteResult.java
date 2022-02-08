package xyz.ventosa.restester.runner;

import xyz.ventosa.restester.test.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteResult extends Result {

    private List<TestCaseResult> testCaseResults = new ArrayList<>();
    private TestSuite suite;

    public TestSuiteResult(TestSuite suite) {
        this.setName(suite.getName());
        this.setSuite(suite);
    }

    public List<TestCaseResult> getTestCaseResults() {
        return testCaseResults;
    }

    public TestSuite getSuite() {
        return suite;
    }

    public void setSuite(TestSuite suite) {
        this.suite = suite;
    }

    public int getAmountOfTestCasesByStatus(Status status) {
        int result = 0;

        for (TestCaseResult testCaseResult: this.testCaseResults) {
            if (testCaseResult.getStatus() == status) {
                result++;
            }
        }

        return result;
    }

    public void setStatus() {
        this.setStatus(Status.PASSED);
        for (TestCaseResult testCaseResult: this.testCaseResults) {
            if (testCaseResult.getStatus() == Status.FAILED) {
                this.setStatus(Status.FAILED);
            }
        }
    }
}
