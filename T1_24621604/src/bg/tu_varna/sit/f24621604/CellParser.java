package bg.tu_varna.sit.f24621604;

public class CellParser {
    public Cell parse(String text, int row, int col) {
        text = text.trim();

        if (text.startsWith("\"") && text.endsWith("\"") && text.length() >= 2) {
            String content = text.substring(1, text.length() - 1);
            String normalizedContent = content.replace("\\\"", "\"").replace("\\\\", "\\");
            return new StringCell(normalizedContent);
        }

        if (text.startsWith("=")) {
            return new FormulaCell(text);
        }

        if (isNumeric(text)) {
                boolean hasPlus = text.startsWith("+");

                if (text.contains(".")) {
                    if (text.indexOf('.') != text.lastIndexOf('.')) {
                        throw new IllegalArgumentException("Error: row " + (row + 1) + ", col " + (col + 1) + ", " + text + " is unknown data type");
                }
                return new DoubleCell(Double.parseDouble(text), hasPlus);
            }
            else {
                return new IntCell(Integer.parseInt(text), hasPlus);
            }
        }

        if (text.isEmpty()) {
            return new StringCell("");
        }
        throw new IllegalArgumentException("Error: row " + (row + 1) + ", col " + (col + 1) + ", " + text + " is unknown data type");
    }

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
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }
}
