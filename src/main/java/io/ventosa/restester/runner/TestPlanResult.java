package io.ventosa.restester.runner;

import java.util.ArrayList;
import java.util.List;

public class TestPlanResult extends Result {

    private List<TestSuiteResult> testSuiteResults = new ArrayList<>();

    public List<TestSuiteResult> getTestSuiteResults() {
        return testSuiteResults;
    }

    public void setTestSuiteResults(List<TestSuiteResult> testSuiteResults) {
        this.testSuiteResults = testSuiteResults;
    }
}
