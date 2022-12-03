package adventures;

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
}
