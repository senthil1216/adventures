package adventures;

import java.util.*;
import java.util.stream.*;

class Puzzle202107 {

    private int calcMinCost(int fuel, int pos) {
        return Math.abs(fuel - pos);
    }

    private int calcMinIncrementalCost(int fuel, int pos) {
        int currCost = Math.abs(fuel - pos);
        currCost = (currCost * (currCost + 1)) / 2;
        return currCost;
    }

    private void findMinCost(boolean isPart1) {
        String[] data = Helpers.loadFileAsString("202107.txt").split(",");
        List<Integer> nums = new ArrayList<>();
        for (String d : data) {
            nums.add(Integer.parseInt(d.trim()));
        }
        int minFuel = Integer.MAX_VALUE;
        int count = nums.size();
        for (int i = 0; i < count; i++) {
            int fuelCost = 0;
            for (Integer num : nums) {
                if (isPart1) {
                    fuelCost += calcMinCost(num, i);
                } else {
                    fuelCost += calcMinIncrementalCost(num, i);
                }
            }
            minFuel = Math.min(minFuel, fuelCost);
        }
        System.out.println(minFuel);
    }


    public void part1() {
        findMinCost(true);
    }

    public void part2() {
        findMinCost(false);
    }
}
