package io.ventosa.restester.runner;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteResult extends Result {

    private List<TestCaseResult> testCaseResults = new ArrayList<>();

    public List<TestCaseResult> getTestCaseResults() {
        return testCaseResults;
    }

    public void setTestCaseResults(List<TestCaseResult> testCaseResults) {
        this.testCaseResults = testCaseResults;
    }
}
