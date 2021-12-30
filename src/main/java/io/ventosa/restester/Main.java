package io.ventosa.restester;

import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.json.pojo.TestPlanPOJO;
import io.ventosa.restester.util.HttpRequest;
import io.ventosa.restester.json.Json;

import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        try {
            InputStream inputStream = new FileInputStream(args[0]);

            String text = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));

            JsonNode node = Json.parse(text);
            TestPlanPOJO plan = Json.fromJson(node, TestPlanPOJO.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
