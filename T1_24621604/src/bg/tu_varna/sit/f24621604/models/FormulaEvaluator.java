package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.contracts.Operation;
import bg.tu_varna.sit.f24621604.operations.*;

/**
 * Responsible for evaluating formulas in cells.
 * Supports arithmetic operations and cell references (e.g. R1C1).
 */
public class FormulaEvaluator {
    /**
     * Evaluates a formula string by parsing and executing a binary operation.
     * Supports numbers and cell references (e.g. R1C1).
     *
     * @param formula the formula string starting with '='
     * @param table the table used for resolving cell references
     * @return the calculated result as double
     */
    public double evaluate(String formula, Table table) {
        String expression = formula.substring(1).trim();  //Премахваме =

        int operatorIndex = findOperatorIndex(expression); //Намираме индекса на оператора

        if (operatorIndex == -1) { //ако няма оператор
            return getCellValueAsDouble(expression, table); //връщаме стойността
        }

        char operatorChar = expression.charAt(operatorIndex); //взимаме самия оператор

        String leftPart = expression.substring(0, operatorIndex).trim();
        String rightPart = expression.substring(operatorIndex + 1).trim(); //Разделяме израза на 2 части

        double left = getCellValueAsDouble(leftPart, table); //Превръщаме двете части в числа
        double right = getCellValueAsDouble(rightPart, table);

        Operation operation = getOperation(operatorChar ); //взимаме самия оператор

        return operation.calculate(left, right); //връщаме изчисления резултат
    }

    /**
     * Finds the index of the main operator in the expression.
     * Skips operators that are part of a number sign (e.g. "-2").
     *
     * @param expression the formula without '='
     * @return index of the operator or -1 if none found
     */
    private int findOperatorIndex(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i); //Взимаме текущия символ

            if (isOperator(c)) {
                char prev = expression.charAt(i - 1); //Взимаме предишния символ

                if (!isOperator(prev)) {
                    return i; //Връщаме позицията на оператора
                }
            }
        }
        return -1;
    }

    /**
     * Checks whether a character is a supported operator.
     *
     * @param c the character to check
     * @return true if the character is an operator
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * Returns the corresponding Operation implementation
     * based on the given operator symbol.
     *
     * @param c the operator character
     * @return an instance of Operation
     * @throws IllegalArgumentException if operator is unknown
     */
    private Operation getOperation(char c) {
        switch (c) {
            case '+': return new Addition();
            case '-': return new Subtraction();
            case '*': return new Multiplication();
            case '/': return new Division();
            case '^': return new Power();
            default: throw new IllegalArgumentException("Unknown operator");
        }
    }

    /**
     * Converts a string part to a numeric value.
     * Handles both literals and cell references.
     *
     * @param part value or reference (e.g. "5", "R1C1")
     * @param table table for resolving references
     * @return numeric value
     */
    private double getCellValueAsDouble(String part, Table table) {
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
    private int[] parseCoordinates(String ref) {
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
    private double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}