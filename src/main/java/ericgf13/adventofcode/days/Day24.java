package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;
import ericgf13.adventofcode.beans.Bridge;
import ericgf13.adventofcode.beans.Component;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day24 extends Day {

    private int maxStength;
    private int strengthLongest;

    public Day24() {
        super(24);

        Set<Component> components = getInputAsList().stream()
                .map(line -> new Component(Integer.parseInt(line.substring(0, line.indexOf('/'))), Integer.parseInt(line.substring(line.indexOf('/') + 1)))).collect(Collectors.toSet());

        Set<Bridge> bridges = new HashSet<>();

        components.stream().filter(c -> c.getPort1() == 0 || c.getPort2() == 0).forEach(c -> {
            Bridge b = new Bridge();
            b.getComponents().add(c);
            buildBridges(c.getOtherPort(0), components, b, bridges);
        });

        Set<Bridge> longestBridges = new HashSet<>();

        int maxLength = 0;
        for (Bridge b : bridges) {
            if (b.getComponents().size() == maxLength) {
                longestBridges.add(b);
            } else if (b.getComponents().size() > maxLength) {
                longestBridges.clear();
                longestBridges.add(b);
                maxLength = b.getComponents().size();
            }
        }

        maxStength = bridges.stream().max(Comparator.comparing(Bridge::getStrength)).get().getStrength();
        strengthLongest = longestBridges.stream().max(Comparator.comparing(Bridge::getStrength)).get().getStrength();
    }

    private void buildBridges(int port, Set<Component> components, Bridge bridge, Set<Bridge> bridges) {
        Set<Component> componentsCompatiblePort = components.stream()
                .filter(c -> !bridge.getComponents().contains(c) && (c.getPort1() == port || c.getPort2() == port))
                .collect(Collectors.toSet());

        if (componentsCompatiblePort.isEmpty()) {
            bridges.add(bridge);
        } else {
            for (Component c : componentsCompatiblePort) {
                Bridge b = new Bridge(bridge);
                b.getComponents().add(c);
                buildBridges(c.getOtherPort(port), components, b, bridges);
            }
        }
    }

    @Override
    public String part1() {
        return String.valueOf(maxStength);
    }

    @Override
    public String part2() {
        return String.valueOf(strengthLongest);
    }
}
