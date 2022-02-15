package xyz.ventosa.restester.runner;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import xyz.ventosa.restester.json.Json;
import xyz.ventosa.restester.runner.parsed.ExpectedResponse;
import xyz.ventosa.restester.runner.parsed.TestCase;
import xyz.ventosa.restester.runner.result.TestCaseResult;
import xyz.ventosa.restester.util.Util;

import java.io.IOException;

public class Asserter {

    public static TestCaseResult allAssertions(TestCase testCase, HttpResponse response) {
        TestCaseResult result;

        result = statusCodeAssertion(testCase, response);
        if (result.isFailed()) {
            return result;
        }

        return fieldsAssertion(testCase, response);
    }

    private static TestCaseResult fieldsAssertion(TestCase testCase, HttpResponse response) {
        TestCaseResult result = new TestCaseResult(testCase.getName());

        if (testCase.getTestResponse().getFields() == null || testCase.getTestResponse().getFields().size() < 1) {
            return result.setPassed();
        }

        JsonNode jsonNode;
        try {
            jsonNode = Json.parse(Util.stringFromInputStream(response.getEntity().getContent()));
        } catch (IOException e) {
            return result.setError(e.getMessage());
        }

        for (String fieldKey: testCase.getTestResponse().getFields().keySet()) {
            String expectedValue = testCase.getTestResponse().getFields().get(fieldKey);

            String actualValue;
            try {
                actualValue = Json.extractFieldValue(jsonNode, fieldKey);
            } catch (NullPointerException e) {
                return result.setError(e.getMessage());
            }

            if (!expectedValue.equals(actualValue)) {
                return result.setFailed(expectedValue, actualValue);
            }
        }

        return result.setPassed();
    }

    private static TestCaseResult statusCodeAssertion(TestCase testCase, HttpResponse response) {
        TestCaseResult result = new TestCaseResult(testCase.getName());
        ExpectedResponse expected = testCase.getTestResponse();

        if (expected.getCode() == -1) {
            return result;
        }

        if (expected.getCode() == response.getStatusLine().getStatusCode()) {
            return result.setPassed();
        }

        return result.setFailed(String.valueOf(testCase.getTestResponse().getCode()),
                        String.valueOf(response.getStatusLine().getStatusCode()));
    }
}
