package io.ventosa.restester.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.ventosa.restester.json.Json;
import io.ventosa.restester.json.pojo.SimpleJsonSourceTestPOJO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleJsonSourceTitle = "the title";
    private String simpleJsonSource = "{ \"title\": \"" + simpleJsonSourceTitle + "\", \"author\": \"Nil\"}";


    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleJsonSource);

        assertEquals(simpleJsonSourceTitle, node.get("title").asText());
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleJsonSource);
        SimpleJsonSourceTestPOJO pojo = Json.fromJson(node, SimpleJsonSourceTestPOJO.class);

        assertEquals(simpleJsonSourceTitle, pojo.getTitle());
    }

    @Test
    void toJsonNode() throws JsonProcessingException {
        SimpleJsonSourceTestPOJO pojo = new SimpleJsonSourceTestPOJO();
        pojo.setTitle(simpleJsonSourceTitle);

        JsonNode node = Json.toJsonNode(pojo);

        assertEquals(simpleJsonSourceTitle, node.get("title").asText());
    }

    @Test
    void stringify() throws JsonProcessingException {
        SimpleJsonSourceTestPOJO pojo = new SimpleJsonSourceTestPOJO();
        pojo.setTitle(simpleJsonSourceTitle);
        JsonNode node = Json.toJsonNode(pojo);

        assertEquals("{\"title\":\"the title\"}", Json.stringify(node));
    }

    @Test
    void prettyStringify() throws JsonProcessingException {
        SimpleJsonSourceTestPOJO pojo = new SimpleJsonSourceTestPOJO();
        pojo.setTitle(simpleJsonSourceTitle);
        JsonNode node = Json.toJsonNode(pojo);

        assertEquals("{\n  \"title\" : \"the title\"\n}", Json.prettyStringify(node));
    }
}