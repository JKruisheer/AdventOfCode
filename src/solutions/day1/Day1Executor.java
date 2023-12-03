package solutions.day1;

import solutions.SolutionExecutor;
import utils.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1Executor extends SolutionExecutor {
    public static final String FILE_PATH = "src/solutions/day1/input1.txt";

    public Day1Executor() {
        super(1);
    }

    @Override
    public void solve() {
        List<String> lines = IOUtils.loadInputByPath(FILE_PATH);
        solvePart1(lines);
        solvePart2(lines);
    }

    private void solvePart1(List<String> lines) {
        List<String> linesWithoutCharacters = lines
                .stream()
                .map((value) -> value.replaceAll("[^\\d.]", ""))
                .toList();

        System.out.println(String.format("The solution for part 1: %d", calculateSumForLines(linesWithoutCharacters)));
    }

    private void solvePart2(List<String> lines) {
        List<String> linesWithoutCharacters = lines
                .stream()
                .map(Day1Executor::valuesToNumbers)
                .map((value) -> value.replaceAll("[^\\d.]", ""))
                .toList();
        System.out.println(String.format("The solution for part 2: %d", calculateSumForLines(linesWithoutCharacters)));
    }

    private Integer calculateSumForLines(List<String> linesWithoutCharacters) {
        int sumOfTheNumbers = 0;
        for (String number : linesWithoutCharacters) {
            if (number.length() == 1) {
                sumOfTheNumbers += Integer.parseInt(number + number);
            } else {
                String firstValue = String.valueOf(number.charAt(0));
                String secondValue = String.valueOf(number.charAt(number.length() - 1));
                sumOfTheNumbers += Integer.parseInt(firstValue + secondValue);
            }
        }
        return sumOfTheNumbers;
    }

    private static String valuesToNumbers(String value) {
        Map<String, String> replacements = new HashMap<>();
        replacements.put("oneight", "18");
        replacements.put("twone", "21");
        replacements.put("eightwo", "82");
        replacements.put("one", "1");
        replacements.put("two", "2");
        replacements.put("three", "3");
        replacements.put("four", "4");
        replacements.put("five", "5");
        replacements.put("six", "6");
        replacements.put("seven", "7");
        replacements.put("eight", "8");
        replacements.put("nine", "9");

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            value = value.replace(entry.getKey(), entry.getValue());
        }

        return value;
    }
}
