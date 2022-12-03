
package adventures;

import java.util.*;

class Puzzle202202 {

    public void part1() {
        List<String> data = Helpers.loadFile("20220201.txt");
        Map<Character, Integer> cost = new HashMap<>();
        Set<String> wins = new HashSet<>();
        wins.add("C X");
        wins.add("A Y");
        wins.add("B Z");

        Set<String> defeats = new HashSet<>();
        defeats.add("B X");
        defeats.add("C Y");
        defeats.add("A Z");
        // A - Rock, B - Paper, C - Scissor
        // X - Rock, Y - Paper, Z - Scissor
        cost.put('X', 1);
        cost.put('Y', 2);
        cost.put('Z', 3);
        int score = 0;
        for (String play : data) {
            String[] players = play.split(" ");
            char second = players[1].charAt(0);
            int secondVal = cost.get(second);
            play = play.trim();
            if (wins.contains(play)) {
                score += 6;
            } else if (defeats.contains(play)) {
                score += 0;
            } else {
                score += 3;
            }
            score += secondVal;
        }
        System.out.println(score);
    }

    public void part2() {
        List<String> data = Helpers.loadFile("20220201.txt");
        Map<Character, Integer> cost = new HashMap<>();
        Map<Character, Character> wins = new HashMap<>();
        wins.put('C', 'X');
        wins.put('A', 'Y');
        wins.put('B', 'Z');
        Map<Character, Character> defeats = new HashMap<>();
        defeats.put('B', 'X');
        defeats.put('C', 'Y');
        defeats.put('A', 'Z');
        Map<Character, Character> matches = new HashMap<>();
        matches.put('A', 'X');
        matches.put('B', 'Y');
        matches.put('C', 'Z');
        // A - Rock, B - Paper, C - Scissor
        // X - Rock, Y - Paper, Z - Scissor
        cost.put('X', 1);
        cost.put('Y', 2);
        cost.put('Z', 3);

        int score = 0;
        for (String play : data) {
            String[] players = play.split(" ");
            char first = players[0].charAt(0);
            char second = players[1].charAt(0);
            play = play.trim();

            if (second == 'Y') { // .. draw
                second = matches.get(first);
                score += 3;
            } else if (second == 'X') { // .. lose
                second = defeats.get(first);
                score += 0;
            } else { // .. win
                second = wins.get(first);
                score += 6;
            }
            score += cost.get(second);
        }
        System.out.println(score);
    }

}