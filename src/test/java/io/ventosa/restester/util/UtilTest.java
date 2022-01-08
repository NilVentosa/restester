package io.ventosa.restester.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void stringFromFile() throws IOException {
        String expected = "{\"hello\":\"bye\"}";
        String actual = Util.stringFromFile("src/test/resources/tiny.json");

        assertEquals(expected, actual);
    }
}