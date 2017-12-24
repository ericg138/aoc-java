package ericgf13.adventofcode.bean;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

	private List<Component> components = new ArrayList<>();

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public int getStrength() {
		int strength = 0;
		for (Component c : components) {
			strength += c.getPort1() + c.getPort2();
		}
		return strength;
	}

	@Override
	public Bridge clone() {
		Bridge bridge = new Bridge();
		bridge.getComponents().addAll(this.components);
		return bridge;
	}

	@Override
	public String toString() {
		return "Bridge [components=" + components + "]";
	}
}
