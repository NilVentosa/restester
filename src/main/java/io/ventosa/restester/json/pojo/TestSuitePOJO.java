package io.ventosa.restester.json.pojo;

public class TestSuitePOJO {
    private String name;
    private String description;
    private String url;
    private TestCasePOJO[] tests;

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

    public TestCasePOJO[] getTests() {
        return tests;
    }

    public void setTests(TestCasePOJO[] tests) {
        this.tests = tests;
    }

}
