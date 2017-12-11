package ericgf13.adventofcode.bean;

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

	public void setRegister(String register) {
		this.register = register;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
