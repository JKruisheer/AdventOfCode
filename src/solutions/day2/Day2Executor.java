package solutions.day2;

import solutions.SolutionExecutor;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class Day2Executor extends SolutionExecutor {
    public static final String FILE_PATH = "src/solutions/day2/input2.txt";

    public static List<CubeStorage> validGames = new ArrayList<>();

    public Day2Executor() {
        super(2);
    }

    @Override
    public void solve() {
        List<String> lines = IOUtils.loadInputByPath(FILE_PATH);

        solvePart1(lines);
        solvePart2(lines);
    }

    private void solvePart1(List<String> lines) {
        validGames.clear();
        for (String line : lines) {
            try {
                Integer gameId = findGameIdInLine(line);
                CubeStorage storage = new CubeStorage(gameId, 12, 13, 14, true);
                playGame(line, storage);
            } catch (InvalidGameException e) {
                //nothing, could make a fancy log and add a logger here in the class, but ey...
            }
        }
        Integer gameIdValues = validGames.stream().mapToInt(CubeStorage::getGameId).sum();
        System.out.println(String.format("Solution to part 1: %d ", gameIdValues));
    }

    private void solvePart2(List<String> lines) {
        validGames.clear();
        for (String line : lines) {
            try {
                Integer gameId = findGameIdInLine(line);
                CubeStorage storage = new CubeStorage(gameId, 12, 13, 14, false);
                playGame(line, storage);
            } catch (InvalidGameException e) {
                System.out.println("Invalid game exception.");
            }
        }
        Integer valueOfGoodGames = validGames.stream().mapToInt(CubeStorage::getTotalMultiplication).sum();
        System.out.println(String.format("Solution to part 2: %d ", valueOfGoodGames));
    }


    private static void playGame(String line, CubeStorage storage) throws InvalidGameException {
        String[] gameoptions = findItemOptionsInLine(line);
        for (String gameOption : gameoptions) {
            storage.resetValues(12, 13, 14);
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
