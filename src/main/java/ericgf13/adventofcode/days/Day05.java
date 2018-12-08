package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends Day {
    public Day05() {
        super(5);
    }

    private List<Integer> input = getInputAsList().stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());

    @Override
    public String part1() {
        ArrayList<Integer> list = new ArrayList<>(input);
        int length = list.size();
        int pos = 0;
        int steps = 0;

        while (pos < length) {
            int jump = list.get(pos);
            list.set(pos, jump + 1);
            pos += jump;
            steps++;
        }

        return String.valueOf(steps);
    }

    @Override
    public String part2() {
        ArrayList<Integer> list = new ArrayList<>(input);
        int length = list.size();
        int pos = 0;
        int steps = 0;

        while (pos < length) {
            int jump = list.get(pos);
            if (jump >= 3) {
                list.set(pos, jump - 1);
            } else {
                list.set(pos, jump + 1);
            }
            pos += jump;
            steps++;
        }

        return String.valueOf(steps);
    }
}
