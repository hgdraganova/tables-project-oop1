package bg.tu_varna.sit.f24621604;

public class Validator {
    private final String line;
    private final int rowIdx;
    private boolean inQuotes = false;

    public Validator(String line, int rowIdx) {
        this.line = line;
        this.rowIdx = rowIdx;
    }

    public void validate() {
        for (int i = 0; i < line.length(); i++) {
            if (isUnescapedQuote(i)) {
                handleQuoteFound(i);
                inQuotes = !inQuotes;
            } else if (!inQuotes) {
                validateNumericSpacing(i);
            }
        }
    }

    private boolean isUnescapedQuote(int index) {
        char c = line.charAt(index);
        return c == '"' && (index == 0 || line.charAt(index - 1) != '\\');
    }

    private void handleQuoteFound(int index) {
        if (!inQuotes) {
            checkCommaBefore(index);
        } else {
            checkCommaAfter(index);
        }
    }

    private void checkCommaBefore(int index) {
        int j = skipSpacesBackwards(index - 1);
        if (j >= 0 && line.charAt(j) != ',') {
            throwError(j + 1);
        }
    }

    private void checkCommaAfter(int index) {
        int j = skipSpacesForward(index + 1);
        if (j < line.length() && line.charAt(j) != ',') {
            throwError(index + 1);
        }
    }

    private void validateNumericSpacing(int i) {
        char current = line.charAt(i);

        if (i + 1 < line.length() && isNumericChar(current)) {
            int nextIdx = skipSpacesForward(i + 1); // от следващия символ и прескача всички интервали

            if (nextIdx < line.length() && isNumericChar(line.charAt(nextIdx)) && nextIdx > i + 1) {
                throwError(i + 1);
            }
        }
    }

    private int skipSpacesForward(int index) {
        while (index < line.length() && isSpace(line.charAt(index))) {
            index++;
        }
        return index;
    }

    private int skipSpacesBackwards(int index) {
        while (index >= 0 && isSpace(line.charAt(index))) {
            index--;
        }
        return index;
    }

    private boolean isNumericChar(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }

    private void throwError(int position) {
        throw new IllegalArgumentException("Error: row " + (rowIdx + 1) + ", missing comma after position " + position);
    }
}
