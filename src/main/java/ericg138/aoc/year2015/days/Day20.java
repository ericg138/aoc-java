package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;

import java.util.ArrayList;
import java.util.List;

public class Day20 extends Day {
    private final String input = getInputAsString();

    public Day20() {
        super(20);
    }

    @Override
    public String part1() {
        int house = 1;
        while (computePart1(house) < Integer.parseInt(input.trim())) {
            house++;
        }
        return "" + house;
    }

    private Integer computePart1(int house) {
        List<Integer> dividers = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(house); i++) {
            if (house % i == 0) {
                dividers.add(i);
                dividers.add(house / i);
            }
        }

        return dividers.stream().mapToInt(i -> i).sum() * 10;
    }

    @Override
    public String part2() {
        int house = 1;
        while (computePart2(house) < Integer.parseInt(input.trim())) {
            house++;
        }
        return "" + house;
    }

    private Integer computePart2(int house) {
        List<Integer> dividers = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(house); i++) {
            if (house % i == 0) {
                int d1 = i;
                int d2 = house / i;
                if (d1 <= 50) {
                    dividers.add(d2);
                }
                if (d2 <= 50) {
                    dividers.add(d1);
                }
            }
        }

        return dividers.stream().mapToInt(i -> i).sum() * 11;
    }
}
