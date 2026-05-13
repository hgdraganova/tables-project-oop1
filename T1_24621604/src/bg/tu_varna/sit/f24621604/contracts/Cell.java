package bg.tu_varna.sit.f24621604.contracts;
/**
 * Represents a cell in the table.
 * All cell types (e.g. text, number, formula) must implement this interface.
 */
public interface Cell {
    /**
     * Returns the string representation of the cell's value.
     *
     * @return value of the cell as a String
     */
    String getValue();
}
