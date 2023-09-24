package ericg138.aoc.year2017.beans;

import java.util.HashSet;
import java.util.Set;

public class Disk {

    private final String name;
    private final int weight;
    private final Set<Disk> children = new HashSet<>();
    private int trueWeight;
    private Set<String> childrenStr;
    private Disk parent;

    public Disk(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getTrueWeight() {
        return trueWeight;
    }

    public void setTrueWeight(int trueWeight) {
        this.trueWeight = trueWeight;
    }

    public Set<String> getChildrenStr() {
        return childrenStr;
    }

    public void setChildrenStr(Set<String> childrenStr) {
        this.childrenStr = childrenStr;
    }

    public Set<Disk> getChildren() {
        return children;
    }

    public Disk getParent() {
        return parent;
    }

    public void setParent(Disk parent) {
        this.parent = parent;
    }
}
