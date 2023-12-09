package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Iterables.getLast;

public class Day09 extends Day {
  private final List<String> input = getInputAsList();

  public Day09() {
    super(9);
  }

  @Override
  public String part1() {
    int result = 0;

    for (String history : input) {
      List<Integer> current = Stream.of(history.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
      List<List<Integer>> all = new ArrayList<>(List.of(current));

      while (current.stream().anyMatch(i -> i != 0)) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < current.size() - 1; i++) {
          int diff = current.get(i + 1) - current.get(i);
          sequence.add(diff);
        }
        all.add(sequence);
        current = sequence;
      }

      Collections.reverse(all);

      int value = 0;
      for (List<Integer> sequence : all) {
        value = getLast(sequence) + value;
        sequence.add(value);
      }

      result += getLast(getLast(all));
    }

    return "" + result;
  }

  @Override
  public String part2() {
    int result = 0;

    for (String history : input) {
      List<Integer> current = Stream.of(history.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
      List<List<Integer>> all = new ArrayList<>(List.of(current));

      while (current.stream().anyMatch(i -> i != 0)) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < current.size() - 1; i++) {
          int diff = current.get(i + 1) - current.get(i);
          sequence.add(diff);
        }
        all.add(sequence);
        current = sequence;
      }

      Collections.reverse(all);

      int value = 0;
      for (List<Integer> sequence : all) {
        value = sequence.get(0) - value;
        sequence.add(0, value);
      }

      result += getLast(all).get(0);
    }

    return "" + result;
  }
}
