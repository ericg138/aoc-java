package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2015.util.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class Day03 extends Day {
    private final String input = getInputAsString();

    public Day03() {
        super(3);
    }

    @Override
    public String part1() {
        Set<Coordinate> visited = new HashSet<>();
        visited.add(new Coordinate(0, 0));

        Coordinate current = new Coordinate(0, 0);
        for (char instr : input.toCharArray()) {
            switch (instr) {
                case '<':
                    current.offsetX(-1);
                    break;
                case '>':
                    current.offsetX(1);
                    break;
                case '^':
                    current.offsetY(1);
                    break;
                case 'v':
                    current.offsetY(-1);
                    break;
            }
            visited.add(new Coordinate(current.getX(), current.getY()));
        }

        return "" + visited.size();
    }

    @Override
    public String part2() {
        Set<Coordinate> visited = new HashSet<>();
        visited.add(new Coordinate(0, 0));

        boolean visitSanta = true;

        Coordinate currentSanta = new Coordinate(0, 0);
        Coordinate currentRobo = new Coordinate(0, 0);
        for (char instr : input.toCharArray()) {
            Coordinate toVisit = visitSanta ? currentSanta : currentRobo;
            visitSanta = !visitSanta;
            switch (instr) {
                case '<':
                    toVisit.offsetX(-1);
                    break;
                case '>':
                    toVisit.offsetX(1);
                    break;
                case '^':
                    toVisit.offsetY(1);
                    break;
                case 'v':
                    toVisit.offsetY(-1);
                    break;
            }
            visited.add(new Coordinate(toVisit.getX(), toVisit.getY()));
        }

        return "" + visited.size();
    }
}
