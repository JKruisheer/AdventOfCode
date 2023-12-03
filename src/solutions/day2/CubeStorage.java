package solutions.day2;

public class CubeStorage {

    public final Integer gameId;
    public Integer red;
    public Integer green;
    public Integer blue;

    public Integer highestRed = 1;
    public Integer highestBlue = 1;
    public Integer highestGreen = 1;

    public boolean throwException;

    public CubeStorage(Integer gameId, Integer red, Integer green, Integer blue, boolean throwException) {
        this.gameId = gameId;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.throwException = throwException;
    }

    public void removeRedCube(Integer value) throws InvalidGameException {
        red = red - value;
        if (red < 0 && throwException) {
            throw new InvalidGameException();
        }
        if (value > highestRed) {
            highestRed = value;
        }
    }

    public void removeGreenCube(Integer value) throws InvalidGameException {
        green = green - value;
        if (green < 0 && throwException) {
            throw new InvalidGameException();
        }
        if (value > highestGreen) {
            highestGreen = value;
        }
    }

    public void removeBlueCube(Integer value) throws InvalidGameException {
        blue = blue - value;
        if (blue < 0 && throwException) {
            throw new InvalidGameException();
        }
        if (value > highestBlue) {
            highestBlue = value;
        }
    }

    public Integer getGameId() {
        return gameId;
    }

    public Integer getTotalMultiplication() {
        return highestBlue * highestGreen * highestRed;
    }

    public void resetValues(Integer red, Integer green, Integer blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
