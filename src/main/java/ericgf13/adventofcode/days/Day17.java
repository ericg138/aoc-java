package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends Day {
    public Day17() {
        super(17);
    }

    private int input = 335;

    @Override
    public String part1() {
        List<Integer> buffer = new ArrayList<>();
        buffer.add(0);

        int pos = 0;

        for (int i = 1; i <= 2017; i++) {
            pos = (pos + input) % buffer.size() + 1;
            buffer.add(pos, i);
        }

        return String.valueOf(buffer.get(pos + 1));
    }

    @Override
    public String part2() {
        int pos = 0;
        int result = 0;

        for (int i = 1; i <= 50_000_000; i++) {
            pos = (pos + input) % i + 1;

            if (pos == 1) {
                result = i;
            }
        }

        return String.valueOf(result);
    }
}
