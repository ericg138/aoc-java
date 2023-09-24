package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2017.enums.Direction;

import java.util.List;

public class Day19 extends Day {

    private final StringBuilder letters = new StringBuilder();
    private int steps;

    public Day19() {
        super(19);

        List<String> input = getInputAsList();

        int x = input.get(0).indexOf('|');
        int y = 0;

        Direction dir = Direction.DOWN;

        char charac;
        while ((charac = input.get(y).charAt(x)) != ' ') {
            if (charac == '+') {
                if (dir == Direction.UP || dir == Direction.DOWN) {
                    if (input.get(y).charAt(x + 1) != ' ') {
                        dir = Direction.RIGHT;
                        x++;
                    } else if (input.get(y).charAt(x - 1) != ' ') {
                        dir = Direction.LEFT;
                        x--;
                    }
                } else if (dir == Direction.RIGHT || dir == Direction.LEFT) {
                    if (input.get(y - 1).charAt(x) != ' ') {
                        dir = Direction.UP;
                        y--;
                    } else if (input.get(y + 1).charAt(x) != ' ') {
                        dir = Direction.DOWN;
                        y++;
                    }
                }
            } else {
                switch (dir) {
                    case DOWN:
                        y++;
                        break;
                    case UP:
                        y--;
                        break;
                    case RIGHT:
                        x++;
                        break;
                    case LEFT:
                        x--;
                        break;
                }
                if (charac >= 'A' && charac <= 'Z') {
                    letters.append(charac);
                }
            }
            steps++;
        }
    }

    @Override
    public String part1() {
        return letters.toString();
    }

    @Override
    public String part2() {
        return String.valueOf(steps);
    }
}
