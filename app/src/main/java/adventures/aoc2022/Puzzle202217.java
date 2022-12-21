package adventures.aoc2022;

import adventures.data.Point;
import adventures.utils.Helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class Puzzle202217 {
    private final String LINE = "####";
    private final String CROSS = """
            .#.
            ###
            .#.""";
    private final String EL = """
            ..#
            ..#
            ###""";
    private final String ROD = """
            #
            #
            #
            #""";
    private final String BLOCK = """
            ##
            ##""";
    private final String[] ORDER = new String[]{LINE, CROSS, EL, ROD, BLOCK};

    public static void main(String[] args) {
        new Puzzle202217().part1();
//        new Puzzle202217().part2();
    }

    public void part1() {
        String data = Helpers.loadFileAsString("202217.txt");
        int rockCount = 0;
        Chamber chamber = new Chamber();
        int incr = 0;
        while (rockCount <= 2022) {
            String rockStr = ORDER[rockCount % ORDER.length];
            Rock r = new Rock(rockStr, 2, chamber.minY() - 4);
            boolean isValid = true;
            while (isValid) {
                char dir = data.charAt(incr % data.length());
                switch (dir) {
                    case '>' -> r.moveRight(chamber);
                    case '<' -> r.moveLeft(chamber);
                }
                isValid = r.canMoveDown(chamber);
                if (!isValid) {
                    chamber.merge(r);
                }
                incr++;
            }
            rockCount++;
        }
        System.out.println(Math.abs(chamber.minY()));
    }

    public void part2() {
    }

    public class Chamber {
        Map<Point, Character> map = new HashMap<>();

        public Chamber() {

        }

        public int minY() {
            return map.keySet().stream().mapToInt(Point::y).min().orElse(0);
        }

        public void merge(Rock r) {
            map.putAll(r.getMap());
        }
    }

    public class Rock {
        Map<Point, Character> map = new HashMap<>();

        public Rock(String line, int x, int y) {
            List<String> curr = line.lines().toList();
            for (int r = 0; r < curr.size(); r++) {
                for (int c = 0; c < curr.get(r).length(); c++) {
                    if (curr.get(r).charAt(c) == '#') {
                        map.put(new Point(x + c, y + r - curr.size() + 1), '#');
                    }
                }
            }
        }

        public Map<Point, Character> getMap() {
            return map;
        }

        public void moveLeft(Chamber ch) {
            if (minX() > 0) {
                Map<Point, Character> res = transform(-1, 0);
                for (Point entry : ch.map.keySet()) {
                    if (res.containsKey(entry)) {
                        return;
                    }
                }
                map = res;
            }
        }

        public void moveRight(Chamber ch) {
            if (maxX() < 6) {
                Map<Point, Character> res = transform(1, 0);
                for (Point entry : ch.map.keySet()) {
                    if (res.containsKey(entry)) {
                        return;
                    }
                }
                map = res;
            }
        }

        public boolean canMoveDown(Chamber ch) {
            Map<Point, Character> result = transform(0, 1);
            OptionalInt maxVal = result.keySet().stream().mapToInt(Point::y).max();
            if (maxVal.isPresent() && maxVal.getAsInt() == 0) {
                return false;
            }
            for (Point entry : ch.map.keySet()) {
                if (result.containsKey(entry)) {
                    return false;
                }
            }
            map = result;
            return true;
        }

        private int minX() {
            return map.keySet().stream().mapToInt(Point::x).min().orElseThrow();
        }

        private int maxX() {
            return map.keySet().stream().mapToInt(Point::x).max().orElseThrow();
        }

        public Map<Point, Character> transform(int xTrans, int yTrans) {
            Map<Point, Character> result = new HashMap<>();
            for (var e : map.entrySet()) {
                result.put(new Point(e.getKey().x() + xTrans, e.getKey().y() + yTrans), e.getValue());
            }
            return result;
        }
    }
}
