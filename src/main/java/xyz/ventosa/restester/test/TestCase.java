package xyz.ventosa.restester.test;

import xyz.ventosa.restester.json.pojo.TestCasePOJO;

public class TestCase {
    private String name;
    private String description;
    private TestRequest testRequest;
    private ExpectedResponse expectedResponse;

    public TestCase(TestCasePOJO testCasePOJO) {
        this.setName(testCasePOJO.getName());
        this.setDescription(testCasePOJO.getDescription());
        this.setTestRequest(new TestRequest(testCasePOJO.getRequest()));
        this.setTestResponse(new ExpectedResponse(testCasePOJO.getResponse()));
    }

    public ExpectedResponse getTestResponse() {
        return expectedResponse;
    }

    public void setTestResponse(ExpectedResponse expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestRequest getTestRequest() {
        return testRequest;
    }

    public void setTestRequest(TestRequest testRequest) {
        this.testRequest = testRequest;
    }
}
