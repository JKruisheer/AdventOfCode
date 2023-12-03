import solutions.day1.Day1Executor;
import solutions.day2.Day2Executor;
import solutions.day3.Day3Executor;

public class runner {
    public static void main(String[] args) {
        switch (3) {
            case 1:
                new Day1Executor().solve();
                break;
            case 2:
                new Day2Executor().solve();
                break;
            case 3:
                new Day3Executor().solve();
                break;
            default:
                throw new RuntimeException("Executor not implemented yet.");
        }
    }
}
