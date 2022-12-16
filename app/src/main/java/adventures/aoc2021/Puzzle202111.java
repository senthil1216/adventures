package adventures.aoc2021;

import adventures.utils.Helpers;

import java.util.*;
import java.util.stream.Collectors;

class Puzzle202111 {
    public static void main(String[] args) {
        new Puzzle202111().part1();
        new Puzzle202111().part2();
    }

    public void part1() {
        Map<Point, Integer> gridMap = buildGrid();
        int flashCount = 0;
        for (int s = 0; s < 100; s++) {
            Set<Point> visited = new HashSet<>();
            for (Map.Entry<Point, Integer> entry : gridMap.entrySet()) {
                gridMap.put(entry.getKey(), entry.getValue() + 1);
            }

            for (Map.Entry<Point, Integer> entry : gridMap.entrySet()) {
                if (entry.getValue() > 9) {
                    flashCount += findFlash(visited, gridMap, entry.getKey());
                    gridMap.put(entry.getKey(), 0);
                }
            }
        }
        System.out.println(flashCount);
    }

    public void part2() {
        Map<Point, Integer> gridMap = buildGrid();
        int stepCount = 0;
        boolean allFlash = false;
        while (!allFlash) {
            stepCount++;
            Set<Point> visited = new HashSet<>();
            for (Map.Entry<Point, Integer> entry : gridMap.entrySet()) {
                gridMap.put(entry.getKey(), entry.getValue() + 1);
            }

            for (Map.Entry<Point, Integer> entry : gridMap.entrySet()) {
                if (entry.getValue() > 9) {
                    findFlash(visited, gridMap, entry.getKey());
                    gridMap.put(entry.getKey(), 0);
                }
            }

            allFlash = gridMap.values().stream().filter(n -> n != 0).count() == 0;
        }
        System.out.println(stepCount);
    }

    private Map<Point, Integer> buildGrid() {
        String data = Helpers.loadFileAsString("202111.txt");
        List<String> lines = data.lines().collect(Collectors.toList());
        Map<Point, Integer> gridMap = new LinkedHashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                gridMap.put(new Point(i, j), Integer.parseInt("" + lines.get(i).charAt(j)));
            }
        }
        return gridMap;
    }

    private int findFlash(Set<Point> visited, Map<Point, Integer> gridMap, Point curr) {
        if (visited.contains(curr)) {
            return 0;
        }
        visited.add(curr);
        int count = 1;
        for (Point adj : curr.adjacents()) {
            if (visited.contains(adj)) {
                continue;
            }
            if (gridMap.containsKey(adj)) {
                gridMap.put(adj, gridMap.get(adj) + 1);
                if (gridMap.get(adj) > 9) {
                    count += findFlash(visited, gridMap, adj);
                    gridMap.put(adj, 0);
                }
            }
        }
        return count;
    }

    class Point {
        public int row;
        public int col;

        public Point(int r, int c) {
            row = r;
            col = c;
        }

        @Override
        public int hashCode() {
            return (row + "_" + col).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return row == ((Point) o).row && col == ((Point) o).col;
        }

        public List<Point> adjacents() {
            return List.of(new Point(row - 1, col - 1), new Point(row - 1, col), new Point(row - 1, col + 1), new Point(row, col - 1), new Point(row, col + 1), new Point(row + 1, col - 1), new Point(row + 1, col), new Point(row + 1, col + 1));
        }
    }
}
