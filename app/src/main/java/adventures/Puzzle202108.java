package adventures;

import java.util.*;
import java.util.stream.*;

class Puzzle202108 {

    public void part1() {
        String[] data = Helpers.loadFileAsString("202108.txt").split("\n");
        int count = 0;
        for (String d : data) {
            int pipeIdx = d.indexOf("|");
            String signals = d.substring(pipeIdx + 1);
            String[] output = signals.trim().split(" ");
            for (String curr : output) {
                curr = curr.trim();
                if (curr.length() == 2 || curr.length() == 4 || curr.length() == 3
                        || curr.length() == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    // .. code the set based on length of string
    private void decodeStringByLength(String curr, Map<Integer, String> decodedNum,
            Set<String> codedSet) {
        if (curr.length() == 2) {
            decodedNum.put(1, curr);
            codedSet.add(curr);
        }
        if (curr.length() == 4) {
            decodedNum.put(4, curr);
            codedSet.add(curr);
        }
        if (curr.length() == 3) {
            decodedNum.put(7, curr);
            codedSet.add(curr);
        }
        if (curr.length() == 7) {
            decodedNum.put(8, curr);
            codedSet.add(curr);
        }
    }

    public void part2() {
        String[] data = Helpers.loadFileAsString("202108.txt").split("\n");
        int count = 0;
        for (String d : data) {
            int pipeIdx = d.indexOf("|");
            String signals = d.substring(0, pipeIdx);
            String[] output = signals.trim().split(" ");
            Set<String> codedSet = new HashSet<>();
            Map<Integer, String> decodedNum = new HashMap<>();
            for (int i = 0; i < output.length; i++) {
                String currStr = output[i].trim();
                decodeStringByLength(currStr, decodedNum, codedSet);
            }
        }
        System.out.println(count);
    }
}
