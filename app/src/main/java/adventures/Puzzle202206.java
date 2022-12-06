
package adventures;

import java.util.*;
import java.util.stream.*;
import com.google.common.primitives.Chars;

class Puzzle202206 {
    public void part1() {
        String data = Helpers.loadFileAsString("202206.txt");
        boolean found = false;
        for (int i = 0; i < data.length() - 4; i++) {
            if (found) {
                continue;
            }
            List<Character> chars = Chars.asList(data.substring(i, i + 4).toCharArray());
            List<Character> distinct = chars.stream().distinct().collect(Collectors.toList());
            if (chars.size() == distinct.size()) {
                System.out.println(i + 4);
                found = true;
            }
        }
    }

    public void part2() {
        String data = Helpers.loadFileAsString("202206.txt");
        boolean found = false;
        for (int i = 0; i < data.length() - 14; i++) {
            if (found) {
                continue;
            }
            List<Character> chars = Chars.asList(data.substring(i, i +
                    14).toCharArray());
            List<Character> distinct = chars.stream().distinct().collect(Collectors.toList());
            if (chars.size() == distinct.size()) {
                System.out.println(i + 14);
                found = true;
            }
        }
    }
}