package xyz.ventosa.restester;

import com.fasterxml.jackson.databind.JsonNode;
import xyz.ventosa.restester.json.Json;
import xyz.ventosa.restester.json.pojo.TestPlanPOJO;
import xyz.ventosa.restester.runner.parsed.TestPlan;
import xyz.ventosa.restester.util.Util;

import java.io.IOException;

public class TestUtils {

    public static TestPlan getTestPlan() throws IOException {
        JsonNode node = Json.parse(Util.stringFromFile("src/test/resources/testPlan.json"));
        TestPlanPOJO planPOJO = Json.fromJson(node, TestPlanPOJO.class);
        return new TestPlan(planPOJO);
    }

}
