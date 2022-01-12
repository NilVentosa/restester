package xyz.ventosa.restester.util;

import xyz.ventosa.restester.http.Http;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Util {

    private static final Logger LOGGER = Logger.getLogger(Http.class.getSimpleName());

    private Util() { }

    public static String stringFromFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));

        }
    }

    public static String removeSpaces(String input) {
        return input.replace(" ", "_");
    }

    public static void saveXml(String fileName, String content) {
        saveFile(fileName + ".xml", content);
    }

    private static void saveFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            LOGGER.log(Level.INFO, "File: {0} saved.", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
