package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 extends Day {
    private static final String INPUT = "vbqugkhl";

    public Day14() {
        super(14);
    }

    @Override
    public String part1() {
        List<String> binaryHashList = IntStream.range(0, 128)
                .mapToObj(i -> Day10.knotHash(INPUT + "-" + i))
                .map(hash -> new BigInteger(hash, 16).toString(2))
                .collect(Collectors.toList());

        int squareCount = 0;
        for (String binaryHash : binaryHashList) {
            squareCount += binaryHash.replace("0", "").length();
        }

        return String.valueOf(squareCount);
    }

    @Override
    public String part2() {
        return "";
    }
}
