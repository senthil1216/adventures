package adventures;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle202112 {
    public static void main(String[] args) {
        Map<String, Set<String>> graph = readGraph();
        new Puzzle202112().firstPart(graph);
        new Puzzle202112().secondPart(graph);
    }

    private static Map<String, Set<String>> readGraph() {
        Map<String, Set<String>> graph = new HashMap<>();
        String data = Helpers.loadFileAsString("202112.txt");
        data.lines().forEach(line -> {
            var parts = line.split("-");
            graph.computeIfAbsent(parts[0], k -> new HashSet<>()).add(parts[1]);
            graph.computeIfAbsent(parts[1], k -> new HashSet<>()).add(parts[0]);
        });
        return graph;
    }

    private void firstPart(Map<String, Set<String>> graph) {
        System.out.println(countPaths(graph, false));
    }

    private void secondPart(Map<String, Set<String>> graph) {
        System.out.println(countPaths(graph, true));
    }

    private int countPaths(Map<String, Set<String>> graph, boolean doublingAllowed) {
        Queue<Past> paths = new LinkedList<>();
        int found = 0;
        paths.add(new Past("start"));
        while (!paths.isEmpty()) {
            var top = paths.poll();
            if (!top.isEnd()) {
                paths.addAll(top.extend(graph.get(top.currentPosition()), doublingAllowed));
            } else {
                ++found;
            }
        }
        return found;
    }

    class Past {
        private final Set<String> visitedCaves = new HashSet<>();
        private final String doubleSmall;
        private final String lastVisited;

        Past(String step) {
            this(new HashSet<>(), step, null);
        }

        Past(Set<String> caves, String step, String doubleSmall) {
            this.visitedCaves.addAll(caves);
            this.doubleSmall = doubleSmall == null ? determineDouble(step) : doubleSmall;
            this.visitedCaves.add(step);
            this.lastVisited = step;
        }

        String currentPosition() {
            return lastVisited;
        }

        Past extend(String step) {
            return new Past(visitedCaves, step, doubleSmall);
        }

        List<Past> extend(Set<String> possibilities, boolean doublingAllowed) {
            return possibilities.stream().filter(c -> canExtend(c, doublingAllowed)).map(this::extend).collect(Collectors.toList());
        }

        boolean canExtend(String cave, boolean doublingAllowed) {
            return isBig(cave) || (!cave.equals("start")
                    && (!visitedCaves.contains(cave) || (doublingAllowed && doubleSmall == null)));
        }

        boolean isEnd() {
            return "end".equals(lastVisited);
        }

        boolean isBig(String cave) {
            return cave.equals(cave.toUpperCase(Locale.ROOT));
        }

        private String determineDouble(String candidate) {
            if (!isBig(candidate) && visitedCaves.contains(candidate)) {
                return candidate;
            }
            return null;
        }
    }
}