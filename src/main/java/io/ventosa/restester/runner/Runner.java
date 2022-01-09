package io.ventosa.restester.runner;

import io.ventosa.restester.TestCase;
import io.ventosa.restester.TestPlan;
import io.ventosa.restester.TestSuite;
import io.ventosa.restester.http.Http;
import io.ventosa.restester.http.HttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Http.class.getSimpleName());

    public void run(TestPlan testPlan) {
        LOGGER.log(Level.INFO, "Running plan: {0}", testPlan.getName());

        for (TestSuite suite: testPlan.getTestSuites()) {
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

}
