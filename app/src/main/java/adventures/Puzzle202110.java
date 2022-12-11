package adventures;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.base.Strings;

class Puzzle202110 {
    public static void main(String[] args) {
        String data = Helpers.loadFileAsString("202110.txt");
        new Puzzle202110().part1(data);
        new Puzzle202110().part2(data);
    }
    private int getPoints(Character closing) {
        switch(closing){
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return -1;
        }
    }
    private Set<Character> OPENING = Set.of('{', '[', '(', '<');
    private Map<Character, Character> bracketMap = new HashMap<>();

    public Puzzle202110() {
        bracketMap.put('}', '{');
        bracketMap.put('>', '<');
        bracketMap.put(']', '[');
        bracketMap.put(')', '(');
    }

    public void part1(String data) {
        int sum = 0;
        String[] d = data.split("\n");
        Stack<Character> brackets = new Stack<>();
        for(String curr: d) {
            for(int i = 0; i < curr.length(); i++){
                char ch = curr.charAt(i);
                if(OPENING.contains(ch)) {
                    brackets.push(ch);
                } else{
                    char matchingOpen = bracketMap.get(ch);
                    if(brackets.peek() == matchingOpen) {
                        brackets.pop();
                    } else {
                        sum += getPoints(ch);
                        break;
                    }
                }
            }
        }
        System.out.println(sum);
    }
    public void part2(String data) {
        int sum = 0;
        String[] d = data.split("\n");
        boolean isBroken = false;
        List<Long> points = new ArrayList<>();
        for(String curr: d) {
            Stack<Character> brackets = new Stack<>();
            for(int i = 0; i < curr.length(); i++){
                char ch = curr.charAt(i);
                if(OPENING.contains(ch)) {
                    brackets.push(ch);
                } else{
                    char matchingOpen = bracketMap.get(ch);
                    if(brackets.peek() == matchingOpen) {
                        brackets.pop();
                    } else {
                        isBroken = true;
                        break;
                    }
                }
            }
            if(!isBroken) {
                long point = 0;
                while(!brackets.isEmpty()){
                    point *=5;
                    char open = brackets.pop();
                    switch (open) {
                        case '(':
                            point += 1;
                            break;
                        case '[':
                            point += 2;
                            break;
                        case '{':
                            point += 3;
                            break;
                        case '<':
                            point += 4;
                            break;
                    }
                }
                points.add(point);
            }
            isBroken = false;
        }
        points = points.stream().sorted().collect(Collectors.toList());
        System.out.println(points.get(points.size()/2));
    }
}
