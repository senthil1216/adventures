package adventures;

import java.util.*;
import java.util.stream.*;
import com.google.common.base.Strings;

class Puzzle202104 {
    public static void main(String[] args) {
        new Puzzle202104().part1();
        new Puzzle202104().part2();
    }
    class Grid {
        public Grid() {
            values = new ArrayList<>();
        }

        public List<List<Integer>> values;
    }

    private boolean markAndCheckGrid(Grid grid, int bingoVal) {
        for (int i = 0; i < grid.values.size(); i++) {
            List<Integer> values = grid.values.get(i);
            for (int j = 0; j < values.size(); j++) {
                if (values.get(j) == bingoVal) {
                    values.set(j, -1);

                    List<Integer> rowValues =
                            values.stream().distinct().collect(Collectors.toList());
                    if (rowValues.size() == 1 && rowValues.get(0) == -1) {
                        return true;
                    }
                    final int colIdx = j;
                    final List<Integer> colValues = new ArrayList<>();
                    IntStream.range(0, grid.values.size())
                            .forEach(k -> colValues.add(grid.values.get(k).get(colIdx)));
                    List<Integer> distinctColValues =
                            colValues.stream().distinct().collect(Collectors.toList());
                    if (distinctColValues.size() == 1 && distinctColValues.get(0) == -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int calcTotalScore(Grid grid, int bingo) {
        int totalScore = 0;
        for (List<Integer> gridValues : grid.values) {
            for (Integer currValue : gridValues) {
                if (currValue == -1) {
                    continue;
                }
                totalScore += currValue;
            }
        }
        totalScore *= bingo;
        return totalScore;
    }


    private List<Grid> buildGridStruct(String[] data) {
        List<Grid> grids = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            Grid grid = new Grid();
            String[] gridStr = data[i].split("\n");
            for (int j = 0; j < gridStr.length; j++) {
                List<Integer> currValues = new ArrayList<>();
                String[] currRow = gridStr[j].split(" ");
                for (int k = 0; k < currRow.length; k++) {
                    String gridNum = currRow[k];
                    if (Strings.isNullOrEmpty(gridNum)) {
                        continue;
                    }
                    currValues.add(Integer.parseInt(gridNum));
                }
                grid.values.add(currValues);
            }
            grids.add(grid);
        }
        return grids;
    }

    public void part1() {
        String[] data = Helpers.loadFileAsString("202104.txt").split("\n\n");
        String[] bingoValues = data[0].split(",");

        List<Grid> grids = buildGridStruct(data);
        boolean found = false;
        int totalScore = 0;
        for (String bingo : bingoValues) {
            if (found) {
                break;
            }
            for (int i = 0; i < grids.size(); i++) {
                Grid grid = grids.get(i);
                if (markAndCheckGrid(grid, Integer.parseInt(bingo))) {
                    totalScore = calcTotalScore(grid, Integer.parseInt(bingo));
                    found = true;
                    break;
                }
            }
        }
        System.out.println(totalScore);
    }


    public void part2() {
        String[] data = Helpers.loadFileAsString("202104.txt").split("\n\n");
        String[] bingoValues = data[0].split(",");
        Set<Integer> gridIndexes = new HashSet<>();
        List<Grid> grids = buildGridStruct(data);
        for (int i = 0; i < grids.size(); i++) {
            gridIndexes.add(i + 1);
        }
        boolean found = false;
        int totalScore = 0;
        for (String bingo : bingoValues) {
            if (found) {
                break;
            }
            for (int i = 0; i < grids.size(); i++) {
                Grid grid = grids.get(i);
                if (markAndCheckGrid(grid, Integer.parseInt(bingo))) {
                    gridIndexes.remove(i + 1);
                    if (gridIndexes.size() != 0) {
                        continue;
                    }
                    totalScore = calcTotalScore(grid, Integer.parseInt(bingo));
                    found = true;
                    break;
                }
            }
        }
        System.out.println(totalScore);
    }
}
