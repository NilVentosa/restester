package xyz.ventosa.restester.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import xyz.ventosa.restester.json.pojo.ParameterPOJO;
import xyz.ventosa.restester.json.pojo.RequestPOJO;
import xyz.ventosa.restester.util.HttpUtil;

import java.io.IOException;
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

    public HttpResponse send() throws  IOException{
        HttpClient client = HttpClientBuilder.create().build();
        String url = HttpUtil.generateUrlString(this);
        HttpResponse response;
            switch(this.getMethod()) {
                case "POST":
                    HttpPost postRequest = new HttpPost(url);
                    response = client.execute(postRequest);
                    break;
                case "DELETE":
                    HttpDelete httpDelete = new HttpDelete(url);
                    response = client.execute(httpDelete);
                    break;
                default:
                    HttpGet getRequest = new HttpGet(url);
                    response = client.execute(getRequest);
            }
        return response;
    }

}
