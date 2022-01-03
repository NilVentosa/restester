package io.ventosa.restester;

import io.ventosa.restester.json.pojo.TestCasePOJO;

public class TestCase {
    private String name;
    private String description;
    private TestRequest testRequest;

    public TestCase(TestCasePOJO testCasePOJO) {
        this.setName(testCasePOJO.getName());
        this.setDescription(testCasePOJO.getDescription());
        this.setTestRequest(new TestRequest(testCasePOJO.getRequest()));
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
