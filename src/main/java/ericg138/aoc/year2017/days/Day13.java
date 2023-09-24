package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;

import java.util.LinkedHashMap;
import java.util.Map;

public class Day13 extends Day {

    private final Map<Integer, Integer> layers = new LinkedHashMap<>();

    public Day13() {
        super(13);

        for (String line : getInputAsList()) {
            int depth = Integer.parseInt(line.substring(0, line.indexOf(':')));
            int range = Integer.parseInt(line.substring(line.indexOf(':') + 2));
            layers.put(depth, range);
        }
    }

    @Override
    public String part1() {
        int severity = 0;

        for (Map.Entry<Integer, Integer> entry : layers.entrySet()) {
            if (isScannerPos0(entry.getKey(), entry.getValue())) {
                severity += entry.getValue() * entry.getKey();
            }
        }

        return String.valueOf(severity);
    }

    private boolean isScannerPos0(Integer delay, Integer range) {
        return delay % (range * 2 - 2) == 0;
    }

    @Override
    public String part2() {
        int delay = 1;

        while (parseLayers(delay)) {
            delay++;
        }

        return String.valueOf(delay);
    }

    private boolean parseLayers(int delay) {
        for (Map.Entry<Integer, Integer> entry : layers.entrySet()) {
            if (isScannerPos0(entry.getKey() + delay, entry.getValue())) {
                return true;
            }
        }
        return false;
    }
}
