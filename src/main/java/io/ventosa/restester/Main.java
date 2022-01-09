package io.ventosa.restester;

import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.json.pojo.TestPlanPOJO;
import io.ventosa.restester.json.Json;
import io.ventosa.restester.util.Util;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        try {
            JsonNode node = Json.parse(Util.stringFromFile(args[0]));
            TestPlanPOJO planPOJO = Json.fromJson(node, TestPlanPOJO.class);

            TestPlan testPlan = new TestPlan(planPOJO);

            testPlan.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
