package io.ventosa.restester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.json.pojo.TestPlanPOJO;
import io.ventosa.restester.json.Json;
import io.ventosa.restester.util.Util;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {
            JsonNode node = Json.parse(Util.stringFromFile(args[0]));
            TestPlanPOJO planPOJO = Json.fromJson(node, TestPlanPOJO.class);

            TestPlan testPlan = new TestPlan(planPOJO);

        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
