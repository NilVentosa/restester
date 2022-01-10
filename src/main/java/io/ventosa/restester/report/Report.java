package io.ventosa.restester.report;

import io.ventosa.restester.runner.TestCaseResult;
import io.ventosa.restester.runner.TestPlanResult;
import io.ventosa.restester.runner.TestSuiteResult;
import io.ventosa.restester.util.Util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    private static final Logger LOGGER = Logger.getLogger(Report.class.getSimpleName());

    private Report() {}

    public static void generateReport(TestPlanResult testPlanResult) {
        LOGGER.log(Level.INFO, "Generating xml report for {0}", testPlanResult.getName());
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<testsuites>\n");

        for (TestSuiteResult testSuiteResult: testPlanResult.getTestSuiteResults()) {
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
                    stringBuilder.append(String.format("\t\t</testcase>%n"));
                }
            }
            stringBuilder.append("\t</testsuite>\n");
        }
        stringBuilder.append("</testsuites>");



        Util.saveXml(Util.removeSpaces(testPlanResult.getName()), stringBuilder.toString());
    }
}
