package ericgf13.adventofcode.bean;

public class Component {

	private int port1;
	private int port2;

	public Component(int port1, int port2) {
		this.port1 = port1;
		this.port2 = port2;
	}

	public int getPort1() {
		return port1;
	}

	public void setPort1(int port1) {
		this.port1 = port1;
	}

	public int getPort2() {
		return port2;
	}

	public void setPort2(int port2) {
		this.port2 = port2;
	}

	public int getOtherPort(int i) {
		if (port1 == i) {
			return port2;
		}
		return port1;
	}

	@Override
	public String toString() {
		return port1 + "/" + port2;
	}
}
