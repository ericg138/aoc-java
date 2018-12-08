package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 extends Day {

    private int steps;
    private int cycles;

    public Day06() {
        super(6);

        List<Integer> input = Arrays.stream(getInputAsString().split("\t"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int inputSize = input.size();
        List<String> snapshots = new ArrayList<>();

        String snapshot = getSnapshot(input);
        while (!snapshots.contains(snapshot)) {
            snapshots.add(snapshot);

            int indexWithMaxVal = -1;
            int maxVal = -1;
            for (int i = 0; i < inputSize; i++) {
                if (input.get(i) > maxVal) {
                    indexWithMaxVal = i;
                    maxVal = input.get(i);
                }
            }

            input.set(indexWithMaxVal, 0);

            int i = indexWithMaxVal == inputSize - 1 ? 0 : indexWithMaxVal + 1;
            while (maxVal > 0) {
                input.set(i, input.get(i) + 1);

                if (i == inputSize - 1) {
                    i = 0;
                } else {
                    i++;
                }

                maxVal--;
            }

            steps++;
            snapshot = getSnapshot(input);
        }

        cycles = snapshots.size() - snapshots.indexOf(snapshot);
    }

    private static String getSnapshot(List<Integer> input) {
        return input.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String part1() {
        return String.valueOf(steps);
    }

    @Override
    public String part2() {
        return String.valueOf(cycles);
    }
}
