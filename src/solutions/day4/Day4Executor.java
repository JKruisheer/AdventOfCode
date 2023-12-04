package solutions.day4;

import solutions.SolutionExecutor;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class Day4Executor extends SolutionExecutor {
    public static final String FILE_NAME = "src/solutions/day4/input4.txt";

    public Day4Executor() {
        super(4);
    }

    @Override
    public void solve() {
        List<String> lines = IOUtils.loadInputByPath(FILE_NAME);
        solvePart1(lines);
        solvePart2(lines);
    }

    private void solvePart1(List<String> lines) {
        Integer totalValue = 0;
        for (String line : lines) {
            ScratchCardGame scratchCardGame = new ScratchCardGame(line);
            totalValue += scratchCardGame.calculateValueOfGame();
        }
        System.out.println(String.format("Solution to part 1: %d ", totalValue));
    }

    private void solvePart2(List<String> lines) {
        List<ScratchCardGame> cards = new ArrayList<>();
        for (String line : lines) {
            cards.add(new ScratchCardGame(line));
        }

        for (int i = 0; i < cards.size(); i++) {
            ScratchCardGame game = cards.get(i);
            for (int v = i + 1; v <= i + game.getWins(); v++) {
                cards.get(v).addCopies(game.getCopies());
            }
        }
        int solutionValue = cards.stream().mapToInt((card) -> card.getCopies()).sum();
        System.out.println(String.format("Solution to part 2: %d ", solutionValue));
    }


}
