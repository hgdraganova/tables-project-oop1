package bg.tu_varna.sit.f24621604;
/**
 * Represents a division operation.
 * Implements the Operation interface.
 */
public class Division implements Operation {
    /**
     * Divides two numbers.
     *
     * @param a dividend
     * @param b divisor
     * @return result of a / b
     * @throws ArithmeticException if division by zero occurs
     */
    public double calculate(double a, double b) {
        if (b == 0) throw new ArithmeticException("ERROR");
        return a / b;
    }
}
