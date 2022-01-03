package io.ventosa.restester;

import io.ventosa.restester.json.pojo.TestSuitePOJO;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    private String name;
    private String description;
    private String url;
    private List<TestCase> testCases;

    public TestSuite(TestSuitePOJO testSuitePOJO) {
        this.setName(testSuitePOJO.getName());
        this.setDescription(testSuitePOJO.getDescription());
        this.setUrl(testSuitePOJO.getUrl());
        if (testSuitePOJO.getTests().length > 0) {
            this.testCases = new ArrayList<>();
            for (int i = 0; i < testSuitePOJO.getTests().length; i++) {
                this.testCases.add(new TestCase(testSuitePOJO.getTests()[i]));
                if (this.url != null && testSuitePOJO.getTests()[i].getRequest().getUrl() == null) {
                    this.testCases.get(i).getTestRequest().setUrl(this.url);
                }
            }
        }
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
