package bg.tu_varna.sit.f24621604;

public class FormulaEvaluator {
    public static double evaluate(String formula, Table table) {
        String expression = formula.substring(1);

        if (expression.contains("+")) {
            return calculate(expression, "\\+", table);
        }//+ е специален символ в регулярните изрази
        else if (expression.contains("-")) {
            return calculate(expression, "-", table);
        }
        else if (expression.contains("*")) {
            return calculate(expression, "\\*", table);
        }//* е специален символ в регулярните изрази
        else if (expression.contains("/")) {
            return calculate(expression, "/", table);
        }

        return getCellValueAsDouble(expression, table);
    }

    private static double calculate(String expression, String operator, Table table) {
        String[] parts = expression.split(operator);
        double result = getCellValueAsDouble(parts[0].trim(), table);

        for (int i = 1; i < parts.length; i++) {
            double nextVal = getCellValueAsDouble(parts[i].trim(), table);
            if (operator.equals("\\+")) {
                result += nextVal;
            }
            else if (operator.equals("-")) {
                result -= nextVal;
            }
            else if (operator.equals("\\*")) {
                result *= nextVal;
            }
            else if (operator.equals("/")) {
                if (nextVal == 0) {
                    throw new ArithmeticException("ERROR");
                }
                result /= nextVal;
            }
        }
        return result;
    }

    private static double getCellValueAsDouble(String part, Table table) {
        if (part.startsWith("R")) {
            int[] coords = parseCoordinates(part);
            return toDouble(table.getCellValue(coords[0], coords[1]));
        }
        return toDouble(part);
    }

    private static int[] parseCoordinates(String ref) {
        String[] parts = ref.split("C");

        int row = Integer.parseInt(parts[0].substring(1)) - 1;
        int col = Integer.parseInt(parts[1]) - 1;

        return new int[] {row, col};
    }

    private static double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
