package bg.tu_varna.sit.f24621604;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<List<Cell>> data = new ArrayList<>();
    private CellParser parser = new CellParser();

    public void clear() {
        data.clear();
    }

    public void addRow(List<Cell> row) {
        data.add(row);
    }

    public List<List<Cell>> getData() {
        return data;
    }

    public void addParsedRow(String line, int rowIdx) {
        String[] parts = line.split(",", -1);
        List<Cell> row = new ArrayList<>();
        for (int colIdx = 0; colIdx < parts.length; colIdx++) {
            row.add(parser.parse(parts[colIdx], rowIdx, colIdx));
        }
        addRow(row);
    }

    public String getCellValue(int row, int col) {
        if (row < 0 || row >= data.size() || col < 0 || col >= data.get(row).size()) {
            return "0";
        }
        return data.get(row).get(col).getValue();
    }

    public void edit(int row, int col, String value) {
        if (row < 0 || row >= data.size()) {
            System.out.println("Invalid row.");
            return;
        }
        if (col < 0 || col >= data.get(row).size()) {
            System.out.println("Invalid column.");
            return;
        }
        try {
            Cell newCell = parser.parse(value, row, col);
            data.get(row).set(col, newCell);
            System.out.println("Cell updated.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private String formatResult(double result) {
       if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }

    public void print() {
        int[] widths = getColumnWidths();
        int cols = widths.length;

        for (List<Cell> row : data) {
            for (int c = 0; c < cols; c++) {
                String text;
                if (c < row.size()) {
                    Cell cell = row.get(c);

                    if (cell instanceof FormulaCell) {
                        try {
                            double result = ((FormulaCell) cell).evaluate(this);
                            text = formatResult(result);
                        } catch (ArithmeticException | IllegalArgumentException e) {
                            text = "ERROR";
                        }
                    }
                    else {
                        text = cell.getValue();
                    }
                }
                else {
                    text = "";
                }

                System.out.print(padRight(text, widths[c]));
                if (c < cols - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }

    private int getMaxColumns() {
        int max = 0;
        for (List<Cell> row : data) {
            if (row.size() > max) max = row.size();
        }
        return max;
    }

    private int[] getColumnWidths() {
        int cols = getMaxColumns();
        int[] widths = new int[cols];
        for (List<Cell> row : data) {
            for (int c = 0; c < row.size(); c++) {
                int len = row.get(c).getValue().length();
                if (len > widths[c]) widths[c] = len;
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
}
