package adventures;

import java.util.*;
import java.util.stream.*;
import com.google.common.primitives.Chars;

class Puzzle202206 {
    public void part1() {
        String data = Helpers.loadFileAsString("202206.txt");
        boolean found = false;
        int i = 0;
        while (!found) {
            List<Character> chars = Chars.asList(data.substring(i, i + 4).toCharArray());
            found = chars.stream().distinct().collect(Collectors.toList()).size() == 4;
            i++;
        }
        System.out.println(i + 3);
    }

    public void part2() {
        String data = Helpers.loadFileAsString("202206.txt");
        boolean found = false;
        int i = 0;
        while (!found) {
            List<Character> chars = Chars.asList(data.substring(i, i + 14).toCharArray());
            found = chars.stream().distinct().collect(Collectors.toList()).size() == 14;
            i++;
        }
        System.out.println(i + 13);
    }
}
