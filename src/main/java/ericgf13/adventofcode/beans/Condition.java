package ericgf13.adventofcode.beans;

public class Condition {

    private String register;
    private String comparator;
    private int value;

    public Condition(String register, String comparator, int value) {
        this.register = register;
        this.comparator = comparator;
        this.value = value;
    }

    public String getRegister() {
        return register;
    }

    public String getComparator() {
        return comparator;
    }

    public int getValue() {
        return value;
    }
}
