package bg.tu_varna.sit.f24621604;

public class Division implements Operation {
    public double calculate(double a, double b) {
        if (b == 0) throw new ArithmeticException("ERROR");
        return a / b;
    }
}
