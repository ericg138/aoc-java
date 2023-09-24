package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends Day {
    private final List<String> input = getInputAsList();

    public Day05() {
        super(5);
    }

    @Override
    public String part1() {
        List<String> words = new ArrayList<>();

        for (String word : input) {
            if (word.contains("ab") || word.contains("cd") || word.contains("pq") || word.contains("xy")) {
                continue;
            }
            char previous = ' ';
            List<Character> vowels = new ArrayList<>();
            boolean duplicate = false;
            for (char current : word.toCharArray()) {
                if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u') {
                    vowels.add(current);
                }
                if (current == previous) {
                    duplicate = true;
                }
                previous = current;
            }
            if (vowels.size() >= 3 && duplicate) {
                words.add(word);
            }
        }

        return "" + words.size();
    }

    @Override
    public String part2() {
        List<String> words = new ArrayList<>();

        for (String word : input) {
            List<String> pairs = new ArrayList<>();

            boolean duplicatePair = false;
            boolean repeat = false;
            String pair = "";
            for (int i = 0; i < word.length(); i++) {
                char current = word.charAt(i);

                pair += "" + current;
                if (pair.length() == 2) {
                    if (pairs.size() > 1 && pairs.subList(0, pairs.size() - 1).contains(pair)) {
                        duplicatePair = true;
                    }
                    pairs.add(pair);
                    pair = pair.substring(1);
                }

                if (i > 1 && current == word.charAt(i - 2)) {
                    repeat = true;
                }
            }

            if (duplicatePair && repeat) {
                words.add(word);
            }
        }

        return "" + words.size();
    }
}
