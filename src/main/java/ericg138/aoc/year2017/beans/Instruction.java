package ericg138.aoc.year2017.beans;

public class Instruction {

    private final String register;
    private final boolean increment;
    private final int amount;
    private final Condition condition;

    public Instruction(String register, boolean increment, int amount, Condition condition) {
        this.register = register;
        this.increment = increment;
        this.amount = amount;
        this.condition = condition;
    }

    public String getRegister() {
        return register;
    }

    public boolean isIncrement() {
        return increment;
    }

    public int getAmount() {
        return amount;
    }

    public Condition getCondition() {
        return condition;
    }
}
