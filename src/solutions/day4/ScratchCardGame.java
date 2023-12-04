package solutions.day4;

import java.util.ArrayList;
import java.util.List;

public class ScratchCardGame {

    private List<Integer> winningNumbers;

    private List<Integer> myNumbers = new ArrayList<>();

    private Integer totalAmountOfPoints = 0;

    private int copies = 1;

    int wins = 0;

    public ScratchCardGame(String gameLine) {
        String[] gameSeparation = gameLine.split(":");

        String[] cardDecks = gameSeparation[1].split("\\|");
        this.winningNumbers = initializeNumbers(cardDecks[0]);
        this.myNumbers = initializeNumbers(cardDecks[1]);

        for (Integer myNumber : myNumbers) {
            if (winningNumbers.contains(myNumber)) {
                wins++;
            }
        }

    }

    public Integer calculateValueOfGame() {
        for (Integer myNumber : myNumbers) {
            if (winningNumbers.contains(myNumber)) {
                if (totalAmountOfPoints == 0) {
                    totalAmountOfPoints++;
                } else {
                    totalAmountOfPoints *= 2;
                }
            }
        }
        return totalAmountOfPoints;
    }

    private List<Integer> initializeNumbers(String cardDeck) {
        String[] numberArray = cardDeck.split("\\s+");
        List<Integer> integerList = new ArrayList<>();
        for (String num : numberArray) {
            if (!num.isEmpty()) {
                integerList.add(Integer.parseInt(num));
            }
        }
        return integerList;
    }

    public int getWins() {
        return wins;
    }

    public void addCopies(int copies) {
        this.copies += copies;
    }

    public int getCopies() {
        return copies;
    }
}
