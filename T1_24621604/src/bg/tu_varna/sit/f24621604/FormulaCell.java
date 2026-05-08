package bg.tu_varna.sit.f24621604;
/**
 * Represents a cell containing a formula.
 * The formula is stored as text and can be evaluated dynamically.
 */
public class FormulaCell implements Cell {
    /**
     * The formula string (e.g. "=A1+B2" or "=2+3").
     */
    private String formula;

    /**
     * Constructs a FormulaCell.
     *
     * @param formula the formula text
     */
    public FormulaCell(String formula) {
        this.formula = formula;
    }

    /**
     * Returns the formula as it was entered (without evaluation).
     *
     * @return formula string
     */
    public String getValue() {
        return formula;
    }

    /**
     * Evaluates the formula using the table's evaluator.
     *
     * @param table the table used for resolving references
     * @return the calculated result of the formula
     */
    public double evaluate(Table table) {
        return table.getEvaluator().evaluate(this.formula, table);
    }
}
