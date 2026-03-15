package bg.tu_varna.sit.f24621604;

public class FormulaCell implements Cell {
    private String formula;

    public FormulaCell(String formula) {
        this.formula = formula;
    }

    public String getValue() {
        return formula;
    }

    public double evaluate(Table table) {
        return FormulaEvaluator.evaluate(this.formula, table);
    }
}
