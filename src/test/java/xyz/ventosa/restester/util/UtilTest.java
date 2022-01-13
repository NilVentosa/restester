package xyz.ventosa.restester.util;

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

    @Test
    void removeSpaces() {
        String expected = "hello_bye";
        String actual = Util.removeSpaces("hello bye");
        assertEquals(expected, actual);
    }
}