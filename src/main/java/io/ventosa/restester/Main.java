package io.ventosa.restester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.http.Http;
import io.ventosa.restester.json.pojo.TestPlanPOJO;
import io.ventosa.restester.json.Json;
import io.ventosa.restester.runner.Runner;
import io.ventosa.restester.runner.TestPlanResult;
import io.ventosa.restester.util.Util;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Http.class.getSimpleName());

    public static void main(String[] args) {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        try {
            JsonNode node = Json.parse(Util.stringFromFile(args[0]));
            TestPlanPOJO planPOJO = Json.fromJson(node, TestPlanPOJO.class);

            Runner runner = new Runner();
            TestPlanResult result = runner.run(new TestPlan(planPOJO));
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error processing JSON: {0}", e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding file: {0}", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: {0}", e.getMessage());
        }
    }
}
