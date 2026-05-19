package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.cells.DoubleCell;
import bg.tu_varna.sit.f24621604.cells.FormulaCell;
import bg.tu_varna.sit.f24621604.cells.IntCell;
import bg.tu_varna.sit.f24621604.cells.StringCell;
import bg.tu_varna.sit.f24621604.contracts.Cell;
import bg.tu_varna.sit.f24621604.validation.Validator;

/**
 * Responsible for parsing text input into specific Cell objects.
 * Determines the type of cell (string, integer, double, formula)
 * based on the input format.
 */
public class CellParser {
    /**
     * Parses a string value and converts it into the appropriate Cell type.
     *
     * @param text the input text
     * @param row the row index (used for error reporting)
     * @param col the column index (used for error reporting)
     * @return a Cell object representing the parsed value
     * @throws IllegalArgumentException if the input is not a valid type
     */
    public Cell parse(String text, int row, int col) {
        text = text.trim();

        if (text.startsWith("=")) {
            return new FormulaCell(text);
        }

        if (isString(text)) {
            String content = text.substring(1, text.length() - 1) //Премахва кавичките
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\"); // \" → " и \\ → \
            return new StringCell(content);
        }

        if (isDouble(text)) {
            if (text.indexOf('.') != text.lastIndexOf('.')) {
                throw new IllegalArgumentException("Error: row " + (row + 1) + ", col " + (col + 1) + ", " + text + " is unknown data type");
            }
            boolean hasPlus = text.startsWith("+"); //Проверка за +
            return new DoubleCell(Double.parseDouble(text), hasPlus);
        }

        if (isInt(text)) {
            boolean hasPlus = text.startsWith("+");
            return new IntCell(Integer.parseInt(text), hasPlus);
        }

        if (text.isEmpty()) {
            return new StringCell("");
        }

        throw new IllegalArgumentException("Error: row " + (row + 1) + ", col " + (col + 1) + ", " + text + " is unknown data type");
    }

    /**
     * Checks if the text represents a string (enclosed in quotes).
     *
     * @param text input text
     * @return true if it is a string
     */
    private boolean isString(String text) {
        return text.startsWith("\"") && text.endsWith("\"") && text.length() >= 2;
    }

    /**
     * Checks if the text represents a double value.
     *
     * @param text input text
     * @return true if it is a double
     */
    private boolean isDouble(String text) {
        return isNumeric(text) && text.contains(".");
    }

    /**
     * Checks if the text represents an integer value.
     *
     * @param text input text
     * @return true if it is an integer
     */
    private boolean isInt(String text) {
        return isNumeric(text) && !text.contains(".");
    }

    /**
     * Checks if the string is numeric (integer or decimal).
     *
     * @param str input string
     * @return true if numeric
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        int startIndex = 0;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            startIndex = 1;
        }
        for (int i = startIndex; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && c != '.') { // Допускаме цифри и '.'
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the comma structure of a CSV line.
     *
     * @param line the input line
     * @param rowIdx the row index (used for error reporting)
     */
    public void validateCommas(String line, int rowIdx) {
        new Validator(line, rowIdx).validate();
    }
}
