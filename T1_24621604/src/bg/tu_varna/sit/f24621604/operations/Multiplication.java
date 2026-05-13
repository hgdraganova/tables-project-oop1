package bg.tu_varna.sit.f24621604.operations;

import bg.tu_varna.sit.f24621604.contracts.Operation;

/**
 * Represents a multiplication operation.
 * Implements the Operation interface.
 */
public class Multiplication implements Operation {
    /**
     * Multiplies two numbers.
     *
     * @param a first operand
     * @param b second operand
     * @return result of a * b
     */
    public double calculate(double a, double b) {
        return a * b;
    }
}
