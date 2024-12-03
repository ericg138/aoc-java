package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 extends Day {
  private final List<String> input = getInputAsList();

  public Day02() {
    super(2);
  }

  @Override
  public String part1() {
    long result = 0;

    for (String report : input) {
      List<Integer> levels = Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).boxed().toList();
      if (isSafe(levels)) {
        result++;
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;

    for (String report : input) {
      List<Integer> levels = Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).boxed().toList();
      for (int i = 0; i < levels.size(); i++) {
        ArrayList<Integer> subLevels = new ArrayList<>(levels);
        subLevels.remove(i);
        if (isSafe(subLevels)) {
          result++;
          break;
        }
      }
    }

    return "" + result;
  }

  private boolean isSafe(List<Integer> levels) {
    boolean increasing = true;

    for (int i = 1; i < levels.size(); i++) {
      Integer value = levels.get(i);
      Integer valueBefore = levels.get(i - 1);

      if (i == 1) {
        increasing = valueBefore < value;
      } else {
        if (increasing && valueBefore >= value) {
          return false;
        }
        if (!increasing && valueBefore <= value) {
          return false;
        }
      }

      int diff = Math.abs(valueBefore - value);
      if (diff > 3 || diff < 1) {
        return false;
      }
    }

    return true;
  }
}
