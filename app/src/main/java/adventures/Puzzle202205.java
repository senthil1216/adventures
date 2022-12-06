package adventures;

import java.util.*;
import com.google.common.base.Strings;
import com.google.common.primitives.Chars;
import org.apache.commons.collections4.ListUtils;

class Puzzle202205 {
    public void part1() {
        String data = Helpers.loadFileAsString("202205.txt");
        String[] values = data.split("\n\n");
        String[] stackStr = values[0].split("\n");
        String instr = values[1];
        Map<Integer, List<String>> arranged = new HashMap<>();
        for (int i = 0; i < stackStr.length - 1; i++) {
            List<Character> chars = Chars.asList(stackStr[i].toCharArray());
            List<List<Character>> splitter = ListUtils.partition(chars, 4);
            int stackIdx = 0;
            for (List<Character> st : splitter) {
                String stackValue = st.get(1).toString().trim();
                stackIdx++;
                if (Strings.isNullOrEmpty(stackValue)) {
                    continue;
                }
                List<String> stacks = new ArrayList<>();
                if (arranged.containsKey(stackIdx)) {
                    stacks = arranged.get(stackIdx);
                }
                stacks.add(stackValue);
                arranged.put(stackIdx, stacks);
            }
        }
        for (String m : instr.split("\n")) {
            String[] moves = m.split(" ");
            int count = Integer.parseInt(moves[1]);
            int fromIdx = Integer.parseInt(moves[3]);
            int toIdx = Integer.parseInt(moves[5]);
            List<String> fromValues = arranged.get(fromIdx);
            List<String> toValues = arranged.get(toIdx);
            for (int i = 0; i < count; i++) {
                String moved = fromValues.remove(0);
                toValues.add(0, moved);
            }
        }
        StringBuilder b = new StringBuilder();
        arranged.entrySet().stream().map(k -> k.getValue().get(0)).forEach(k -> b.append(k));
        System.out.println(b.toString());
    }

    public void part2() {
        String data = Helpers.loadFileAsString("202205.txt");
        String[] values = data.split("\n\n");
        String[] stackStr = values[0].split("\n");
        String instr = values[1];
        Map<Integer, List<String>> arranged = new HashMap<>();
        for (int i = 0; i < stackStr.length - 1; i++) {
            List<Character> chars = Chars.asList(stackStr[i].toCharArray());
            List<List<Character>> splitter = ListUtils.partition(chars, 4);
            int stackIdx = 0;
            for (List<Character> st : splitter) {
                String stackValue = st.get(1).toString().trim();
                stackIdx++;
                if (Strings.isNullOrEmpty(stackValue)) {
                    continue;
                }
                List<String> stacks = new ArrayList<>();
                if (arranged.containsKey(stackIdx)) {
                    stacks = arranged.get(stackIdx);
                }
                stacks.add(stackValue);
                arranged.put(stackIdx, stacks);
            }
        }
        for (String m : instr.split("\n")) {
            String[] moves = m.split(" ");
            int count = Integer.parseInt(moves[1]);
            int fromIdx = Integer.parseInt(moves[3]);
            int toIdx = Integer.parseInt(moves[5]);
            List<String> fromValues = arranged.get(fromIdx);
            List<String> toValues = arranged.get(toIdx);
            List<String> toRemove = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String moved = fromValues.remove(0);
                toRemove.add(moved);
            }
            toValues.addAll(0, toRemove);
        }
        StringBuilder b = new StringBuilder();
        arranged.entrySet().stream().map(k -> k.getValue().get(0)).forEach(k -> b.append(k));
        System.out.println(b.toString());
    }
}
