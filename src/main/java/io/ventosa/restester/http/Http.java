package io.ventosa.restester.http;

import io.ventosa.restester.TestRequest;
import io.ventosa.restester.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {

    private Http() {}

    public static HttpResponse send(TestRequest testRequest) throws IOException {
        URL url = new URL(generateUrlString(testRequest));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("user-agent", "restester 1.0.0.0-SNAPSHOT");

        int status = con.getResponseCode();
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        while((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();

        HttpResponse response = new HttpResponse();
        response.setCode(status);
        response.setResponse(Json.parse(responseContent.toString()));
        response.setResponseMessage(con.getResponseMessage());

        return response;
    }

    private static String generateUrlString(TestRequest testRequest) {
        StringBuilder url = new StringBuilder();

        url.append(testRequest.getUrl());
        if (url.charAt(url.length()-1) != '/') {
            url.append("/");
        }
        if (testRequest.getEndpoint().startsWith("/")) {
            url.append(testRequest.getEndpoint().substring(1));
        }
        if (!testRequest.getRequestParameters().isEmpty()) {
            url.append("?");
            for (String key: testRequest.getRequestParameters().keySet()) {
                url.append(key).append("=").append(testRequest.getRequestParameters().get(key)).append("&");
            }
            url.deleteCharAt(url.length()-1);
        }

        return url.toString();
    }
}
