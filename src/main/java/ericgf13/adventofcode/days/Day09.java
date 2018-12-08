package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

public class Day09 extends Day {

    private int score;
    private int garbageCount;

    public Day09() {
        super(9);

        int groupVal = 1;
        boolean inGarbage = false;
        boolean ignore = false;

        for (char charac : getInputAsString().toCharArray()) {
            if (ignore) {
                ignore = false;
                continue;
            }

            if (charac == '!') {
                ignore = true;
            } else if (!inGarbage && charac == '<') {
                inGarbage = true;
            } else if (charac == '>') {
                inGarbage = false;
            } else if (!inGarbage && charac == '{') {
                score += groupVal;
                groupVal++;
            } else if (!inGarbage && charac == '}') {
                groupVal--;
            } else if (inGarbage) {
                garbageCount++;
            }
        }
    }

    @Override
    public String part1() {
        return String.valueOf(score);
    }

    @Override
    public String part2() {
        return String.valueOf(garbageCount);
    }
}
