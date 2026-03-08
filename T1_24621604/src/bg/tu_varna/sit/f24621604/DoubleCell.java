package bg.tu_varna.sit.f24621604;

public class DoubleCell implements Cell {
    private double value;

    public DoubleCell(double value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
