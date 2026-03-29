package bg.tu_varna.sit.f24621604;

import java.util.List;

public class TablePrinter {
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }

    private int[] getColumnWidths(Table table) {
        int cols = table.getMaxColumns();
        int[] widths = new int[cols];

        for (List<Cell> row : table.getData()) {
            for (int c = 0; c < cols; c++) {
                String text = resolveCellText(table, row, c);
                int len = text.length();

                if (len > widths[c]) {
                    widths[c] = len;
                }
            }
        }
        return widths;
    }

    private String padRight(String text, int width) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < width) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public void print(Table table) {
        int[] widths = getColumnWidths(table);
        int cols = widths.length;

        for (List<Cell> row : table.getData()) {
            for (int c = 0; c < cols; c++) {
                String text = resolveCellText(table, row, c);

                System.out.print(padRight(text, widths[c]));
                if (c < cols - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    private String resolveCellText(Table table, List<Cell> row, int col) {
        if (col >= row.size()) {
            return "";
        }
        Cell cell = row.get(col);

        if (cell instanceof FormulaCell) {
            try {
                double result = ((FormulaCell) cell).evaluate(table);
                return formatResult(result);
            } catch (ArithmeticException | IllegalArgumentException e) {
                return "ERROR";
            }
        }
        return cell.getValue();
    }
}