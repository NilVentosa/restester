package io.ventosa.restester.json.pojo;

public class TestCasePOJO {
    private String name;
    private RequestPOJO request;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestPOJO getRequest() {
        return request;
    }

    public void setRequest(RequestPOJO request) {
        this.request = request;
    }

    public ResponsePOJO getResponse() {
        return response;
    }

    public void setResponse(ResponsePOJO response) {
        this.response = response;
    }

    private ResponsePOJO response;
}
