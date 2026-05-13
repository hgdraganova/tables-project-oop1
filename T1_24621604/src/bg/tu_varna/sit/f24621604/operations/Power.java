package bg.tu_varna.sit.f24621604.operations;

import bg.tu_varna.sit.f24621604.contracts.Operation;

/**
 * Represents a power (exponentiation) operation.
 * Implements the Operation interface.
 */
public class Power implements Operation {
    /**
     * Raises a number to a given power.
     *
     * @param a the base
     * @param b the exponent
     * @return result of a raised to the power of b
     */
    public double calculate(double a, double b) {
        return Math.pow(a, b);
    }
}
