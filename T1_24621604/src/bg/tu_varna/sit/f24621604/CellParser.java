package bg.tu_varna.sit.f24621604;

public class CellParser {
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
            boolean hasPlus = text.startsWith("+");
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

    private boolean isString(String text) {
        return text.startsWith("\"") && text.endsWith("\"") && text.length() >= 2;
    }

    private boolean isDouble(String text) {
        return isNumeric(text) && text.contains(".");
    }

    private boolean isInt(String text) {
        return isNumeric(text) && !text.contains(".");
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
            if (!Character.isDigit(c) && c != '.') { // Допускаме цифри и '.'
                return false;
            }
        }
        return true;
    }

    public void validateCommas(String line, int rowIdx) {
        new Validator(line, rowIdx).validate();
    }
}
