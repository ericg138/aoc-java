package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;

public class Day01 extends Day {
    private final String input = getInputAsString();

    public Day01() {
        super(1);
    }

    @Override
    public String part1() {
        int sum = 0;
        char previousDigit = 0;

        for (char digit : input.toCharArray()) {
            if (digit == previousDigit) {
                sum += Character.getNumericValue(digit);
            }
            previousDigit = digit;
        }

        char firstDigit = input.charAt(0);
        if (firstDigit == previousDigit) {
            sum += Character.getNumericValue(firstDigit);
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2() {
        int sum = 0;
        int steps = input.length() / 2;
        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int indexToCheck;
            if (i + steps >= input.length()) {
                indexToCheck = i + steps - input.length();
            } else {
                indexToCheck = i + steps;
            }

            if (chars[i] == input.charAt(indexToCheck)) {
                sum += Character.getNumericValue(chars[i]);
            }
        }

        return String.valueOf(sum);
    }
}
