package xyz.ventosa.restester.runner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void getExecutionTime() {
        TestCaseResult result = new TestCaseResult("name");
        result.setExecutionTime(1.77777d);

        assertEquals(1.778d, result.getExecutionTime());
    }
}