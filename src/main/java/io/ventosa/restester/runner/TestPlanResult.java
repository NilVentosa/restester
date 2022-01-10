package io.ventosa.restester.runner;

import io.ventosa.restester.TestPlan;

import java.util.ArrayList;
import java.util.List;

public class TestPlanResult extends Result {

    private List<TestSuiteResult> testSuiteResults = new ArrayList<>();
    private TestPlan testPlan;

    public TestPlanResult(TestPlan testPlan) {
        this.setTestPlan(testPlan);
        this.setName(testPlan.getName());
    }

    public List<TestSuiteResult> getTestSuiteResults() {
        return testSuiteResults;
    }

    public void setTestSuiteResults(List<TestSuiteResult> testSuiteResults) {
        this.testSuiteResults = testSuiteResults;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
