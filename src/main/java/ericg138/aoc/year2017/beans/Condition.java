package ericg138.aoc.year2017.beans;

public class Condition {

    private final String register;
    private final String comparator;
    private final int value;

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
