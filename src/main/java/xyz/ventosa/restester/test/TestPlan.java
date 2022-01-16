package xyz.ventosa.restester.test;

import xyz.ventosa.restester.http.Http;
import xyz.ventosa.restester.json.pojo.TestPlanPOJO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TestPlan {
    private String name;
    private List<TestSuite> testSuites;
    private String url = null;
    private String endpoint;

    private static final Logger LOGGER = Logger.getLogger(Http.class.getSimpleName());

    public TestPlan(TestPlanPOJO testPlanPOJO) {
        this.setName(testPlanPOJO.getName());
        this.setUrl(testPlanPOJO.getUrl());
        this.setEndpoint(testPlanPOJO.getEndpoint());
        if (testPlanPOJO.getSuites().length > 0) {
            this.testSuites = new ArrayList<>();
            for (int i = 0; i < testPlanPOJO.getSuites().length; i++) {
                if (testPlanPOJO.getSuites()[i].getUrl() == null && this.url != null) {
                    testPlanPOJO.getSuites()[i].setUrl(this.url);
                }
                if (testPlanPOJO.getSuites()[i].getEndpoint() == null && this.endpoint != null) {
                    testPlanPOJO.getSuites()[i].setEndpoint(this.endpoint);
                }
                this.testSuites.add(new TestSuite(testPlanPOJO.getSuites()[i]));
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
