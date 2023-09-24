package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day12 extends Day {

    private final Map<Integer, Set<Integer>> input = new HashMap<>();
    private final Set<Integer> connected = new HashSet<>();

    public Day12() {
        super(12);

        for (String line : getInputAsList()) {
            Integer num = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            Set<Integer> links = new HashSet<>();
            for (String s : line.substring(line.indexOf("<->") + 4).split(", ")) {
                links.add(Integer.parseInt(s));
            }
            links.removeIf(l -> l.equals(num));
            input.put(num, links);
        }

        connected.add(0);
        findConnected(0, input, connected, new HashSet<>());
    }

    private void findConnected(int num, Map<Integer, Set<Integer>> input, Set<Integer> connected, Set<Integer> processed) {
        input.get(num).stream().filter(link -> !processed.contains(link)).forEach(link -> {
            processed.add(link);
            connected.add(link);
            findConnected(link, input, connected, processed);
        });
    }

    @Override
    public String part1() {
        return String.valueOf(connected.size());
    }

    @Override
    public String part2() {
        int nbGroups = 1;

        for (int num : input.keySet()) {
            if (!connected.contains(num)) {
                findConnected(num, input, connected, new HashSet<>());
                nbGroups++;
            }
        }

        return String.valueOf(nbGroups);
    }
}
