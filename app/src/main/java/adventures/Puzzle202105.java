package adventures;

class Puzzle202105 {
    public static void main(String[] args) {
        new Puzzle202105().part1();
        new Puzzle202105().part2();
    }
    private int calcTotalCount(int[][] grid, int minX, int maxX, int minY, int maxY) {
        int totalCount = 0;
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (grid[y][x] > 1) {
                    totalCount++;
                }
            }
        }
        return totalCount;
    }

    private int[] findCoords(String currLine) {
        String[] lines = currLine.split("->");
        String[] coords1 = lines[0].split(",");
        int x1 = Integer.parseInt(coords1[0].trim());
        int y1 = Integer.parseInt(coords1[1].trim());

        String[] coords2 = lines[1].split(",");
        int x2 = Integer.parseInt(coords2[0].trim());
        int y2 = Integer.parseInt(coords2[1].trim());
        return new int[] {x1, y1, x2, y2};
    }

    public void part1() {
        String[] data = Helpers.loadFileAsString("202105.txt").split("\n");
        int[][] grid = new int[1000][1000];
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE,
                minY = Integer.MAX_VALUE;
        for (String d : data) {
            int[] curr = findCoords(d);
            int x1 = curr[0], y1 = curr[1], x2 = curr[2], y2 = curr[3];
            if (x1 == x2 || y1 == y2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                        grid[y][x] += 1;
                    }
                }
            }
            minX = Math.min(minX, (Math.min(x1, x2)));
            maxX = Math.max(maxX, (Math.max(x1, x2)));
            minY = Math.min(minY, (Math.min(y1, y2)));
            maxY = Math.max(maxY, (Math.max(y1, y2)));
        }
        System.out.println(calcTotalCount(grid, minX, maxX, minY, maxY));
    }

    public void part2() {
        String[] data = Helpers.loadFileAsString("202105.txt").split("\n");
        int[][] grid = new int[1000][1000];
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE,
                minY = Integer.MAX_VALUE;
        for (String d : data) {
            int[] curr = findCoords(d);
            int x1 = curr[0], y1 = curr[1], x2 = curr[2], y2 = curr[3];
            if (x1 == x2 || y1 == y2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                        grid[y][x] += 1;
                    }
                }
            } else if (!(x1 == x2 || y1 == y2)) {
                int deltaX = (x1 > x2) ? -1 : 1;
                int deltaY = (y1 > y2) ? -1 : 1;
                grid[y1][x1] += 1;
                while (x1 != x2) {
                    x1 += deltaX;
                    y1 += deltaY;
                    grid[y1][x1] += 1;
                }
            }
            minX = Math.min(minX, (Math.min(x1, x2)));
            maxX = Math.max(maxX, (Math.max(x1, x2)));
            minY = Math.min(minY, (Math.min(y1, y2)));
            maxY = Math.max(maxY, (Math.max(y1, y2)));
        }
        System.out.println(calcTotalCount(grid, minX, maxX, minY, maxY));
    }
}
