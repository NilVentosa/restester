package io.ventosa.restester.json.pojo;

public class RequestPOJO {
    private String endpoint;
    private String url;
    private ParameterPOJO[] parameters;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public ParameterPOJO[] getParameters() {
        return parameters;
    }

    public void setParameters(ParameterPOJO[] parameters) {
        this.parameters = parameters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
