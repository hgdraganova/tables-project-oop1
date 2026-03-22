package bg.tu_varna.sit.f24621604;

import java.util.HashMap;
import java.util.Map;

public class FormulaEvaluator {
    private static final Map<String, Operation> operations = new HashMap<>();

    static {
        operations.put("\\+", new Addition());
        operations.put("-", new Subtraction());
        operations.put("\\*", new Multiplication());
        operations.put("/", new Division());
        operations.put("\\^", new Power());
    }

    public static double evaluate(String formula, Table table) {
        String expression = formula.substring(1).trim();
        String operatorSymbol = getOperatorSymbol(expression);

        if (operatorSymbol == null) {
            return getCellValueAsDouble(expression, table);
        }

        return executeOperation(expression, operatorSymbol, table);
    }

    private static String getOperatorSymbol(String expression) {
        if (expression.contains("+")) return "\\+";
        if (expression.contains("-")) return "-";
        if (expression.contains("*")) return "\\*";
        if (expression.contains("/")) return "/";
        if (expression.contains("^")) return "\\^";
        return null;
    }

    private static double executeOperation(String expression, String symbol, Table table) {
        String[] parts = expression.split(symbol);
        Operation operation = operations.get(symbol);

        double result = getCellValueAsDouble(parts[0].trim(), table);

        for (int i = 1; i < parts.length; i++) {
            double nextVal = getCellValueAsDouble(parts[i].trim(), table);
            result = operation.calculate(result, nextVal);
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