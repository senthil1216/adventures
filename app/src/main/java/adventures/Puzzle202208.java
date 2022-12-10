package adventures;

class Puzzle202208 {

    public static void main(String[] args) {
        new Puzzle202208().part1();
        new Puzzle202208().part2();
    }
    private final int GRID = 99;

    private boolean isTaller(int r, int c, int[][] grid) {
        int currVal = grid[r][c];
        // .. Left Horizontal
        boolean isLeft = true;
        for (int l = 0; l < c; l++) {
            if (grid[r][l] >= currVal) {
                isLeft = false;
                break;
            }
        }

        // .. Right Horizontal
        boolean isRight = true;
        for (int l = c + 1; l < GRID; l++) {
            if (grid[r][l] >= currVal) {
                isRight = false;
                break;
            }
        }

        // .. Top Vertical
        boolean isTop = true;
        for (int t = 0; t < r; t++) {
            if (grid[t][c] >= currVal) {
                isTop = false;
                break;
            }
        }

        // .. Bottom Vertical
        boolean isBottom = true;
        for (int b = r + 1; b < GRID; b++) {
            if (grid[b][c] >= currVal) {
                isBottom = false;
                break;
            }
        }
        return (isLeft || isRight || isTop || isBottom);
    }

    public void part1() {
        String[] data = Helpers.loadFileAsString("202208.txt").split("\n");
        int[][] grid = new int[GRID][GRID];
        for (int i = 0; i < GRID; i++) {
            char[] arr = data[i].toCharArray();
            for (int j = 0; j < GRID; j++) {
                grid[i][j] = Integer.parseInt("" + arr[j]);
            }
        }

        int totalTallTrees = 0;
        for (int i = 1; i < GRID - 1; i++) {
            for (int j = 1; j < GRID - 1; j++) {
                if (isTaller(i, j, grid)) {
                    totalTallTrees++;
                }
            }
        }
        totalTallTrees += (2 * (GRID));
        totalTallTrees += (2 * (GRID - 2));
        System.out.println(totalTallTrees);
    }

    private int getLeftCount(int r, int c, int[][] grid) {
        int count = 0;
        int currVal = grid[r][c];
        for (int l = c - 1; l >= 0; l--) {
            if (grid[r][l] < currVal) {
                count++;
            } else {
                return count + 1;
            }
        }
        return count;
    }

    private int getRightCount(int r, int c, int[][] grid) {
        int count = 0;
        int currVal = grid[r][c];
        for (int l = c + 1; l < GRID; l++) {
            if (grid[r][l] < currVal) {
                count++;
            } else {
                return count + 1;
            }
        }
        return count;
    }

    private int getTopCount(int r, int c, int[][] grid) {
        int count = 0;
        int currVal = grid[r][c];
        for (int v = r - 1; v >= 0; v--) {
            if (grid[v][c] < currVal) {
                count++;
            } else {
                return count + 1;
            }
        }
        return count;
    }

    private int getBottomCount(int r, int c, int[][] grid) {
        int count = 0;
        int currVal = grid[r][c];
        for (int v = r + 1; v < GRID; v++) {
            if (grid[v][c] < currVal) {
                count++;
            } else {
                return count + 1;
            }
        }
        return count;
    }

    public void part2() {
        String[] data = Helpers.loadFileAsString("202208.txt").split("\n");
        int[][] grid = new int[GRID][GRID];
        for (int i = 0; i < GRID; i++) {
            char[] arr = data[i].toCharArray();
            for (int j = 0; j < GRID; j++) {
                grid[i][j] = Integer.parseInt("" + arr[j]);
            }
        }

        int maxTreeValue = Integer.MIN_VALUE;
        for (int i = 1; i < GRID - 1; i++) {
            for (int j = 1; j < GRID - 1; j++) {
                int currTreeValue = getLeftCount(i, j, grid) * getRightCount(i, j, grid)
                        * getTopCount(i, j, grid) * getBottomCount(i, j, grid);
                maxTreeValue = Math.max(maxTreeValue, currTreeValue);
            }
        }
        System.out.println(maxTreeValue);
    }
}
