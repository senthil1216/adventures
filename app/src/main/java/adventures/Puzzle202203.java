
package adventures;

import java.util.*;

class Puzzle202203 {
    public void part1() {
        List<String> data = Helpers.loadFile("20220301.txt");
        int out = 0;
        for (String d : data) {
            Set<Character> common = new HashSet<>();
            String first = d.substring(0, d.length() / 2);
            String second = d.substring(d.length() / 2);
            for (Character c : first.toCharArray()) {
                if (second.indexOf(c) >= 0 && !common.contains(c)) {
                    out += Character.isUpperCase(c) ? (int) c - 38 : (int) c - 96;
                    common.add(c);
                }
            }
        }
        System.out.println(out);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("20220302.txt");
        int out = 0;
        for (int i = 0; i < data.size(); i += 3) {
            Set<Character> common = new HashSet<>();
            String first = data.get(i);
            String second = data.get(i + 1);
            String third = data.get(i + 2);
            for (Character c : first.toCharArray()) {
                if (second.indexOf(c) >= 0 && third.indexOf(c) >= 0 && !common.contains(c)) {
                    out += Character.isUpperCase(c) ? (int) c - 38 : (int) c - 96;
                    common.add(c);
                }
            }
        }
        System.out.println(out);
    }

}