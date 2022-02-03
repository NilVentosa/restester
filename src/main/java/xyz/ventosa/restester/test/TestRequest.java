package xyz.ventosa.restester.test;

import xyz.ventosa.restester.json.pojo.ParameterPOJO;
import xyz.ventosa.restester.json.pojo.RequestPOJO;

import java.util.HashMap;
import java.util.Map;

public class TestRequest {
    private String endpoint;
    private String url;
    private Map<String, String> requestParameters;
    private String method;

    public TestRequest(RequestPOJO requestPOJO) {
        this.setEndpoint(requestPOJO.getEndpoint());
        this.setUrl(requestPOJO.getUrl());
        this.setMethod(requestPOJO.getMethod());
        if (requestPOJO.getParameters() != null && requestPOJO.getParameters().length > 0) {
            this.setRequestParameters(new HashMap<>());
            for (ParameterPOJO parameterPOJO: requestPOJO.getParameters()) {
                this.getRequestParameters().put(parameterPOJO.getKey(), parameterPOJO.getValue());
            }
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getMethod() {
        return method == null ? "GET" : method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
