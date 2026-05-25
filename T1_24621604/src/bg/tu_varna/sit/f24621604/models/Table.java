package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.contracts.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a table structure that stores data in rows and columns.
 * Supports operations such as loading data, editing cells, and printing.
 */
public class Table {
    /** Stores the table data as a list of column containers */
    private List<Column> data;
    /** Responsible for parsing string values into Cell objects */
    private CellParser parser;
    /** Responsible for printing the table */
    private TablePrinter printer;
    /** Responsible for evaluating formulas */
    private FormulaEvaluator evaluator;

    /**
     * Constructs a Table with required components.
     *
     * @param parser parser used to create cells
     * @param printer printer used for output
     * @param evaluator evaluator used for formulas
     */
    public Table(CellParser parser, TablePrinter printer, FormulaEvaluator evaluator) {
        this.parser = parser;
        this.printer = printer;
        this.evaluator = evaluator;
        this.data = new ArrayList<>();
    }

    /**
     * Returns the table data.
     *
     * @return list of column containers
     */
    public List<Column> getData() {
        return data;
    }

    /**
     * Returns the formula evaluator.
     *
     * @return evaluator instance
     */
    public FormulaEvaluator getEvaluator() {
        return evaluator;
    }

    /**
     * Clears all data in the table.
     */
    public void clear() {
        data.clear();
    }

    /**
     * Adds a new row to the table.
     *
     * @param row list of cells representing a row
     */
    public void addRow(List<Cell> row) {
        data.add(new Column(row));
    }

    /**
     * Parses a CSV line and adds it as a row in the table.
     *
     * @param line the input line
     * @param rowIdx the row index (used for error reporting)
     */
    public void addParsedRow(String line, int rowIdx) {
        parser.validateCommas(line, rowIdx);

        String[] parts = line.split(",", -1); //-1 пази празни клетки в края - ("a,b," → ["a","b",""])
        List<Cell> row = new ArrayList<>();

        for (int colIdx = 0; colIdx < parts.length; colIdx++) {
            row.add(parser.parse(parts[colIdx], rowIdx, colIdx)); //създава подходящ тип Cell и го добавя в реда
        }
        addRow(row); //добавя реда в таблицата
    }

    /**
     * Returns the value of a specific cell.
     *
     * @param row row index
     * @param col column index
     * @return cell value or "0" if invalid
     */
    public String getCellValue(int row, int col) {
        if (!isValidCell(row, col)) {
            return "0";
        }
        return data.get(row).get(col).getValue(); //взима реда->клетката->стойността
    }

    /**
     * Edits a cell at given position.
     *
     * @param row row index
     * @param col column index
     * @param value new value
     */
    public void edit(int row, int col, String value) {
        if (!isValidRow(row)) {
            throw new IllegalArgumentException("Invalid row.");
        }
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column.");
        }
        ensureColumnExists(row, col);

        try {
            Cell newCell = parser.parse(value, row, col);
            data.get(row).set(col, newCell);
            System.out.println("Cell updated.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Prints the table using the printer.
     */
    public void print() {
        printer.print(this);
    }

    /**
     * Finds the maximum number of columns in the table.
     *
     * @return maximum column count
     */
    public int getMaxColumns() {
        int max = 0;
        for (Column row : data) {
            if (row.size() > max) {
                max = row.size();
            }
        }
        return max;
    }

    /**
     * Checks if a row index is valid.
     *
     * @param row row index
     * @return true if valid
     */
    private boolean isValidRow(int row) {
        return row >= 0 && row < data.size();
    }

    /**
     * Checks if a cell position is valid.
     *
     * @param row row index
     * @param col column index
     * @return true if valid
     */
    private boolean isValidCell(int row, int col) {
        return isValidRow(row) && col >= 0 && col < data.get(row).size();
    }

    /**
     * Ensures that a column exists in a given row.
     * If not, adds empty cells until it does.
     *
     * @param row row index
     * @param col column index
     */
    private void ensureColumnExists(int row, int col) {
        data.get(row).ensureCellExists(col);
    }
}