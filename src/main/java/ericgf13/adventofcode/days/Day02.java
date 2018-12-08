package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 extends Day {
    public Day02() {
        super(2);
    }

    private List<Integer[]> input = getInputAsList().stream()
            .map(l -> Arrays.stream(l.split("\t")).map(Integer::parseInt).toArray(Integer[]::new))
            .collect(Collectors.toList());

    @Override
    public String part1() {
        int sum = 0;

        for (Integer[] line : input) {
            int min = -1;
            int max = -1;
            for (Integer num : line) {
                if (min == -1 || num < min) {
                    min = num;
                }
                if (max == -1 || num > max) {
                    max = num;
                }
            }
            sum += max - min;
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2() {
        return input.stream()
                .map(this::calc)
                .reduce(0, (a, b) -> a + b)
                .toString();
    }

    private int calc(Integer[] line) {
        for (Integer num : line) {
            for (Integer num2 : line) {
                if (!num.equals(num2) && num % num2 == 0) {
                    return num / num2;
                }
            }
        }
        return 0;
    }
}
