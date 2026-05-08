package bg.tu_varna.sit.f24621604;
/**
 * Represents a cell containing a double (floating-point) value.
 */
public class DoubleCell implements Cell {
    /**
     * The numeric value of the cell.
     */
    private double value;
    /**
     * Indicates whether the original input had a leading '+' sign.
     */
    private boolean hasPlus;

    /**
     * Constructs a DoubleCell.
     *
     * @param value the numeric value
     * @param hasPlus whether the value has a leading '+' sign
     */
    public DoubleCell(double value, boolean hasPlus) {
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
