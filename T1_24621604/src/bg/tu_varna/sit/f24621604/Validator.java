package bg.tu_varna.sit.f24621604;
/**
 * Validates a CSV line before it is parsed into table cells.
 * Ensures correct comma placement, proper quoting and valid numeric spacing.
 */
public class Validator {
    /**
     * The input line to validate.
     */
    private final String line;
    /**
     * Row index (used for error reporting).
     */
    private final int rowIdx;
    /**
     * Tracks whether the parser is currently inside quotes.
     */
    private boolean inQuotes = false;

    /**
     * Constructs a Validator.
     *
     * @param line the input line
     * @param rowIdx the row index
     */
    public Validator(String line, int rowIdx) {
        this.line = line;
        this.rowIdx = rowIdx;
    }

    /**
     * Validates the entire line.
     * Checks for correct quotes usage, comma placement and spacing rules.
     */
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

    /**
     * Checks if the current character is an unescaped quote.
     *
     * @param index current position
     * @return true if it is an unescaped quote
     */
    private boolean isUnescapedQuote(int index) {
        char c = line.charAt(index);
        return c == '"' && (index == 0 || line.charAt(index - 1) != '\\');
    }

    /**
     * Handles logic when a quote is found.
     *
     * @param index position of the quote
     */
    private void handleQuoteFound(int index) {
        if (!inQuotes) {
            checkCommaBefore(index);
        } else {
            checkCommaAfter(index);
        }
    }

    /**
     * Ensures there is a comma before an opening quote.
     *
     * @param index position of the quote
     */
    private void checkCommaBefore(int index) {
        int j = skipSpacesBackwards(index - 1);
        if (j >= 0 && line.charAt(j) != ',') {
            throwError(j + 1);
        }
    }

    /**
     * Ensures there is a comma before an opening quote.
     *
     * @param index position of the quote
     */
    private void checkCommaAfter(int index) {
        int j = skipSpacesForward(index + 1);
        if (j < line.length() && line.charAt(j) != ',') {
            throwError(index + 1);
        }
    }

    /**
     * Validates that numeric values are not separated by spaces.
     *
     * @param i current position
     */
    private void validateNumericSpacing(int i) {
        char current = line.charAt(i);

        if (i + 1 < line.length() && isNumericChar(current)) {
            int nextIdx = skipSpacesForward(i + 1); // от следващия символ и прескача всички интервали

            if (nextIdx < line.length() && isNumericChar(line.charAt(nextIdx)) && nextIdx > i + 1) {
                throwError(i + 1);
            }
        }
    }

    /**
     * Skips spaces moving forward.
     *
     * @param index starting index
     * @return index of first non-space character
     */
    private int skipSpacesForward(int index) {
        while (index < line.length() && isSpace(line.charAt(index))) {
            index++;
        }
        return index;
    }

    /**
     * Skips spaces moving backward.
     *
     * @param index starting index
     * @return index of first non-space character
     */
    private int skipSpacesBackwards(int index) {
        while (index >= 0 && isSpace(line.charAt(index))) {
            index--;
        }
        return index;
    }

    /**
     * Checks if a character is numeric (digit or decimal point).
     *
     * @param c character to check
     * @return true if numeric
     */
    private boolean isNumericChar(char c) {
        return Character.isDigit(c) || c == '.';
    }

    /**
     * Checks if a character is a whitespace (space or tab).
     *
     * @param c character to check
     * @return true if whitespace
     */
    private boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }

    /**
     * Throws a formatted validation error.
     *
     * @param position error position
     */
    private void throwError(int position) {
        throw new IllegalArgumentException("Error: row " + (rowIdx + 1) + ", missing comma after position " + position);
    }
}
