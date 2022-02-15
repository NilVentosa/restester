package xyz.ventosa.restester.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import xyz.ventosa.restester.json.pojo.SimpleJsonSourceTestPOJO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private final String simpleJsonSourceTitle = "the title";
    private final String simpleJsonSource = "{ \"title\": \"" + simpleJsonSourceTitle + "\", \"author\": \"Nil\"}";
    private final String nestedFieldWithArrays = "[\n" +
            "  {\n" +
            "    \"name\": \"et fugit eligendi deleniti quidem qui sint nihil autem\",\n" +
            "    \"thing\": [\n" +
            "      {\n" +
            "        \"key\": \"value\"\n" +
            "      }, {\n" +
            "        \"key2\": \"value2\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";


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

    @Test
    void extractFieldValueArray() throws JsonProcessingException{
        String expected = "value2";
        String actual = Json.extractFieldValue(Json.parse(nestedFieldWithArrays), "[0].thing[1].key2");

        assertEquals(expected, actual);
    }

    @Test
    void extractFieldValue() throws JsonProcessingException{
        String expected = "et fugit eligendi deleniti quidem qui sint nihil autem";
        String actual = Json.extractFieldValue(Json.parse(nestedFieldWithArrays), "[0].name");

        assertEquals(expected, actual);
    }
}