package io.ventosa.restester.json.pojo;

public class TestPlanPOJO {
    private String name;
    private TestSuitePOJO[] suites;

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
}
