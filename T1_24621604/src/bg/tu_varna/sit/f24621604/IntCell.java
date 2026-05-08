package bg.tu_varna.sit.f24621604;
/**
 * Represents a cell containing an integer value.
 */
public class IntCell implements Cell {
    /**
     * The integer value of the cell.
     */
    private int value;
    /**
     * Indicates whether the original input had a leading '+' sign.
     */
    private boolean hasPlus;

    /**
     * Constructs an IntCell.
     *
     * @param value the integer value
     * @param hasPlus whether the value has a leading '+' sign
     */
    public IntCell(int value, boolean hasPlus) {
        this.value = value;
        this.hasPlus = hasPlus;
    }

    /**
     * Returns the string representation of the value.
     * Preserves the '+' sign if it was present in the input.
     *
     * @return formatted string value
     */
    public String getValue() {
        return (hasPlus ? "+" : "") + String.valueOf(value);
    }
}
