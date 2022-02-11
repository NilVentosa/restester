package xyz.ventosa.restester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import xyz.ventosa.restester.json.pojo.TestPlanPOJO;
import xyz.ventosa.restester.json.Json;
import xyz.ventosa.restester.report.Report;
import xyz.ventosa.restester.runner.Runner;
import xyz.ventosa.restester.runner.result.TestPlanResult;
import xyz.ventosa.restester.runner.parsed.TestPlan;
import xyz.ventosa.restester.util.Util;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        try {
            JsonNode node = Json.parse(Util.stringFromFile("src/test/resources/jsonplaceholder.typicode.com.json"));
            TestPlanPOJO planPOJO = Json.fromJson(node, TestPlanPOJO.class);

            TestPlanResult testPlanResult = new Runner().run(new TestPlan(planPOJO));

            Report.generateReport(testPlanResult);
            Report.printReport(testPlanResult);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error processing JSON: {0}", e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding file: {0}", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: {0}", e.getMessage());
        }
    }
}
