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
            case "202103":
                new Puzzle202103().part1();
                new Puzzle202103().part2();
                break;
            case "202104":
                new Puzzle202104().part1();
                new Puzzle202104().part2();
                break;
            case "202105":
                new Puzzle202105().part1();
                new Puzzle202105().part2();
                break;
            case "202106":
                new Puzzle202106().part1();
                new Puzzle202106().part2();
                break;
            case "202107":
                new Puzzle202107().part1();
                new Puzzle202107().part2();
                break;
            case "202108":
                new Puzzle202108().part1();
                new Puzzle202108().part2();
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
            case "202204":
                new Puzzle202204().part1();
                new Puzzle202204().part2();
                break;
            case "202205":
                new Puzzle202205().part1();
                new Puzzle202205().part2();
                break;
            case "202206":
                new Puzzle202206().part1();
                new Puzzle202206().part2();
                break;
            case "202207":
                new Puzzle202207().part1();
                new Puzzle202207().part2();
                break;
            case "202208":
                new Puzzle202208().part1();
                new Puzzle202208().part2();
                break;
            case "202209":
                new Puzzle202209().part1();
                new Puzzle202209().part2();
                break;
            default:
                System.out.println("Not yet implemented");
                break;
        }
    }
}
