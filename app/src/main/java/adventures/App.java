package adventures;

public class App {
    public static void main(String[] args) {
        String runP = args[0];
        // String runP = "202201";
        switch (runP) {
            case "202101":
                new Puzzle202101().part1();
                new Puzzle202101().part2();
                break;
            case "202102":
                new Puzzle202102().part1();
                new Puzzle202102().part2();
                break;
            case "202201":
                new Puzzle202201().part1();
                new Puzzle202201().part2();
                break;
            case "202202":
                new Puzzle202202().part1();
                new Puzzle202202().part2();
                break;
            case "202203":
                new Puzzle202203().part1();
                new Puzzle202203().part2();
                break;
            default:
                System.out.println("Not yet implemented");
                break;
        }
    }
}
