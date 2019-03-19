package ericgf13.adventofcode.beans;

public class Instruction {

    private String register;
    private boolean increment;
    private int amount;
    private Condition condition;

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
