package xyz.ventosa.restester.test;

import org.junit.jupiter.api.Test;
import xyz.ventosa.restester.TestUtils;
import xyz.ventosa.restester.runner.parsed.TestCase;
import xyz.ventosa.restester.runner.parsed.TestRequest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class TestRequestTest {

    private final String SUITE_ONE_TEST_ONE_URL = "https://testsuite.com";
    private final String SUITE_ONE_TEST_ONE_ENDPOINT = "/suite1";

    private final String SUITE_ONE_TEST_TWO_URL = "https://testsuite.com";
    private final String SUITE_ONE_TEST_TWO_ENDPOINT = "/request2";

    private final String SUITE_TWO_TEST_ONE_URL = "https://testplan.com";
    private final String SUITE_TWO_TEST_ONE_ENDPOINT = null;

    private final String SUITE_TWO_TEST_TWO_URL = "www.testcase.com";
    private final String SUITE_TWO_TEST_TWO_ENDPOINT = "/suite2test2request";

    private final String SUITE_TWO_TEST_TWO_KEY_ONE = "page";
    private final String SUITE_TWO_TEST_TWO_VALUE_ONE = "2";
    private final String SUITE_TWO_TEST_TWO_KEY_TWO = "per_page";
    private final String SUITE_TWO_TEST_TWO_VALUE_TWO = "10";

    @Test
    void url() throws IOException {
        List<TestCase> testCasesSuiteOne = TestUtils.getTestPlan().getTestSuites().get(0).getTestCases();
        List<TestCase> testCasesSuiteTwo = TestUtils.getTestPlan().getTestSuites().get(1).getTestCases();

        assertEquals(SUITE_ONE_TEST_ONE_URL, testCasesSuiteOne.get(0).getTestRequest().getUrl());
        assertEquals(SUITE_ONE_TEST_TWO_URL, testCasesSuiteOne.get(1).getTestRequest().getUrl());
        assertEquals(SUITE_TWO_TEST_ONE_URL, testCasesSuiteTwo.get(0).getTestRequest().getUrl());
        assertEquals(SUITE_TWO_TEST_TWO_URL, testCasesSuiteTwo.get(1).getTestRequest().getUrl());
    }

    @Test
    void endpoint() throws IOException {
        List<TestCase> testCasesSuiteOne = TestUtils.getTestPlan().getTestSuites().get(0).getTestCases();
        List<TestCase> testCasesSuiteTwo = TestUtils.getTestPlan().getTestSuites().get(1).getTestCases();

        assertEquals(SUITE_ONE_TEST_ONE_ENDPOINT, testCasesSuiteOne.get(0).getTestRequest().getEndpoint());
        assertEquals(SUITE_ONE_TEST_TWO_ENDPOINT, testCasesSuiteOne.get(1).getTestRequest().getEndpoint());
        assertEquals(SUITE_TWO_TEST_ONE_ENDPOINT, testCasesSuiteTwo.get(0).getTestRequest().getEndpoint());
        assertEquals(SUITE_TWO_TEST_TWO_ENDPOINT, testCasesSuiteTwo.get(1).getTestRequest().getEndpoint());
    }

    @Test
    void parameters() throws IOException {
        TestRequest request = TestUtils.getTestPlan().getTestSuites().get(1).getTestCases().get(1).getTestRequest();

        assertEquals(SUITE_TWO_TEST_TWO_VALUE_ONE, request.getRequestParameters().get(SUITE_TWO_TEST_TWO_KEY_ONE));
        assertEquals(SUITE_TWO_TEST_TWO_VALUE_TWO, request.getRequestParameters().get(SUITE_TWO_TEST_TWO_KEY_TWO));
    }

}
