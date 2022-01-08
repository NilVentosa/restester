package io.ventosa.restester.json.pojo;

public class TestPlanPOJO {
    private String name;
    private TestSuitePOJO[] suites;
    private String url;
    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestSuitePOJO[] getSuites() {
        return suites;
    }

    public void setSuites(TestSuitePOJO[] suites) {
        this.suites = suites;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
