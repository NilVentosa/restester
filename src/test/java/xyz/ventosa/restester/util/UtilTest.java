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

    @Test
    void roundThree() {
        double expected = 1.778d;
        double actual = Util.round(1.777777d, 3);

        assertEquals(expected, actual);
    }

    @Test
    void roundZero() {
        double expected = 2d;
        double actual = Util.round(1.777777d, 0);

        assertEquals(expected, actual);
    }
}