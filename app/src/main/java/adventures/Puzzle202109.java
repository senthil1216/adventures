package adventures;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.base.Strings;

class Puzzle202109 {
    class Pair {
        public Pair(int r, int c){
            this.Row = r;
            this.Col = c;
        }
        public int Row;
        public int Col;

        @Override
        public int hashCode(){
            String h = Row + " " + Col;
            return h.hashCode();
        }
    }
    public static void main(String[] args) {
        String data = Helpers.loadFileAsString("202109.txt");
        new Puzzle202109().part1(data);
        new Puzzle202109().part2(data);
    }

    private boolean isSmallest(int r, int c, List<List<Integer>> grid){
        boolean isValid = true;
        int curr = grid.get(r).get(c);
        int[][] dirs = new int[][]{
                new int[]{r-1, c},
                new int[]{r+1, c},
                new int[]{r, c-1},
                new int[]{r, c+1},
        };
        for(int i =0; i <dirs.length; i++){
            int currR = dirs[i][0];
            int currC = dirs[i][1];
            if(currR < 0 || currC < 0 || currR >= grid.size() || currC >= grid.get(0).size()) {
                continue;
            }
            isValid &= curr < grid.get(currR).get(currC);
        }
        return isValid;
    }

    public void part1(String data) {
        List<List<Integer>> grid = new ArrayList<>();
        data.lines().collect(Collectors.toList()).stream().forEach(m -> {
            List<Integer> curr = new ArrayList<>();
            for(int i = 0;i < m.length(); i++) {
                curr.add(Integer.parseInt(""+m.charAt(i)));
            }
            grid.add(curr);
        });
        int sum = 0;
        List<Pair> basin = new ArrayList<>();

        for(int r = 0;r < grid.size(); r++) {
            for(int c = 0; c < grid.get(r).size(); c++) {
                if(isSmallest(r, c, grid)) {
                    sum += grid.get(r).get(c) +1;
                }
            }
        }
        System.out.println(sum);
    }
    public void part2(String data) {
        List<List<Integer>> grid = new ArrayList<>();
        data.lines().collect(Collectors.toList()).stream().forEach(m -> {
            List<Integer> curr = new ArrayList<>();
            for(int i = 0;i < m.length(); i++) {
                curr.add(Integer.parseInt(""+m.charAt(i)));
            }
            grid.add(curr);
        });
        Set<Integer> visited = new HashSet<>();
        List<Integer> largest = new ArrayList<>();
        for(int r = 0;r < grid.size(); r++) {
            for(int c = 0; c < grid.get(r).size(); c++) {
                Pair currPair = new Pair(r, c);
                List<Pair> basin = new ArrayList<>();
                if(!visited.contains(currPair.hashCode()) && grid.get(r).get(c) < 9) {
                    findBasin(r, c, grid, basin, visited);
                    largest.add(basin.size());
                }
            }
        }
        int sum = 1;
        largest = largest.stream().sorted(Collections.reverseOrder()).limit(3).collect(Collectors.toList());
        for(Integer n: largest){
            sum *= n;
        }
        System.out.println(sum);
    }

    public void findBasin(int r, int c, List<List<Integer>> grid, List<Pair> basin, Set<Integer> visited){
        if(visited.contains(new Pair(r, c).hashCode())) {
            return;
        }
        visited.add(new Pair(r, c).hashCode());
        basin.add(new Pair(r,c));
        int[][] dirs = new int[][]{
                new int[]{r-1, c},
                new int[]{r+1, c},
                new int[]{r, c-1},
                new int[]{r, c+1},
        };
        for(int i =0; i <dirs.length; i++){
            int currR = dirs[i][0];
            int currC = dirs[i][1];
            if(currR < 0 || currC < 0 || currR >= grid.size() || currC >= grid.get(0).size()) {
                continue;
            }
            if(grid.get(currR).get(currC) == 9) {
                continue;
            }
            findBasin(currR, currC, grid, basin, visited);
        }
    }
}
