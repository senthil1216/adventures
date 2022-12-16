package adventures.aoc2022;

import adventures.utils.Helpers;

import java.util.*;
import java.util.stream.*;

class Puzzle202209 {

    public static void main(String[] args) {
        new Puzzle202209().part1();
        new Puzzle202209().part2();
    }

    class Move {
        public String dir;
        public int steps;

        public Pair moveOneStep(Pair head, Pair tail) {
            if (head.X == tail.X) {
                tail.Y += (tail.Y < head.Y) ? 1 : -1;
            } else if (head.Y == tail.Y) {
                tail.X += (tail.X < head.X) ? 1 : -1;
            } else if (tail.Y < head.Y) {
                tail.X += (tail.X < head.X) ? 1 : -1;
                tail.Y += 1;
            } else {
                tail.X += (tail.X < head.X) ? 1 : -1;
                tail.Y -= 1;
            }
            return tail;
        }
    }

    class Pair {
        public Pair(int x, int y) {
            this.X = x;
            this.Y = y;
        }

        public int X;
        public int Y;

        @Override
        public int hashCode() {
            String h = new String(this.X + " " + this.Y);
            return h.hashCode();
        }
    }

    private void solver(int sizeOfPairs) {
        String data = Helpers.loadFileAsString("202209.txt");
        List<Move> moves = data.lines().map(l -> {
            var parts = l.split(" ");
            var steps = Integer.parseInt(parts[1]);
            Move m = new Move();
            m.dir = parts[0].trim();
            m.steps = steps;
            return m;
        }).collect(Collectors.toList());
        Set<Pair> tailVisited = new HashSet<>();
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < sizeOfPairs; i++) {
            pairs.add(new Pair(0, 0));
        }
        tailVisited.add(pairs.get(sizeOfPairs - 1));
        for (Move m : moves) {
            for (int i = 0; i < m.steps; i++) {
                Pair head = pairs.get(0);
                switch (m.dir) {
                    case "U":
                        head.Y += 1;
                        break;
                    case "D":
                        head.Y -= 1;
                        break;
                    case "L":
                        head.X -= 1;
                        break;
                    case "R":
                        head.X += 1;
                        break;
                }
                pairs.set(0, head);
                for (int k = 1; k < pairs.size(); k++) {
                    Pair prev = pairs.get(k - 1);
                    Pair curr = pairs.get(k);
                    if (isTouching(prev, curr)) {
                        continue;
                    }
                    curr = m.moveOneStep(prev, curr);
                }
                Pair tail = pairs.get(pairs.size() - 1);
                tailVisited.add(tail);
            }
        }
        System.out.println(tailVisited.size());
    }

    public void part1() {
        solver(2);
    }

    private boolean isTouching(Pair head, Pair tail) {
        return Math.abs(head.X - tail.X) <= 1 && Math.abs(head.Y - tail.Y) <= 1;
    }

    public void part2() {
        solver(10);
    }
}
