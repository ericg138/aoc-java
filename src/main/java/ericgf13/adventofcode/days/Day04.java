package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 extends Day {
    public Day04() {
        super(4);
    }

    private List<String> input = getInputAsList();

    @Override
    public String part1() {
        int nbValid = 0;

        for (String line : input) {
            nbValid++;
            Set<String> checker = new HashSet<>();
            for (String word : line.split(" ")) {
                if (checker.contains(word)) {
                    nbValid--;
                    break;
                }
                checker.add(word);
            }
        }

        return String.valueOf(nbValid);
    }

    @Override
    public String part2() {
        int nbValid = 0;

        for (String line : input) {
            nbValid++;
            Set<String> checker = new HashSet<>();
            for (String word : line.split(" ")) {
                word = sortString(word);
                if (checker.contains(word)) {
                    nbValid--;
                    break;
                }
                checker.add(word);
            }
        }

        return String.valueOf(nbValid);
    }

    private static String sortString(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
