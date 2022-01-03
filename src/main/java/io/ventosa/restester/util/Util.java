package io.ventosa.restester.util;

import java.io.*;
import java.util.stream.Collectors;

public class Util {
    public static String stringFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(filePath);
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));

    }
}
