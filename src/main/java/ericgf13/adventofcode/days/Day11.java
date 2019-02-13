package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

public class Day11 extends Day {

    private int steps;
    private int mostSteps;

    public Day11() {
        super(11);

        int x = 0;
        int y = 0;

        for (String dir : getInputAsString().split(",")) {
            switch (dir) {
                case "n":
                    y += 2;
                    break;
                case "ne":
                    y++;
                    x++;
                    break;
                case "nw":
                    y++;
                    x--;
                    break;
                case "s":
                    y -= 2;
                    break;
                case "se":
                    y--;
                    x++;
                    break;
                case "sw":
                    y--;
                    x--;
                    break;
            }

            int xAbs = Math.abs(x);
            int yAbs = Math.abs(y);

            steps = xAbs + (yAbs - xAbs) / 2;

            if (steps > mostSteps) {
                mostSteps = steps;
            }
        }
    }

    @Override
    public String part1() {
        return String.valueOf(steps);
    }

    @Override
    public String part2() {
        return String.valueOf(mostSteps);
    }
}
