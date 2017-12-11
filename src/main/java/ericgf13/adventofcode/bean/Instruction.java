package ericgf13.adventofcode.bean;

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

	public void setRegister(String register) {
		this.register = register;
	}

	public boolean isIncrement() {
		return increment;
	}

	public void setIncrement(boolean increment) {
		this.increment = increment;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
