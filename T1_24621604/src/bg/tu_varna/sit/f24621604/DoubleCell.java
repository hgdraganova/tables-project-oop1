package bg.tu_varna.sit.f24621604;

public class DoubleCell implements Cell {
    private double value;
    private boolean hasPlus;

    public DoubleCell(double value, boolean hasPlus) {
        this.value = value;
        this.hasPlus = hasPlus;
    }

    public String getValue() {
        return (hasPlus ? "+" : "") + String.valueOf(value);
    }
}
