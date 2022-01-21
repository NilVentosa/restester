package xyz.ventosa.restester.json.pojo;

public class RequestPOJO {
    private String endpoint;
    private String url;
    private ParameterPOJO[] parameters;
    private String method;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
