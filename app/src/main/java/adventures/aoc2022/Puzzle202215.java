package adventures.aoc2022;

import adventures.data.Point;
import adventures.utils.Helpers;
import adventures.utils.InputUtils;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Puzzle202215 {
    public static void main(String[] args) {
        new Puzzle202215().part1();
        new Puzzle202215().part2();
    }

    private List<Sensor> prepareSensors() {
        List<String> lines = Helpers.loadFile("202215.txt");

        return lines.stream().map(InputUtils::parseInts).map(n -> {
            Point sp = new Point(n[0], n[1]);
            Point bp = new Point(n[2], n[3]);
            return new Sensor(sp, bp, sp.dist(bp));
        }).toList();
    }

    public void part1() {
        int inputY = 10;
        List<Sensor> sensors = prepareSensors();
        int minX = sensors.stream().map(n -> n.sensor().x() - n.radius()).min(Comparator.comparingInt(x -> x)).get();
        int maxX = sensors.stream().map(n -> n.sensor().x() + n.radius()).max(Comparator.comparingInt(x -> x)).get();
        Set<Point> unique = new HashSet<>();
        for (int x = minX; x <= maxX; x++) {
            Point p = new Point(x, inputY);
            for (Sensor s : sensors) {
                if (s.excludes(p) && !s.beacon().equals(p)) {
                    unique.add(p);
                }
            }
        }
        System.out.println(unique.size());
    }

    public void part2() {
        List<Sensor> sensors = prepareSensors();
        for (Sensor sensor : sensors) {
            for (int i = -(sensor.radius() + 1); i <= sensor.radius(); i++) {
                int width = (sensor.radius() - Math.abs(i));
                Point curr = sensor.sensor();
                if (isCoordinateOutsideSensorRange(sensors, (curr.x() - width - 1), (curr.y() + i)) ||
                        isCoordinateOutsideSensorRange(sensors, (curr.x() + width + 1), (curr.y() + i))) {
                    return;
                }
            }
        }
    }

    private boolean isCoordinateOutsideSensorRange(List<Sensor> sensors, int tx, int ty) {
        if (tx >= 0 && tx <= 4000000 && ty >= 0 && ty <= 4000000
                && sensors.stream().allMatch(sensor -> !sensor.excludes(new Point(tx, ty)))) {
            System.out.println("Frequency: " + BigInteger.valueOf(tx).multiply(BigInteger.valueOf(4000000)).add(BigInteger.valueOf((ty))));
            return true;
        }
        return false;
    }


    public record Sensor(Point sensor, Point beacon, int radius) {
        boolean excludes(Point p) {
            return sensor().dist(p) <= radius();
        }
    }
}
