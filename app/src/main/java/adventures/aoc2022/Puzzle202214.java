package adventures.aoc2022;

import adventures.data.Point;
import adventures.utils.Helpers;
import adventures.utils.InputUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Puzzle202214 {

    public static void main(String[] args) {
        new Puzzle202214().part1();
        new Puzzle202214().part2();
    }

    private void solver(int partNumber) {
        List<String> lines = Helpers.loadFile("202214.txt");
        List<int[]> lists = lines.stream().map(InputUtils::parseInts).toList();
        Map<Point, Character> map = new HashMap<>();
        for (int[] line : lists) {
            Point prev = new Point(line[0], line[1]);
            for (int i = 2; i < line.length; i += 2) {
                Point curr = new Point(line[i], line[i + 1]);
                if (prev.x() == curr.x()) {
                    for (int y = Math.min(prev.y(), curr.y()); y <= Math.max(prev.y(), curr.y()); y++) {
                        map.put(new Point(curr.x(), y), '#');
                    }
                } else if (prev.y() == curr.y()) {
                    for (int x = Math.min(prev.x(), curr.x()); x <= Math.max(prev.x(), curr.x()); x++) {
                        map.put(new Point(x, curr.y()), '#');
                    }
                }
                prev = curr;
            }
        }
        int maxY = map.keySet().stream().mapToInt(Point::y).max().getAsInt();
        int sandCount = 0;
        Point start = new Point(500, 0);
        while (true) {
            sandCount++;
            Point s = start;
            while (true) {
                Optional<Point> next = Stream.of(new Point(s.x(), s.y() + 1),
                                new Point(s.x() - 1, s.y() + 1),
                                new Point(s.x() + 1, s.y() + 1))
                        .filter(m -> m.y() <= maxY + 1)
                        .filter(m -> !map.containsKey(m))
                        .findFirst();

                if (next.isPresent()) {
                    s = next.get();
                } else {
                    break;
                }
            }
            map.put(s, 'O');
            if (partNumber == 1 && s.y() == maxY + 1) {
                sandCount--;
                break;
            }
            if (partNumber == 2 && s.equals(start)) {
                break;
            }
        }
        System.out.println(sandCount);
    }

    public void part1() {
        solver(1);
    }

    public void part2() {
        solver(2);
    }
}
