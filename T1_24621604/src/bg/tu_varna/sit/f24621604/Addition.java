package bg.tu_varna.sit.f24621604;
/**
 * Represents an addition operation.
 * Implements the Operation interface.
 */
public class Addition implements Operation {
    /**
     * Calculates the sum of two numbers.
     *
     * @param a first operand
     * @param b second operand
     * @return result of a + b
     */
    public double calculate(double a, double b) {
        return a + b;
    }
}
