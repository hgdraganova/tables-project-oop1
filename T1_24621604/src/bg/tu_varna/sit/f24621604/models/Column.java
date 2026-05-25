package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.cells.StringCell;
import bg.tu_varna.sit.f24621604.contracts.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a row/column container of cells in the table model.
 * Encapsulates all operations over the internal cell list.
 */
public class Column {
    /** Internal list storing the cells */
    private final List<Cell> cells;

    /**
     * Constructs an empty column.
     */
    public Column() {
        this.cells = new ArrayList<>();
    }

    /**
     * Constructs a column with an existing list of cells.
     *
     * @param cells list of cells to initialize the column with
     */
    public Column(List<Cell> cells) {
        this.cells = new ArrayList<>(cells);
    }

    /**
     * Returns the number of cells in the column.
     *
     * @return size of the column
     */
    public int size() {
        return cells.size();
    }

    /**
     * Returns the cell at the specified index.
     *
     * @param index position of the cell
     * @return the cell at the given index
     */
    public Cell get(int index) {
        return cells.get(index);
    }

    /**
     * Replaces the cell at the specified index.
     *
     * @param index position of the cell
     * @param cell new cell value
     */
    public void set(int index, Cell cell) {
        cells.set(index, cell);
    }

    /**
     * Adds a new cell at the end of the column.
     *
     * @param cell cell to be added
     */
    public void add(Cell cell) {
        cells.add(cell);
    }

    /**
     * Returns the list of cells.
     *
     * @return list of cells
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Ensures that a cell exists at the given index.
     * If the index is outside the current size, empty cells are added.
     *
     * @param index index that must be accessible
     */
    public void ensureCellExists(int index) {
        while (index >= cells.size()) {
            cells.add(new StringCell(""));
        }
    }
}
