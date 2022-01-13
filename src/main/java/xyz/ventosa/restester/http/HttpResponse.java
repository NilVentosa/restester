package xyz.ventosa.restester.http;

import com.fasterxml.jackson.databind.JsonNode;
import xyz.ventosa.restester.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponse {
    private int code;
    private String responseMessage;
    private JsonNode response;

    public HttpResponse(HttpURLConnection con) throws IOException {
        this.setCode(con.getResponseCode());
        this.setResponse(Json.parse(extractResponseContent(con)));
        this.setResponseMessage(con.getResponseMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonNode getResponse() {
        return response;
    }

    public void setResponse(JsonNode response) {
        this.response = response;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private static String extractResponseContent(HttpURLConnection con) throws IOException {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        if (con.getResponseCode() > 299) {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        while((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();

        return responseContent.toString();
    }
}
