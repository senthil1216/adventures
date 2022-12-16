package adventures.aoc2021;

import java.util.*;
import java.util.stream.Collectors;

import adventures.utils.Helpers;
import com.google.common.base.Strings;

class Puzzle202108 {
    public static void main(String[] args) {
        new Puzzle202108().part1();
        new Puzzle202108().part2();
    }
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
    private int decodeStringByLength(String curr) {
        switch (curr.length()) {
            case 2:
                return 1;
            case 4:
                return 4;
            case 3:
                return 7;
            case 7:
                return 8;
            default:
                return -1;
        }
    }

    private int overLapCharLen(String s, String p) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (p.indexOf(s.charAt(i)) > -1) {
                count++;
            }
        }
        return count;
    }

    private int[] findOverLapLen(String s, Map<Integer, String> decodedNum) {
        int overLap7 = overLapCharLen(s, decodedNum.get(7));

        int overLap1 = overLapCharLen(s, decodedNum.get(1));

        int overLap4 = overLapCharLen(s, decodedNum.get(4));

        int overLap8 = overLapCharLen(s, decodedNum.get(8));
        return new int[] {overLap7, overLap1, overLap4, overLap8};
    }

    // 2, 3, 5 ==> length 5
    // if len == 5:
    // len of characters that overlap between 2 & 7 = 2 || 2 & 1 = 2 || 2 & 4 = 2 || 2 & 8 = 2
    // len of characters that overlap between 3 & 7 = 3 || 3 & 1 = 2 || 3 & 4 = 2 || 3 & 8 = 4
    // len of characters that overlap between 5 & 7 = 2 || 5 & 1 = 1 || 5 & 4 = 1 || 5 & 8 = 5
    private int decodeTwoThreeFive(String s, Map<Integer, String> decodedNum) {
        int[] res = findOverLapLen(s, decodedNum);
        if (res[0] == 3) {
            return 3;
        }
        if (res[2] == 2) {
            return 2;
        }
        return 5;
    }

    // 0, 6, 9 ==> length 6
    // if len == 6:
    // len of characters that overlap between 0 & 7 = 3 || 0 & 1 = 2 || 0 & 4 = 3 || 0 & 8 = 6
    // len of characters that overlap between 6 & 7 = 2 || 6 & 1 = 1 || 6 & 4 = 3 || 6 & 8 = 6
    // len of characters that overlap between 9 & 7 = 3 || 9 & 1 = 2 || 9 & 4 = 4 || 9 & 8 = 7
    private int decodeZeroSixNine(String s, Map<Integer, String> decodedNum) {
        int[] res = findOverLapLen(s, decodedNum);
        if (res[0] == 2) {
            return 6;
        }
        if (res[2] == 3) {
            return 0;
        }
        return 9;
    }


    private String getSorted(String s) {
        return s.chars().sorted().mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining());
    }

    private Map<String, Integer> decodeMap(String pattern) {
        Map<String, Integer> result = new HashMap<>();
        String[] output = pattern.split(" ");
        List<String> nonDecoded = new ArrayList<>();
        Map<Integer, String> decodedNum = new HashMap<>();

        for (String s : output) {
            var sorted = getSorted(s);
            int num = decodeStringByLength(sorted);
            if (num > 0) {
                result.put(sorted, num);
                decodedNum.put(num, sorted);
            } else {
                nonDecoded.add(s);
            }
        }
        for (String nd : nonDecoded) {
            int num = 0;
            // if length == 5, then it has to be either one of 2, 3, 5
            if (nd.length() == 5) {
                num = decodeTwoThreeFive(nd, decodedNum);
            }

            // if length == 6, then it has to be either one of 0, 6, 9
            if (nd.length() == 6) {
                num = decodeZeroSixNine(nd, decodedNum);
            }

            var sorted = getSorted(nd);
            result.put(sorted, num);
        }
        return result;
    }

    public void part2() {
        String[] data = Helpers.loadFileAsString("202108.txt").split("\n");
        int count = 0;
        for (String d : data) {
            String[] instr = d.split("\\|");

            /* Decoding the patterns */
            Map<String, Integer> decoded = decodeMap(instr[0].trim());

            /** Calculate the actual numbers */
            List<String> actual = Arrays.asList(instr[1].trim().split(" "));
            String value = actual.stream().map(k -> {
                return getSorted(k);
            }).map(sorted -> {
                for (Map.Entry<String, Integer> entry : decoded.entrySet()) {
                    if (sorted.length() == entry.getKey().length()
                            && overLapCharLen(sorted, entry.getKey()) == entry.getKey().length()) {
                        return "" + entry.getValue();
                    }
                }
                return "";
            }).filter(n -> !Strings.isNullOrEmpty(n)).collect(Collectors.joining());
            count += Integer.parseInt(value);
        }
        System.out.println(count);
    }
}
