package adventures.aoc2022;

import adventures.utils.Helpers;
import java.util.*;
import java.util.stream.*;

public class Puzzle202218 {


    public static void main(String[] args) {
        new Puzzle202218().part1();
        new Puzzle202218().part2();
    }

    public void part1() {
        List<String> input = Helpers.loadFile("202218.txt");
        Set<Cube> cubes = input.stream().map(n -> {
            String[] data = n.split(",");
            int num0 = Integer.parseInt(data[0]);
            int num1 = Integer.parseInt(data[1]);
            int num2 = Integer.parseInt(data[2]);
            return new Cube(num0, num1, num2);
        }).collect(Collectors.toSet());
        int sum = 0;
        for (Cube cube : cubes) {
            sum += 6;
            for (Cube neighbour : cube.neighbours()) {
                if (cubes.contains(neighbour)) {
                    sum--;
                }
            }
        }
        System.out.println(sum);
    }

    public void part2() {
        var input = Helpers.loadFileAsString("202218.txt");
    }

    public record Cube(int x, int y, int z) {
        List<Cube> neighbours() {
            return List.of(
                    new Cube(x() - 1, y(), z()),
                    new Cube(x() + 1, y(), z()),
                    new Cube(x(), y() + 1, z()),
                    new Cube(x(), y() - 1, z()),
                    new Cube(x(), y(), z() - 1),
                    new Cube(x(), y(), z() - 1)
            );
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cube other = (Cube) o;
            return x() == other.x() && y() == other.y() && z() == other.z();
        }

        @Override
        public int hashCode() {
            return (x() + "_" + y() + "_" + z()).hashCode();
        }
    }
}