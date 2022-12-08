package adventures;

class Puzzle202106 {
    private void findTotalNumFishes(int days) {
        String[] data = Helpers.loadFileAsString("202106.txt").split(",");
        long[] counterMap = new long[9];
        for (int i = 0; i < data.length; i++) {
            int currNum = Integer.parseInt(data[i].trim());
            counterMap[currNum]++;
        }
        for (int d = 0; d < days; d++) {
            long newFishes = counterMap[0];
            for (int i = 1; i < 9; i++) {
                counterMap[i - 1] = counterMap[i];
            }
            counterMap[6] += newFishes;
            counterMap[8] = newFishes;
        }
        long total = 0;
        for (int i = 0; i < 9; i++) {
            total += counterMap[i];
        }
        System.out.println(total);
    }

    public void part1() {
        findTotalNumFishes(80);
    }

    public void part2() {
        findTotalNumFishes(256);
    }
}
