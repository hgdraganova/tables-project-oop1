package bg.tu_varna.sit.f24621604;

public class IntCell implements Cell {
    private int value;

    public IntCell(int value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
