package io.ventosa.restester;

import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.http.Http;
import io.ventosa.restester.http.HttpResponse;
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

            HttpResponse response = Http.send(testPlan.getTestSuites().get(0).getTestCases().get(0).getTestRequest());
            System.out.println(response.getResponseMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
