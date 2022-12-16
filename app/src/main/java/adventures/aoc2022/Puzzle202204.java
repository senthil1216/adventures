package adventures.aoc2022;

import adventures.utils.Helpers;

import java.util.*;

class Puzzle202204 {
    public static void main(String[] args) {
        new Puzzle202204().part1();
        new Puzzle202204().part2();
    }
    public void part1() {
        List<String> data = Helpers.loadFile("202204.txt");
        int out = 0;
        for (String d : data) {
            String[] pairs = d.split(",");
            String[] first = pairs[0].split("-");
            String[] second = pairs[1].split("-");
            int first_1 = Integer.parseInt(first[0]);
            int first_2 = Integer.parseInt(first[1]);
            int second_1 = Integer.parseInt(second[0]);
            int second_2 = Integer.parseInt(second[1]);
            if (first_1 <= second_1 && first_2 >= second_2) {
                out++;
            } else if (second_1 <= first_1 && second_2 >= first_2) {
                out++;
            }
        }
        System.out.println(out);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("202204.txt");
        int out = 0;
        for (String d : data) {
            String[] pairs = d.split(",");
            String[] first = pairs[0].split("-");
            String[] second = pairs[1].split("-");
            int first_1 = Integer.parseInt(first[0]);
            int first_2 = Integer.parseInt(first[1]);
            int second_1 = Integer.parseInt(second[0]);
            int second_2 = Integer.parseInt(second[1]);
            if (first_1 > second_2 || first_2 < second_1) {
                continue;
            }
            out++;
        }
        System.out.println(out);
    }

}