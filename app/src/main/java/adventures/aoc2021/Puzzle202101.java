package adventures.aoc2021;

import adventures.utils.Helpers;

import java.util.*;

class Puzzle202101 {

    public static void main(String[] args) {
        new Puzzle202101().part1();
        new Puzzle202101().part2();
    }
    public void part1() {
        List<String> data = Helpers.loadFile("202101.txt");
        int prev = Integer.parseInt(data.get(0));
        int maxValue = 0;
        int curr = 0;
        for (int i = 1; i < data.size(); i++) {
            String d = data.get(i);
            curr = Integer.parseInt(d);
            if (curr > prev) {
                maxValue++;
            }
            prev = curr;
        }
        System.out.println(maxValue);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("202101.txt");
        int prevSum = 0;
        int count = 0;
        int currSum = 0;
        for (int i = 0; i <= data.size() - 3; i++) {
            currSum = Integer.parseInt(data.get(i)) + Integer.parseInt(data.get(i + 1))
                    + Integer.parseInt(data.get(i + 2));
            if (prevSum > 0 && currSum > prevSum) {
                count++;
            }
            prevSum = currSum;
        }
        System.out.println(count);
    }

}
