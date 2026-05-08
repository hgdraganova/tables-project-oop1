package bg.tu_varna.sit.f24621604;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for evaluating formulas in cells.
 * Supports arithmetic operations and cell references (e.g. R1C1).
 */
public class FormulaEvaluator {
    /**
     * Stores supported operations mapped by their symbols.
     */
    private static final Map<String, Operation> operations = new HashMap<>();

    /**
     * Initializes all supported operations.
     */
    public FormulaEvaluator() {
        operations.put("\\+", new Addition());
        operations.put("-", new Subtraction());
        operations.put("\\*", new Multiplication());
        operations.put("/", new Division());
        operations.put("\\^", new Power());
    }

    /**
     * Evaluates a formula string.
     * Removes the leading '=' and determines if it is a simple value or expression.
     *
     * @param formula the formula (e.g. "=2+3", "=R1C1")
     * @param table the table used for resolving references
     * @return the calculated result
     */
    public double evaluate(String formula, Table table) {
        String expression = formula.substring(1).trim(); //Премахваме =
        String operatorSymbol = getOperatorSymbol(expression);

        // If there is no operator, return the value directly
        if (operatorSymbol == null) {
            return getCellValueAsDouble(expression, table);
        }
        return executeOperation(expression, operatorSymbol, table);
    }

    /**
     * Detects the operator used in the expression.
     *
     * @param expression the formula without '='
     * @return operator symbol (regex-safe) or null if none exists
     */
    private static String getOperatorSymbol(String expression) {
        if (expression.contains("+")) return "\\+";
        if (expression.contains("-")) return "-";
        if (expression.contains("*")) return "\\*";
        if (expression.contains("/")) return "/";
        if (expression.contains("^")) return "\\^";
        return null;
    }

    /**
     * Executes the operation over all parts of the expression.
     * Supports chained operations (e.g. 2+3+4).
     *
     * @param expression the full expression
     * @param symbol operator symbol
     * @param table table for resolving references
     * @return result of calculation
     */
    private static double executeOperation(String expression, String symbol, Table table) {
        String[] parts = expression.split(symbol);
        Operation operation = operations.get(symbol);

        double result = getCellValueAsDouble(parts[0].trim(), table); //Взимаме първата част от масива и с getCellValueAsDouble проверяваме дали е референция или число и я връщаме като число

        for (int i = 1; i < parts.length; i++) {
            double nextVal = getCellValueAsDouble(parts[i].trim(), table); //Взимаме следващата част от масива и с getCellValueAsDouble проверяваме дали е референция или число и я връщаме като число
            result = operation.calculate(result, nextVal); //Пресмятаме израза
        }
        return result;
    }

    /**
     * Converts a string part to a numeric value.
     * Handles both literals and cell references.
     *
     * @param part value or reference (e.g. "5", "R1C1")
     * @param table table for resolving references
     * @return numeric value
     */
    private static double getCellValueAsDouble(String part, Table table) {
        if (part.startsWith("R")) {
            int[] coords = parseCoordinates(part);
            return toDouble(table.getCellValue(coords[0], coords[1]));
        }
        return toDouble(part);
    }

    /**
     * Parses a cell reference in format R<row>C<col>.
     *
     * @param ref reference string (e.g. "R1C2")
     * @return array with row and column indexes (0-based)
     */
    private static int[] parseCoordinates(String ref) {
        String[] parts = ref.split("C"); //Разделяме низа по символа 'C' (например "R5C10" става ["R5", "10"])
        int row = Integer.parseInt(parts[0].substring(1)) - 1; //Взимаме "R5", премахваме първия символ 'R' чрез substring(1), превръщаме "5" в число и вадим 1
        int col = Integer.parseInt(parts[1]) - 1; //Взимаме втората част "10", превръщаме я в число и вадим 1
        //-1 - превръщаме потребителския номер (започващ от 1) в програмен индекс (започващ от 0)

        int[] coordinates = new int[2];
        coordinates[0] = row;
        coordinates[1] = col;
        return coordinates;
    }

    /**
     * Converts string to double.
     * Returns 0.0 if conversion fails (as per requirements).
     *
     * @param value string value
     * @return parsed double or 0.0 if invalid
     */
    private static double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}