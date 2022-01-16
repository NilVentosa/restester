package xyz.ventosa.restester.runner;

import xyz.ventosa.restester.test.TestPlan;

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
