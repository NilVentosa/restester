package io.ventosa.restester.runner;

import io.ventosa.restester.TestPlan;
import io.ventosa.restester.util.Util;

import java.util.ArrayList;
import java.util.List;

public class TestPlanResult extends Result {

    private List<TestSuiteResult> testSuiteResults = new ArrayList<>();
    private TestPlan testPlan;

    public TestPlanResult(TestPlan testPlan) {
        this.setTestPlan(testPlan);
        this.setName(testPlan.getName());
    }

    public void generateReport() {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<testsuites>\n");

        for (TestSuiteResult testSuiteResult: this.getTestSuiteResults()) {
            stringBuilder.append(
                    String.format("\t<testsuite name=\"%s\" time=\"0.135\" tests=\"%s\" skipped=\"%s\" failures=\"%s\">%n",
                            testSuiteResult.getName(),
                            testSuiteResult.getTestCaseResults().size(),
                            testSuiteResult.getRemaining(),
                            testSuiteResult.getFailed()));

            for (TestCaseResult testCaseResult: testSuiteResult.getTestCaseResults()) {
                if (testCaseResult.isPassed()) {
                    stringBuilder.append(String.format("\t\t<testcase name=\"%s\" time=\"0.135\"/>%n", testCaseResult.getName()));
                } else {
                    stringBuilder.append(String.format("\t\t<testcase name=\"%s\" time=\"0.135\">%n", testCaseResult.getName()));
                    stringBuilder.append(String.format("\t\t\t<failure>%s</failure>%n", testCaseResult.getFailureReason()));
                    stringBuilder.append(String.format("\t\t</testcase>%n", testCaseResult.getName()));
                }
            }

            stringBuilder.append("\t</testsuite>\n");
        }
        stringBuilder.append("</testsuites>");



        Util.saveXml(Util.removeSpaces(testPlan.getName()), stringBuilder.toString());
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
