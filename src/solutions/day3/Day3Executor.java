package solutions.day3;

import solutions.SolutionExecutor;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3Executor extends SolutionExecutor {
    public static final String FILE_NAME = "src/solutions/day3/input3.txt";
    public static final String SPECIAL_CHARS = "*&/@=+#$%-";
    public static final String SPECIAL_CHARS_WITH_DOT = SPECIAL_CHARS + ".";
    public static final String NUMBERS = "0123456789";
    public static final int[] ROW_OFFSETS = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static final int[] COL_OFFSETS = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static char[][] board;

    public Day3Executor() {
        super(3);
    }

    @Override
    public void solve() {
        List<String> lines = IOUtils.loadInputByPath(FILE_NAME);
        board = createBoard(lines);
        solveBoardPart1();
        solveBoardPart2();
    }


    private void solveBoardPart2() {
        Integer totalValue = 0;
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            char[] arr = board[rowIndex];
            for (int columnIndex = 0; columnIndex < arr.length; columnIndex++) {
                char n = arr[columnIndex];
                if ('*' == n) {
                    totalValue += findEngineSymbols(rowIndex, columnIndex);
                }
            }
        }
        System.out.println(String.format("Solution to part 2: %d ", totalValue));
    }

    private Integer findEngineSymbols(int rowIndex, int columnIndex) {
        //find 2 engine symbols, if that is the case return those.
        List<EngineMappingCharacter> possibleSymbols = new ArrayList<>();

        for (int i = 0; i < ROW_OFFSETS.length; i++) {
            int newRow = rowIndex + ROW_OFFSETS[i];
            int newCol = columnIndex + COL_OFFSETS[i];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (NUMBERS.indexOf(board[newRow][newCol]) != -1) {
                    possibleSymbols.add(new EngineMappingCharacter(newRow, newCol, board[newRow][newCol]));
                }
            }
        }

        List<String> matches = new ArrayList<>();
        for (EngineMappingCharacter sy : possibleSymbols) {
            matches.add(createSymbol(board, sy));
        }

        List<String> distinctMatches = matches.stream().distinct().toList();
        if (distinctMatches.size() == 2) {
            return Integer.parseInt(distinctMatches.get(0)) * Integer.parseInt(distinctMatches.get(1));
        }
        return 0;
    }


    private String createSymbol(char[][] board, EngineMappingCharacter sy) {

        LinkedList<String> linkedListOfString = new LinkedList<>();
        linkedListOfString.add(String.valueOf(sy.getValue()));

        while (sy.getColumnIndexAndDecrease() >= 0 && SPECIAL_CHARS_WITH_DOT.indexOf(board[sy.getRowIndex()][sy.getAlteredColumnIndex()]) == -1) {
            linkedListOfString.addFirst(String.valueOf(board[sy.getRowIndex()][sy.getAlteredColumnIndex()]));
        }

        sy.resetColumnIndex();

        while (sy.getColumnIndexAndIncrease() < board[0].length && SPECIAL_CHARS_WITH_DOT.indexOf(board[sy.getRowIndex()][sy.getAlteredColumnIndex()]) == -1) {
            linkedListOfString.addLast(String.valueOf(board[sy.getRowIndex()][sy.getAlteredColumnIndex()]));
        }
        return String.join("", linkedListOfString);
    }

    private void solveBoardPart1() {
        Integer totalValue = 0;
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            char[] arr = board[rowIndex];
            List<EngineMappingCharacter> symbols = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < arr.length; columnIndex++) {
                char n = arr[columnIndex];
                if (Character.isDigit(n)) {
                    symbols.add(new EngineMappingCharacter(rowIndex, columnIndex, n));
                } else if (n == '.' || SPECIAL_CHARS.indexOf(n) != -1) {
                    if (!symbols.isEmpty()) {
                        Integer value = isNumberValid(symbols);
                        totalValue += value;
                        symbols.clear();
                    }
                }
                if (columnIndex == arr.length - 1 && !symbols.isEmpty()) {
                    Integer value = isNumberValid(symbols);
                    totalValue += value;
                }
            }
        }
        System.out.println(String.format("Solution to part 1: %d ", totalValue));
    }

    private Integer isNumberValid(List<EngineMappingCharacter> symbols) {
        for (EngineMappingCharacter symbol : symbols) {
            if (hasSymbolAround(symbol.getRowIndex(), symbol.getColumnIndex(), SPECIAL_CHARS)) {
                String result = symbols.stream()
                        .map(EngineMappingCharacter::getValue) // Extract char from each object
                        .map(String::valueOf) // Convert each char to a String
                        .collect(Collectors.joining());
                return Integer.valueOf(result);
            }
        }
        return 0;
    }

    public boolean hasSymbolAround(int rowIndex, int colIndex, String whichSymbol) {
        for (int i = 0; i < ROW_OFFSETS.length; i++) {
            int newRow = rowIndex + ROW_OFFSETS[i];
            int newCol = colIndex + COL_OFFSETS[i];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (whichSymbol.indexOf(board[newRow][newCol]) != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    private char[][] createBoard(List<String> lines) {
        char[][] board = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            String value = lines.get(i);
            for (int j = 0; j < value.length(); j++) {
                board[i][j] = value.charAt(j);
            }
        }
        return board;
    }
}
