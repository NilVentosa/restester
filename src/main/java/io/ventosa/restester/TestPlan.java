package io.ventosa.restester;

import io.ventosa.restester.http.Http;
import io.ventosa.restester.http.HttpResponse;
import io.ventosa.restester.json.pojo.TestPlanPOJO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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

    public void run() {
        LOGGER.log(Level.INFO, "Running plan: {0}", getName());

        for (TestSuite suite: getTestSuites()) {
            LOGGER.log(Level.INFO, "Running suite: {0}", suite.getName());

            for (TestCase testCase: suite.getTestCases()) {
                LOGGER.log(Level.INFO, "Running test case: {0}", testCase.getName());

                try {
                    HttpResponse response = Http.send(testCase.getTestRequest());
                    LOGGER.log(Level.INFO, "Expected: {0}, and found {1}",
                            new Object[]{ testCase.getTestResponse().getCode(), response.getCode() });
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error trying to send a request: {0}", e.toString());
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
