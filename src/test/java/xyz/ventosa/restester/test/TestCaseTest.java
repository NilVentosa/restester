package xyz.ventosa.restester.test;

import org.junit.jupiter.api.Test;
import xyz.ventosa.restester.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class TestCaseTest {

    private final String SUITE_ONE_TEST_ONE_NAME = "Suite1Test1";
    private final String SUITE_ONE_TEST_ONE_DESCRIPTION = "Description test suite 1 test case 1";
    private final int SUITE_ONE_TEST_ONE_RESPONSE_CODE = 200;

    private final String SUITE_ONE_TEST_TWO_NAME = "Suite1Test2";
    private final String SUITE_ONE_TEST_TWO_DESCRIPTION = null;
    private final int SUITE_ONE_TEST_TWO_RESPONSE_CODE = 404;

    private final String SUITE_TWO_TEST_ONE_NAME = "Suite2Test1";
    private final String SUITE_TWO_TEST_ONE_DESCRIPTION = null;
    private final int SUITE_TWO_TEST_ONE_RESPONSE_CODE = 200;

    private final String SUITE_TWO_TEST_TWO_NAME = "Suite2Test2";
    private final String SUITE_TWO_TEST_TWO_DESCRIPTION = "Description test suite 2 test case 2";
    private final int SUITE_TWO_TEST_TWO_RESPONSE_CODE = 500;

    @Test
    void name() throws IOException {
        List<TestSuite> testSuites = TestUtils.getTestPlan().getTestSuites();

        assertEquals(SUITE_ONE_TEST_ONE_NAME, testSuites.get(0).getTestCases().get(0).getName());
        assertEquals(SUITE_ONE_TEST_TWO_NAME, testSuites.get(0).getTestCases().get(1).getName());
        assertEquals(SUITE_TWO_TEST_ONE_NAME, testSuites.get(1).getTestCases().get(0).getName());
        assertEquals(SUITE_TWO_TEST_TWO_NAME, testSuites.get(1).getTestCases().get(1).getName());
    }

    @Test
    void description() throws IOException {
        List<TestSuite> testSuites = TestUtils.getTestPlan().getTestSuites();

        assertEquals(SUITE_ONE_TEST_ONE_DESCRIPTION, testSuites.get(0).getTestCases().get(0).getDescription());
        assertEquals(SUITE_ONE_TEST_TWO_DESCRIPTION, testSuites.get(0).getTestCases().get(1).getDescription());
        assertEquals(SUITE_TWO_TEST_ONE_DESCRIPTION, testSuites.get(1).getTestCases().get(0).getDescription());
        assertEquals(SUITE_TWO_TEST_TWO_DESCRIPTION, testSuites.get(1).getTestCases().get(1).getDescription());
    }

    @Test
    void responseCode() throws IOException {
        List<TestSuite> testSuites = TestUtils.getTestPlan().getTestSuites();

        assertEquals(SUITE_ONE_TEST_ONE_RESPONSE_CODE, testSuites.get(0).getTestCases().get(0).getTestResponse().getCode());
        assertEquals(SUITE_ONE_TEST_TWO_RESPONSE_CODE, testSuites.get(0).getTestCases().get(1).getTestResponse().getCode());
        assertEquals(SUITE_TWO_TEST_ONE_RESPONSE_CODE, testSuites.get(1).getTestCases().get(0).getTestResponse().getCode());
        assertEquals(SUITE_TWO_TEST_TWO_RESPONSE_CODE, testSuites.get(1).getTestCases().get(1).getTestResponse().getCode());
    }

}
