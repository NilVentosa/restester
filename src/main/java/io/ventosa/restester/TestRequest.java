package io.ventosa.restester;

import io.ventosa.restester.json.pojo.ParameterPOJO;
import io.ventosa.restester.json.pojo.RequestPOJO;

import java.util.HashMap;
import java.util.Map;

public class TestRequest {
    private String endpoint;
    private String url;
    private Map<String, String> requestParameters;

    public TestRequest(RequestPOJO requestPOJO) {
        this.setEndpoint(requestPOJO.getEndpoint());
        this.setUrl(requestPOJO.getUrl());
        if (requestPOJO.getParameters().length > 0) {
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
}
