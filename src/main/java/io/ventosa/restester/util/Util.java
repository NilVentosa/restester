package io.ventosa.restester.util;

import java.io.*;
import java.util.stream.Collectors;

public class Util {

    private Util() { }

    public static String stringFromFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));

        }
    }
}
