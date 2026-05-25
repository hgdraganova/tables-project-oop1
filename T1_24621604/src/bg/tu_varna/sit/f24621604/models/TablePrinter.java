package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.cells.FormulaCell;
import bg.tu_varna.sit.f24621604.contracts.Cell;

/**
 * Class responsible for printing a table in a formatted way.
 * It aligns columns based on the longest cell in each column
 * and evaluates formulas when needed.
 */
public class TablePrinter {
    /**
     * Prints the table to the console with proper formatting.
     *
     * @param table the table to be printed
     */

    public void print(Table table) {
        int[] widths = getColumnWidths(table);
        int cols = widths.length;

        for (Column row : table.getData()) {
            for (int c = 0; c < cols; c++) {
                String text = resolveCellText(table, row, c);

                System.out.print(padRight(text, widths[c]));
                if (c < cols - 1) { //проверка за последна колона
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Formats a numeric result.
     * If the number is whole, removes the decimal part.
     *
     * @param result the number to format
     * @return formatted string representation
     */
    private String formatResult(double result) {
        if (Math.floor(result) == result) { //дали числото е цяло
            return String.valueOf((long) result); //махаме .0 -> цяло число(long няма дробна част)
        } else {
            return String.valueOf(result);
        }
    }

    private int[] getColumnWidths(Table table) {
        int cols = table.getMaxColumns();
        int[] widths = new int[cols];

        for (Column row : table.getData()) {
            for (int c = 0; c < cols; c++) {
                String text = resolveCellText(table, row, c); //взима текста
                int len = text.length();

                if (len > widths[c]) {
                    widths[c] = len; //сравнява, за да вземе най-голямата дължина за колоната
                }
            }
        }
        return widths; //връща масив от стойностите на най-големите дължини за всяка колона
    } //всяка колона се подравнява според най-дългата клетка в нея

    /**
     * Pads a string with spaces on the right until it reaches the given width.
     *
     * @param text the original text
     * @param width the desired width
     * @return padded string
     */
    private String padRight(String text, int width) {
        StringBuilder sb = new StringBuilder(text); //започва с text
        while (sb.length() < width) { //докато текстът е по-къс от нужната ширина
            sb.append(' ');
        }
        return sb.toString();
    } //разширява текста с интервали отдясно, докато стане с дадената дължина

    /**
     * Resolves the text representation of a cell.
     * If the cell is a formula, it is evaluated.
     * If evaluation fails, returns "ERROR".
     *
     * @param table the table
     * @param row the current row
     * @param col the column index
     * @return string representation of the cell
     */
    private String resolveCellText(Table table, Column row, int col) {
        if (col >= row.size()) {
            return "";
        }
        Cell cell = row.get(col); //взима конкретната клетка

        if (cell instanceof FormulaCell) {
            try {
                double result = ((FormulaCell) cell).evaluate(table); //изчислява стойността
                return formatResult(result); //форматира резултата
            } catch (ArithmeticException | IllegalArgumentException e) {
                return "ERROR";
            }
        }
        return cell.getValue(); //ако не е формула, връща директно текста
    } //връща какво да се покаже в клетката(стойност или резултат от формула)
}