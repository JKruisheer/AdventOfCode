package day3;

public class EngineMappingCharacter {

    private final Integer rowIndex;
    private final Integer columnIndex;

    private Integer alteredColumnIndex;

    private char value;

    public EngineMappingCharacter(Integer rowIndex, Integer columnIndex, char value) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
        this.alteredColumnIndex = columnIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public Integer getColumnIndexAndDecrease(){
        return --alteredColumnIndex;
    }

    public Integer getColumnIndexAndIncrease(){
        return ++alteredColumnIndex;
    }

    public void resetColumnIndex(){
        this.alteredColumnIndex = columnIndex;
    }

    public Integer getAlteredColumnIndex() {
        return alteredColumnIndex;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
