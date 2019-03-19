package ericgf13.adventofcode.beans;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private List<Component> components = new ArrayList<>();

    public Bridge() {
    }

    public Bridge(Bridge source) {
        this.getComponents().addAll(source.components);
    }

    public List<Component> getComponents() {
        return components;
    }

    public int getStrength() {
        int strength = 0;
        for (Component c : components) {
            strength += c.getPort1() + c.getPort2();
        }
        return strength;
    }

    @Override
    public String toString() {
        return "Bridge [components=" + components + "]";
    }
}
