package bg.tu_varna.sit.f24621604.contracts;
/**
 * Represents a mathematical operation with two operands.
 * Implementations define specific operations like addition, subtraction, etc.
 */
public interface Operation {
    /**
     * Performs a calculation on two numbers.
     *
     * @param left the first operand
     * @param right the second operand
     * @return result of the operation
     */
    double calculate(double left, double right);
}
