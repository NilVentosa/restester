package io.ventosa.restester;

import io.ventosa.restester.json.pojo.TestPlanPOJO;

import java.util.ArrayList;
import java.util.List;

public class TestPlan {
    private String name;
    private List<TestSuite> testSuites;
    private String url = null;

    public TestPlan(TestPlanPOJO testPlanPOJO) {
        this.setName(testPlanPOJO.getName());
        this.setUrl(testPlanPOJO.getUrl());
        if (testPlanPOJO.getSuites().length > 0) {
            this.testSuites = new ArrayList<>();
            for (int i = 0; i < testPlanPOJO.getSuites().length; i++) {
                this.testSuites.add(new TestSuite(testPlanPOJO.getSuites()[i]));
                if (this.url != null && testPlanPOJO.getSuites()[i].getUrl() == null) {
                    this.testSuites.get(i).setUrl(this.url);
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

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
