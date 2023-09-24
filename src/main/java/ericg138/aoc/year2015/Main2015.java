package ericg138.aoc.year2015;

import ericg138.aoc.Day;
import ericg138.aoc.year2015.days.*;

import java.util.List;

public class Main2015 {

    public static void main(String[] args) {
        List.of(
                new Day03(),
                new Day05(),
                new Day09(),
                new Day10(),
                new Day12(),
                new Day20()
        ).forEach(Day::execute);
    }
}
