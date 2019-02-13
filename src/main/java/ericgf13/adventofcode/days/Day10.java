package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 extends Day {
    public Day10() {
        super(10);
    }

    @Override
    public String part1() {
        List<Integer> input = Arrays.stream(getInputAsString().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());

        int pos = 0;
        int skipSize = 0;

        for (int length : input) {
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                subList.add(list.get((pos + i) % list.size()));
            }

            Collections.reverse(subList);

            for (int i = 0; i < length; i++) {
                list.set((pos + i) % list.size(), subList.get(0));
                subList.remove(0);
            }

            pos = (pos + length) % list.size() + skipSize++;
        }

        return String.valueOf(list.get(0) * list.get(1));
    }

    @Override
    public String part2() {
        return knotHash(getInputAsString());
    }

    public static String knotHash(String input) {
        List<Integer> inputChars = input.chars().boxed().collect(Collectors.toList());
        inputChars.addAll(Arrays.asList(17, 31, 73, 47, 23));

        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());

        int pos = 0;
        int skipSize = 0;

        for (int y = 0; y < 64; y++) {
            for (int length : inputChars) {

                List<Integer> subList = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    subList.add(list.get((pos + i) % list.size()));
                }

                Collections.reverse(subList);

                for (int i = 0; i < length; i++) {
                    list.set((pos + i) % list.size(), subList.get(0));
                    subList.remove(0);
                }

                pos = (pos + length) % list.size() + skipSize++;
            }
        }

        List<Integer> denseHash = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int hash = 0;
            for (int y = i * 16; y < (i + 1) * 16; y++) {
                hash ^= list.get(y);
            }
            denseHash.add(hash);
        }

        return denseHash.stream().map(p -> String.format("%02x", p)).collect(Collectors.joining());
    }
}
