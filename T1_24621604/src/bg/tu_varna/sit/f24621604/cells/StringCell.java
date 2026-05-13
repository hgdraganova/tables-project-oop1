package bg.tu_varna.sit.f24621604.cells;

import bg.tu_varna.sit.f24621604.contracts.Cell;

/**
 * Represents a cell containing a string value.
 */
public class StringCell implements Cell {
    /**
     * The text value of the cell.
     */
    private String value;

    /**
     * Constructs a StringCell.
     *
     * @param value the string content of the cell
     */
    public StringCell(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the cell.
     *
     * @return the stored string value
     */
    public String getValue() {
        return value;
    }
}
