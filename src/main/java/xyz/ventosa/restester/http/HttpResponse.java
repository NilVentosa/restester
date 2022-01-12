package xyz.ventosa.restester.http;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpResponse {
    private int code;
    private String responseMessage;
    private JsonNode response;

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
}
