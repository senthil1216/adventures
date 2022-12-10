package adventures;

import java.util.*;
import java.util.stream.Collectors;

class Puzzle202103 {

    public static void main(String[] args) {
        new Puzzle202103().part1();
        new Puzzle202103().part2();
    }
    class Pair {
        public int CountOnes;
        public int CountZeroes;
    }

    public void part1() {
        List<String> data = Helpers.loadFile("202103.txt");
        Pair[] pairs = new Pair[data.get(0).length()];
        for (String d : data) {
            char[] binStr = d.toCharArray();
            for (int i = 0; i < binStr.length; i++) {
                if (pairs[i] == null) {
                    pairs[i] = new Pair();
                }
                if (binStr[i] == '0') {
                    pairs[i].CountZeroes += 1;
                } else {
                    pairs[i].CountOnes += 1;
                }
            }
        }
        String most = "", least = "";
        for (Pair p : pairs) {
            if (p.CountOnes > p.CountZeroes) {
                most += "1";
                least += "0";
            } else {
                most += "0";
                least += "1";
            }
        }
        int mostNum = Integer.parseInt(most, 2);
        int leastNum = Integer.parseInt(least, 2);
        System.out.println(mostNum * leastNum);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("202103.txt");
        int numOfBits = data.get(0).length();
        List<String> cloned = new ArrayList<String>(data);
        for (int i = 0; i < numOfBits; i++) {
            final int c = i;
            long onesCount = cloned.stream().filter(n -> n.charAt(c) == '1').count();
            long zeroesCount = cloned.stream().filter(n -> n.charAt(c) == '0').count();
            if (onesCount >= zeroesCount) {
                cloned = cloned.stream().filter(n -> n.charAt(c) == '1')
                        .collect(Collectors.toList());
            } else {
                cloned = cloned.stream().filter(n -> n.charAt(c) == '0')
                        .collect(Collectors.toList());
            }
        }
        int o2Rating = Integer.parseInt(cloned.get(0), 2);

        cloned = new ArrayList<String>(data);
        for (int i = 0; i < numOfBits; i++) {
            final int c = i;
            long onesCount = cloned.stream().filter(n -> n.charAt(c) == '1').count();
            long zeroesCount = cloned.stream().filter(n -> n.charAt(c) == '0').count();
            zeroesCount = (zeroesCount == 0) ? Integer.MAX_VALUE : zeroesCount;
            onesCount = (onesCount == 0) ? Integer.MAX_VALUE : onesCount;
            if (onesCount < zeroesCount) {
                cloned = cloned.stream().filter(n -> n.charAt(c) == '1')
                        .collect(Collectors.toList());
            } else {
                cloned = cloned.stream().filter(n -> n.charAt(c) == '0')
                        .collect(Collectors.toList());
            }
        }
        int co2Rating = Integer.parseInt(cloned.get(0), 2);
        System.out.println(o2Rating * co2Rating);
    }
}
