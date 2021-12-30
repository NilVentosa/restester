package io.ventosa.restester.json.pojo;

public class RequestPOJO {
    private String endpoint;

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

    private ParameterPOJO[] parameters;
}
