package io.ventosa.restester.json.pojo;

public class TestCasePOJO {
    private String name;
    private String description;
    private RequestPOJO request;
    private ResponsePOJO response;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
