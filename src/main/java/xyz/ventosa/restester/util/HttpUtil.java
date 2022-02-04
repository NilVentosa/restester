package xyz.ventosa.restester.util;

import xyz.ventosa.restester.test.TestRequest;

public class HttpUtil {

    public static String generateUrlString(TestRequest testRequest) {
        StringBuilder url = new StringBuilder();

        url.append(testRequest.getUrl());
        if (url.charAt(url.length()-1) != '/') {
            url.append("/");
        }
        if (testRequest.getEndpoint().startsWith("/")) {
            url.append(testRequest.getEndpoint().substring(1));
        }
        if (testRequest.getRequestParameters() != null) {
            url.append("?");
            for (String key: testRequest.getRequestParameters().keySet()) {
                url.append(key).append("=").append(testRequest.getRequestParameters().get(key)).append("&");
            }
            url.deleteCharAt(url.length()-1);
        }

        return url.toString();
    }

}
