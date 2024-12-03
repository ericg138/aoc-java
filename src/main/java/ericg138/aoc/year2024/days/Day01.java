package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 extends Day {
  private final List<String> input = getInputAsList();

  public Day01() {
    super(1);
  }

  @Override
  public String part1() {
    long result = 0;

    var list1 = new ArrayList<Integer>();
    var list2 = new ArrayList<Integer>();

    for (String line : input) {
      String[] split = line.split(" {3}");
      list1.add(Integer.parseInt(split[0]));
      list2.add(Integer.parseInt(split[1]));
    }

    Collections.sort(list1);
    Collections.sort(list2);

    for (int i = 0; i < list1.size(); i++) {
      result += Math.abs(list1.get(i) - list2.get(i));
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;

    var list1 = new ArrayList<Integer>();
    var list2 = new ArrayList<Integer>();

    for (String line : input) {
      String[] split = line.split(" {3}");
      list1.add(Integer.parseInt(split[0]));
      list2.add(Integer.parseInt(split[1]));
    }

    for (Integer value1 : list1) {
      long occurrences = list2.stream().filter(value2 -> value2.equals(value1)).count();
      result += value1 * occurrences;
    }

    return "" + result;
  }
}
