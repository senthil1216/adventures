package adventures.aoc2022;

import adventures.utils.Helpers;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Puzzle202213 {

    public static void main(String[] args) {
        new Puzzle202213().part1();
        new Puzzle202213().part2();
    }

    public static int compare(Object left, Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt)
            return leftInt - rightInt;

        // If left list runs out of items first, it is in the right order.
        if (left instanceof List<?> leftList && right instanceof List<?> rightList) {
            if (leftList.isEmpty())
                return rightList.isEmpty() ? 0 : -1;

            var minSize = Math.min(leftList.size(), rightList.size());

            for (int i = 0; i < minSize; i++) {
                int check = compare(leftList.get(i), rightList.get(i));
                if (check != 0) return check;
            }

            // all elements matched.
            return leftList.size() - rightList.size();
        }

        // otherwise one side is an int and the other is a list.
        if (left instanceof Integer leftInt)
            return compare(List.of(leftInt), right);

        if (right instanceof Integer rightInt)
            return compare(left, List.of(rightInt));

        throw new RuntimeException("Should not happen");
    }

    public void part1() {
        String[] data = Helpers.loadFileAsString("202213.txt").split("\n\n");
        var pairs = Arrays.stream(data).map(n -> {
            var splitter = n.split("\n");
            String left = splitter[0].trim();
            String right = splitter[1].trim();
            var leftPacket = parsePacket(left);
            var rightPacket = parsePacket(right);
            return new Pair(leftPacket, rightPacket);
        }).toList();

        int sum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            Pair p = pairs.get(i);
            if (p.isRight()) {
                sum += i + 1;
            }
        }
        System.out.println(sum);
    }

    public void part2() {
        String data = Helpers.loadFileAsString("202213.txt").replaceAll("\n\n", "\n");
        data += "[[2]]\n";
        data += "[[6]]\n";
        String[] splitter = data.split("\n");
        var allValues = Arrays.stream(splitter).map(this::parsePacket).toList();
        var first = parsePacket("[[2]]");
        var second = parsePacket("[[6]]");
        var sorted = allValues.stream().sorted(Puzzle202213::compare).toList();
        int firstIdx = 0, secondIdx = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).equals(first)) {
                firstIdx = i + 1;
            }

            if (sorted.get(i).equals(second)) {
                secondIdx = i + 1;
            }
        }
        System.out.println(firstIdx * secondIdx);
    }

    private List<Object> parsePacketFromJsonArray(JsonArray elements) {
        var items = new ArrayList<>();

        elements.forEach(e -> {
            if (e.isJsonArray())
                items.add(parsePacketFromJsonArray(e.getAsJsonArray()));
            else
                items.add(e.getAsInt());
        });

        return items;
    }

    private List<Object> parsePacket(String inputLine) {
        var json = JsonParser.parseString(inputLine);
        return parsePacketFromJsonArray(json.getAsJsonArray());
    }

    public record Pair(Object left, Object right) {
        boolean isRight() {
            return compare(left, right) <= 0;
        }

    }
}
