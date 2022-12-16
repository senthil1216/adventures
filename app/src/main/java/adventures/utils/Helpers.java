package adventures.utils;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Helpers {
    public static List<String> loadFile(String name) {
        InputStream is = Helpers.class.getClassLoader().getResourceAsStream(name);
        List<String> data = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String loadFileAsString(String name) {
        InputStream is = Helpers.class.getClassLoader().getResourceAsStream(name);
        StringBuilder builder = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
