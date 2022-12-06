package adventures;

import java.util.*;
import java.util.stream.*;
import com.google.common.primitives.Chars;

class Puzzle202206 {
    public void part1() {
        String data = Helpers.loadFileAsString("202206.txt");
        int i = 0;
        while (true) {
            List<Character> chars = Chars.asList(data.substring(i, i + 4).toCharArray());
            if (chars.stream().distinct().collect(Collectors.toList()).size() == 4) {
                break;
            }
            i++;
        }
        System.out.println(i + 4);
    }

    public void part2() {
        String data = Helpers.loadFileAsString("202206.txt");
        int i = 0;
        while (true) {
            List<Character> chars = Chars.asList(data.substring(i, i + 14).toCharArray());
            if (chars.stream().distinct().collect(Collectors.toList()).size() == 14) {
                break;
            }
            i++;
        }
        System.out.println(i + 14);
    }
}
