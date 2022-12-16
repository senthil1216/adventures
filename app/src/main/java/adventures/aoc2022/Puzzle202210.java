package adventures.aoc2022;

import adventures.utils.Helpers;

import java.util.*;
import java.util.stream.*;

class Puzzle202210 {

    public static void main(String[] args) {
        new Puzzle202210().part1();
        new Puzzle202210().part2();
    }
    private List<Integer> buildCycleValues() {
        String INPUT = Helpers.loadFileAsString("202210.txt");
        List<Integer> vals = new ArrayList<>();
        int x = 1;
        vals.add(x);
        for (String line : INPUT.lines().collect(Collectors.toList())) {
            if (line.equals("noop")) {
                vals.add(x);
            } else {
                vals.add(x);
                vals.add(x);
                x += Integer.parseInt(line.split(" ")[1]);
            }
        }
        return vals;
    }

    public void part1() {
        List<Integer> vals = buildCycleValues();
        int sum = 0;
        for (int i = 20; i <= 220; i += 40) {
            sum += i * vals.get(i);
        }
        System.out.println(sum);
    }


    public void part2() {
        List<Integer> vals = buildCycleValues();
        int cycle = 1;
        StringJoiner rowJoiner = new StringJoiner("\n");
        for (int i = 0; i < 6; ++i) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int j = 0; j < 40; ++j, ++cycle) {
                lineBuilder.append(Math.abs(vals.get(cycle) - j) <= 1 ? "#" : " ");
            }
            rowJoiner.add(lineBuilder.toString());
        }
        System.out.println(rowJoiner.toString());
    }
}
