package adventures;

import java.util.*;

class Puzzle202102 {

    public static void main(String[] args) {
        new Puzzle202102().part1();
        new Puzzle202102().part2();
    }
    public void part1() {
        List<String> data = Helpers.loadFile("202102.txt");
        int h = 0, v = 0;
        for (String d : data) {
            String[] dirsValues = d.split(" ");
            String dir = dirsValues[0];
            int value = Integer.parseInt(dirsValues[1]);
            if (dir.equals("forward")) {
                h += value;
            } else if (dir.equals("down")) {
                v += value;
            } else if (dir.equals("up")) {
                v -= value;
            }
        }
        System.out.println(h * v);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("202102.txt");
        int h = 0, aim = 0, v = 0;
        for (String d : data) {
            String[] dirsValues = d.split(" ");
            String dir = dirsValues[0];
            int value = Integer.parseInt(dirsValues[1]);
            if (dir.equals("forward")) {
                h += value;
                v += (aim * value);
            } else if (dir.equals("down")) {
                aim += value;
            } else if (dir.equals("up")) {
                aim -= value;
            }
        }
        System.out.println(h * v);
    }
}
