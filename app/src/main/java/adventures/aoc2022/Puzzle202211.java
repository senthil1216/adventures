package adventures.aoc2022;

import adventures.utils.Helpers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Puzzle202211 {
    public static void main(String[] args) {
        new Puzzle202211().part1();
        new Puzzle202211().part2();
    }

    private Monkey prepare(List<String> data) {
        Monkey m = new Monkey();
        m.items = Arrays.stream(data.get(1).substring(data.get(1).lastIndexOf(":") + 1).split(",")).map(n -> {
            return BigInteger.valueOf(Long.valueOf(n.trim()));
        }).collect(Collectors.toList());
        m.testDivisible = BigInteger.valueOf(Long.valueOf(data.get(3).substring(data.get(3).lastIndexOf("y ") + 1).trim()));
        m.trueIdx = Integer.parseInt(data.get(4).substring(data.get(4).lastIndexOf(" ")).trim());
        m.falseIdx = Integer.parseInt(data.get(5).substring(data.get(5).lastIndexOf(" ")).trim());
        var operations = data.get(2).substring(data.get(2).lastIndexOf("=") + 1).trim();
        if (operations.indexOf("*") > 0) {
            String opNumStr = operations.split("\\*")[1].trim();
            if (opNumStr.equals("old")) {
                m.op = OpName.SQ;
            } else {
                m.op = OpName.MULTI;
                m.opNum = BigInteger.valueOf(Long.valueOf(opNumStr));
            }
        } else {
            m.op = OpName.ADD;
            m.opNum = BigInteger.valueOf(Long.valueOf(operations.split("\\+")[1].trim()));
        }
        return m;
    }

    private void solver(int rounds, boolean isLCM) {
        String data = Helpers.loadFileAsString("202211.txt");
        String[] monkeys = data.split("\n\n");
        List<String> input = Arrays.asList(monkeys);
        List<Monkey> monkeyObjects = input.stream().map(k -> {
            return prepare(k.lines().collect(Collectors.toList()));
        }).collect(Collectors.toList());
        BigInteger multiples = monkeyObjects.stream().map(n -> n.testDivisible).reduce(BigInteger.ONE, (a, b) -> a.multiply(b));
        for (int i = 0; i < rounds; i++) {
            for (int k = 0; k < monkeyObjects.size(); k++) {
                Monkey m = monkeyObjects.get(k);
                for (BigInteger item : m.items) {
                    if (m.op == OpName.ADD) {
                        item = item.add(m.opNum);
                    } else if (m.op == OpName.MULTI) {
                        item = item.multiply(m.opNum);
                    } else if (m.op == OpName.SQ) {
                        item = item.multiply(item);
                    }
                    if (isLCM) {
                        item = item.divideAndRemainder(multiples)[1];
                    } else {
                        item = item.divide(BigInteger.valueOf(3L));
                    }
                    if (item.divideAndRemainder(m.testDivisible)[1].equals(BigInteger.ZERO)) {
                        monkeyObjects.get(m.trueIdx).items.add(item);
                    } else {
                        monkeyObjects.get(m.falseIdx).items.add(item);
                    }
                }
                m.count += m.items.size();
                m.items.clear();
            }
        }
        System.out.println(monkeyObjects.stream().map(m -> m.count).sorted(Collections.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b));
    }

    public void part1() {
        solver(20, false);
    }

    public void part2() {
        solver(10_000, true);
    }

    enum OpName {
        MULTI,
        SQ,
        ADD
    }

    class Monkey {

        public List<BigInteger> items;
        public BigInteger testDivisible;
        public int trueIdx;
        public int falseIdx;
        public OpName op;
        public BigInteger opNum;
        public long count;
    }
}
