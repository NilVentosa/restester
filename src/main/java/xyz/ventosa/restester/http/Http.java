package xyz.ventosa.restester.http;

import xyz.ventosa.restester.test.TestRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Http {
    private static final Logger LOGGER = Logger.getLogger(Http.class.getSimpleName());

    private Http() {}

    public static HttpResponse send(TestRequest testRequest) throws IOException {
        LOGGER.log(Level.INFO, "Sending to: {0}", generateUrlString(testRequest));

        HttpURLConnection con = generateHttpConnection(testRequest);

        return new HttpResponse(con);
    }

    private static HttpURLConnection generateHttpConnection(TestRequest testRequest) throws IOException {
        URL url = new URL(generateUrlString(testRequest));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            LOGGER.info(e.getMessage());
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("user-agent", "restester 1.0.0.0-SNAPSHOT");

        return con;
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
