package adventures.aoc2022;

import adventures.utils.Helpers;

import java.util.*;
import java.util.stream.Collectors;

class Puzzle202212 {

    public static void main(String[] args) {
        new Puzzle202212().part1();
        new Puzzle202212().part2();
    }

    public void part1() {
        Map<Point, Integer> grid = buildGrid();
        List<Point> startingPoints = grid.keySet().stream().filter(n -> grid.get(n) == 96).collect(Collectors.toList());
        solver(startingPoints, grid);
    }

    public void part2() {
        Map<Point, Integer> grid = buildGrid();
        List<Point> startingPoints = grid.keySet().stream().filter(n -> grid.get(n) == 96 || grid.get(n) == 97).collect(Collectors.toList());
        solver(startingPoints, grid);
    }

    private Map<Point, Integer> buildGrid() {
        String data = Helpers.loadFileAsString("202212.txt");
        List<String> lines = data.lines().collect(Collectors.toList());
        Map<Point, Integer> gridMap = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char currChar = lines.get(i).charAt(j);
                if (currChar == 'S') {
                    Point p = new Point(i, j);
                    gridMap.put(p, 96); // < (int)'a'
                } else if (currChar == 'E') {
                    Point dest = new Point(i, j);
                    gridMap.put(dest, 123); // > (int)'z'
                } else {
                    Point p = new Point(i, j);
                    gridMap.put(p, (int) currChar);
                }
            }
        }
        return gridMap;
    }

    private void solver(List<Point> startingPoints, Map<Point, Integer> grid) {
        final Point dest = grid.keySet().stream().filter(n -> grid.get(n) == 123).findFirst().get();
        System.out.println(startingPoints.stream().map(n -> getDist(n, grid, dest)).min(Comparator.comparingInt(x -> x)).get());
    }

    private int getDist(Point startPoint, Map<Point, Integer> gridMap, Point dest) {
        Queue<Point> pointQueue = new LinkedList<>();
        startPoint.stepCount = 1;
        pointQueue.offer(startPoint);
        Set<Point> visited = new HashSet<>();
        while (!pointQueue.isEmpty()) {
            Point curr = pointQueue.poll();
            if (curr.equals(dest)) {
                return curr.stepCount - 1;
            }
            if (visited.contains(curr)) {
                continue;
            }
            int stepCount = curr.stepCount;
            visited.add(curr);
            for (Point adj : curr.adjacents()) {
                if (!visited.contains(adj) && gridMap.containsKey(adj)) {
                    if (gridMap.get(adj) <= gridMap.get(curr) + 1) {
                        adj.stepCount = stepCount + 1;
                        pointQueue.offer(adj);
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    class Point {
        public int row;
        public int col;
        public int stepCount;

        public Point(int r, int c) {
            row = r;
            col = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return row == ((Point) o).row && col == ((Point) o).col;
        }

        @Override
        public int hashCode() {
            return (row + "_" + col).hashCode();
        }

        public List<Point> adjacents() {
            return List.of(new Point(row, col - 1), new Point(row, col + 1), new Point(row - 1, col), new Point(row + 1, col));
        }
    }
}
