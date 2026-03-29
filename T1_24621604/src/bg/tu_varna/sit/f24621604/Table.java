package bg.tu_varna.sit.f24621604;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<List<Cell>> data = new ArrayList<>();
    private CellParser parser = new CellParser();
    private TablePrinter printer = new TablePrinter();

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
        if (!isValidCell(row, col)) {
            return "0";
        }
        return data.get(row).get(col).getValue();
    }

    public void edit(int row, int col, String value) {
        if (!isValidRow(row)) {
            System.out.println("Invalid row.");
            return;
        }

        if (col < 0) {
            System.out.println("Invalid column.");
            return;
        }

        ensureColumnExists(row, col);

        try {
            Cell newCell = parser.parse(value, row, col);
            data.get(row).set(col, newCell);
            System.out.println("Cell updated.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        printer.print(this);
    }

    public int getMaxColumns() {
        int max = 0;
        for (List<Cell> row : data) {
            if (row.size() > max) max = row.size();
        }
        return max;
    }

    private boolean isValidRow(int row) {
        return row >= 0 && row < data.size();
    }

    private boolean isValidCell(int row, int col) {
        return isValidRow(row) && col >= 0 && col < data.get(row).size();
    }

    private void ensureColumnExists(int row, int col) {
        while (col >= data.get(row).size()) {
            data.get(row).add(new StringCell(""));
        }
    }
}