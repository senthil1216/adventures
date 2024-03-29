package adventures.aoc2022;

import adventures.utils.Helpers;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Puzzle202201 {
    public static void main(String[] args) {
        new Puzzle202201().part1();
        new Puzzle202201().part2();
    }

    public void part1() {
        List<String> data = Helpers.loadFile("202201.txt");
        int cal = 0;
        int maxCal = -1;
        for (String val : data) {
            if (Strings.isNullOrEmpty(val) || !val.matches("\\d+")) {
                maxCal = Math.max(maxCal, cal);
                cal = 0;
                continue;
            }
            cal += Integer.parseInt(val);
        }
        maxCal = Math.max(maxCal, cal);
        System.out.println(maxCal);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("202201.txt");
        int cal = 0;
        List<Integer> calories = new ArrayList<>();
        for (String val : data) {
            if (Strings.isNullOrEmpty(val) || !val.matches("\\d+")) {
                calories.add(cal);
                cal = 0;
                continue;
            }
            cal += Integer.parseInt(val);
        }
        Collections.sort(calories);
        int last = calories.size() - 1;
        System.out.println(calories.get(last) + calories.get(last - 1) + calories.get(last - 2));
    }

}
