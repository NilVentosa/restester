package xyz.ventosa.restester.report;

import xyz.ventosa.restester.runner.Status;
import xyz.ventosa.restester.runner.TestCaseResult;
import xyz.ventosa.restester.runner.TestPlanResult;
import xyz.ventosa.restester.runner.TestSuiteResult;
import xyz.ventosa.restester.util.Util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    private static final Logger LOGGER = Logger.getLogger(Report.class.getSimpleName());
    private static final String SEPARATOR = "----------------------------------------------------------\n";

    private Report() {}

    public static void generateReport(TestPlanResult testPlanResult) {
        LOGGER.log(Level.INFO, "Generating xml report for {0}", testPlanResult.getName());
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<testsuites>\n");

        for (TestSuiteResult testSuiteResult: testPlanResult.getTestSuiteResults()) {
            stringBuilder.append(
                    String.format("\t<testsuite name=\"%s\" time=\"%s\" tests=\"%s\" skipped=\"%s\" failures=\"%s\">%n",
                            testSuiteResult.getName(),
                            testSuiteResult.getExecutionTime(),
                            testSuiteResult.getTestCaseResults().size(),
                            testSuiteResult.getAmountOfTestCasesByStatus(Status.SKIPPED),
                            testSuiteResult.getAmountOfTestCasesByStatus(Status.FAILED)));

            for (TestCaseResult testCaseResult: testSuiteResult.getTestCaseResults()) {
                if (testCaseResult.isPassed()) {
                    stringBuilder.append(String.format("\t\t<testcase name=\"%s\" time=\"%s\"/>%n",
                            testCaseResult.getName(),
                            testCaseResult.getExecutionTime()));
                } else {
                    stringBuilder.append(String.format("\t\t<testcase name=\"%s\" time=\"%s\">%n",
                            testCaseResult.getName(),
                            testCaseResult.getExecutionTime()));
                    stringBuilder.append(String.format("\t\t\t<failure>%s</failure>%n", testCaseResult.getFailureReason()));
                    stringBuilder.append(String.format("\t\t</testcase>%n"));
                }
            }
            stringBuilder.append("\t</testsuite>\n");
        }
        stringBuilder.append("</testsuites>");



        Util.saveXml(Util.removeSpaces(testPlanResult.getName()), stringBuilder.toString());
    }


    public static void printReport(TestPlanResult testPlanResult) {
        StringBuilder stringBuilder = new StringBuilder("\n\n");
        stringBuilder.append( "\n");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append( String.format("--- R E S U L T S: %s%n", testPlanResult.getName()));
        stringBuilder.append(SEPARATOR);
        for (TestSuiteResult testSuiteResult: testPlanResult.getTestSuiteResults()) {
            stringBuilder.append(String.format("--- SUITE: %s%n", testSuiteResult.getName()));
            stringBuilder.append(String.format("------ Tests run: %s, Tests passed: %s, Tests failed: %s%n",
                    testSuiteResult.getAmountOfTestCasesByStatus(Status.FAILED)+ testSuiteResult.getAmountOfTestCasesByStatus(Status.PASSED),
                    testSuiteResult.getAmountOfTestCasesByStatus(Status.PASSED),
                    testSuiteResult.getAmountOfTestCasesByStatus(Status.FAILED)));
            if (!testSuiteResult.isPassed()) {
                for (TestCaseResult testCaseResult: testSuiteResult.getTestCaseResults()) {
                    if (!testCaseResult.isPassed()) {
                        stringBuilder.append(String.format("\t\tTest \"%s\" failed due to: %s%n", testCaseResult.getName(), testCaseResult.getFailureReason()));
                    }
                }
            }
        }
        stringBuilder.append(SEPARATOR);
        String report = stringBuilder.toString();
        LOGGER.info(report);
    }
}
