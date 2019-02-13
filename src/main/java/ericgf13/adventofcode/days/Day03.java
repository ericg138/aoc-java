package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

public class Day03 extends Day {
    public Day03() {
        super(3);
    }

    private static final int INPUT = 368078;

    @Override
    public String part1() {
        int steps = 0;

        int square = (int) Math.ceil(Math.sqrt(INPUT));
        if (square % 2 == 0) {
            square++;
        }
        int offset = square / 2;

        int bottomRightValue = (int) Math.pow(square, 2);
        int bottomLeftValue = bottomRightValue - square + 1;
        int topLeftValue = bottomLeftValue - square + 1;
        int topRightValue = topLeftValue - square + 1;

        int topValue;
        if (bottomLeftValue < INPUT) {
            topValue = bottomRightValue;
        } else if (topLeftValue < INPUT) {
            topValue = bottomLeftValue;
        } else if (topRightValue < INPUT) {
            topValue = topLeftValue;
        } else {
            topValue = topRightValue;
        }

        int delta = topValue - INPUT;

        if (delta > offset) {
            steps += delta - offset;
        } else {
            steps += offset - delta;
        }

        steps += offset;

        return String.valueOf(steps);
    }

    @Override
    public String part2() {
        return "";
    }
}
