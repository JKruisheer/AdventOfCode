package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day2Executor {
    public static final String FILE_NAME = "src/day2/input2.txt";

    public static List<CubeStorage> validGames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(FILE_NAME);
        List<String> lines = Files.readAllLines(path);
        for(String line : lines){
            try {
                playGame(line);
            } catch (InvalidGameException e) {
                System.out.println("Invalid man");
            }
        }
        Integer valueOfGoodGames = validGames.stream().mapToInt(CubeStorage::getTotalMultiplication).sum();
        System.out.println(valueOfGoodGames);
    }

    private static void playGame(String line) throws InvalidGameException {
        Integer gameId = findGameIdInLine(line);
        CubeStorage storage = new CubeStorage(gameId, 12, 13, 14);
        String[] gameoptions = findItemOptionsInLine(line);
        for (String gameOption : gameoptions) {
            storage.resetValues(12 , 13, 14);
            String[] games = gameOption.split(",");
                for (String option : games) {
                    if (option.contains("blue")) {
                        Integer value = Integer.valueOf(option.replaceAll("[^\\d.]", ""));
                        storage.removeBlueCube(value);
                    } else if (option.contains("red")) {
                        Integer value = Integer.valueOf(option.replaceAll("[^\\d.]", ""));
                        storage.removeRedCube(value);
                    } else if (option.contains("green")) {
                        Integer value = Integer.valueOf(option.replaceAll("[^\\d.]", ""));
                        storage.removeGreenCube(value);
                    }
                }
        }
        validGames.add(storage);

    }

    private static String[] findItemOptionsInLine(String line) {
        String value = line.substring(line.indexOf(": ") + 1);
        String[] options = value.split(";");
        return options;
    }

    private static Integer findGameIdInLine(String line) {
        String id = line.substring(line.indexOf("Game ") + 5, line.indexOf(":"));
        return Integer.valueOf(id);
    }
}
