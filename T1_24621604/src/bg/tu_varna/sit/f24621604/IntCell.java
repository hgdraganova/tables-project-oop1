package bg.tu_varna.sit.f24621604;

public class IntCell implements Cell {
    private int value;
    private boolean hasPlus;

    public IntCell(int value, boolean hasPlus) {
        this.value = value;
        this.hasPlus = hasPlus;
    }

    public String getValue() {
        return (hasPlus ? "+" : "") + String.valueOf(value);
    }
}
