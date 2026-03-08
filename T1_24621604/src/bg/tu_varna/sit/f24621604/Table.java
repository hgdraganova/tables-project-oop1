package bg.tu_varna.sit.f24621604;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<List<Cell>> data = new ArrayList<>();

    public void clear() {
        data.clear();
    }

    public void addRow(List<Cell> row) {
        data.add(row);
    }

    public List<List<Cell>> getData() {
        return data;
    }

    public void print() {
        int[] widths = getColumnWidths();
        int cols = widths.length;

        for (List<Cell> row : data) {
            for (int c = 0; c < cols; c++) {
                String text = "";

                if (c < row.size()) {
                    text = row.get(c).getValue();
                }

                System.out.print(padRight(text, widths[c]));
                System.out.print(" | ");
            }
            System.out.println();
        }
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

        data.get(row).set(col, createCell(value));
        System.out.println("Cell updated.");
    }

    public Cell createCell(String text) {
        text = text.replace("\\\"", "\"");

        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            text = text.substring(1, text.length() - 1);
            return new StringCell(text);
        }

        try {
            return new IntCell(Integer.parseInt(text));
        } catch (NumberFormatException ignored) {}

        try {
            return new DoubleCell(Double.parseDouble(text));
        } catch (NumberFormatException ignored) {}

        if (text.startsWith("=")) {
            return new FormulaCell(text);
        }

        return new StringCell(text);
    }

    private int getMaxColumns() {
        int max = 0;
        for (List<Cell> row : data) {
            if (row.size() > max) {
                max = row.size();
            }
        }
        return max;
    }

    private int[] getColumnWidths() {
        int cols = getMaxColumns();
        int[] widths = new int[cols];

        for (List<Cell> row : data) {
            for (int c = 0; c < row.size(); c++) {
                int len = row.get(c).getValue().length();
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
}
