package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day12 extends Day {
    private final String input = getInputAsString();

    public Day12() {
        super(12);
    }

    @Override
    public String part1() {
        String result = input.replaceAll("[^0-9\\-]", " ");
        List<Integer> list = Arrays.stream(result.split(" "))
                .filter(c -> !c.isEmpty())
                .map(Integer::parseInt)
                .toList();
        return "" + list.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String part2() {
        return "" + traverse(new JSONArray(input).toList());
    }

    private int traverse(List<?> list) {
        int count = 0;

        for (Object o : list) {
            if (o instanceof List<?>) {
                count += traverse((List<?>) o);
            } else if (o instanceof Map<?, ?>) {
                count += traverse((Map<?, ?>) o);
            } else if (o instanceof Integer) {
                count += (int) o;
            }
        }

        return count;
    }

    private int traverse(Map<?, ?> map) {
        int count = 0;

        for (Object o : map.values()) {
            if (o instanceof List<?>) {
                count += traverse((List<?>) o);
            } else if (o instanceof Map<?, ?>) {
                count += traverse((Map<?, ?>) o);
            } else if (o instanceof Integer) {
                count += (int) o;
            } else if (o instanceof String && o.equals("red")) {
                return 0;
            }
        }

        return count;
    }
}
