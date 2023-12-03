package day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Executor {
    public static final String FILE_NAME = "src/day1/input.txt";

    public static Pattern pattern = Pattern.compile("\\d+");


    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))){

            List<String> numbers = lines
                    .map(Day1Executor::valuesToNumbers)
                    .map((value) -> value.replaceAll("[^\\d.]", "")).toList();
            int totalSum = 0;
            for(String number : numbers){
                if(number.length() == 1){
                    number = number + number;
                    totalSum += Integer.parseInt(number);
                    System.out.println(number + " and we made it: " + Integer.parseInt(number));
                    continue;
                }
                String firstValue = String.valueOf(number.charAt(0));
                String secondValue = String.valueOf(number.charAt(number.length() - 1));
                String value = firstValue + secondValue;
                totalSum += Integer.parseInt(value);
                System.out.println(number + " and we made it: " + Integer.parseInt(value));
            }
            System.out.println(totalSum);
        }
    }

    private static String valuesToNumbers(String value) {
        System.out.println("value was" + value);
        value = value.replaceAll("oneight", "18");
        value = value.replaceAll("twone", "21");
        value = value.replaceAll("eightwo", "82");
        value = value.replaceAll("one", "1");
        value = value.replaceAll("two", "2");
        value = value.replaceAll("three", "3");
        value = value.replaceAll("four", "4");
        value = value.replaceAll("five", "5");
        value = value.replaceAll("six", "6");
        value = value.replaceAll("seven", "7");
        value = value.replaceAll("eight", "8");
        value = value.replaceAll("nine", "9");
        return value;
    }
}
