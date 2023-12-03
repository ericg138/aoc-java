package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2015.util.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day03 extends Day {
  private final List<String> input = getInputAsList();
  private final int width = input.get(0).length();
  private final int height = input.size();

  public Day03() {
    super(3);
  }

  @Override
  public String part1() {
    int result = 0;

    for (int y = 0; y < height; y++) {
      String line = input.get(y);

      StringBuilder currentNum = new StringBuilder();
      for (int x = 0; x < width; x++) {
        char value = line.charAt(x);
        if (StringUtils.isNumeric(String.valueOf(value))) {
          currentNum.append(value);
        } else {
          if (isPartNumber(currentNum.toString(), x, y)) {
            result += Integer.parseInt(currentNum.toString());
          }
          currentNum = new StringBuilder();
        }
      }

      if (isPartNumber(currentNum.toString(), width, y)) {
        result += Integer.parseInt(currentNum.toString());
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    Map<Coordinate, List<Integer>> gearToNumbers = new HashMap<>();

    for (int y = 0; y < height; y++) {
      String line = input.get(y);

      StringBuilder currentNum = new StringBuilder();
      for (int x = 0; x < width; x++) {
        char value = line.charAt(x);
        if (StringUtils.isNumeric(String.valueOf(value))) {
          currentNum.append(value);
        } else {
          Coordinate gear = isAdjacentToGear(currentNum.toString(), x, y);
          if (gear != null) {
            gearToNumbers.putIfAbsent(gear, new ArrayList<>());
            gearToNumbers.get(gear).add(Integer.parseInt(currentNum.toString()));
          }
          currentNum = new StringBuilder();
        }
      }

      Coordinate gear = isAdjacentToGear(currentNum.toString(), width, y);
      if (gear != null) {
        gearToNumbers.putIfAbsent(gear, new ArrayList<>());
        gearToNumbers.get(gear).add(Integer.parseInt(currentNum.toString()));
      }
    }

    return "" + gearToNumbers.values().stream()
      .filter(list -> list.size() == 2)
      .map(list -> list.get(0) * list.get(1))
      .mapToInt(Integer::intValue)
      .sum();
  }

  private boolean isPartNumber(String number, int x, int y) {
    if (number.isEmpty()) {
      return false;
    }
    int numWidth = number.length();

    for (int i = max(0, x - numWidth - 1); i < min(width, x + 1); i++) {
      for (int j = max(0, y - 1); j < min(height, y + 2); j++) {
        String value = String.valueOf(input.get(j).charAt(i));
        if (!value.equals(".") && !StringUtils.isNumeric(value)) {
          return true;
        }
      }
    }

    return false;
  }

  private Coordinate isAdjacentToGear(String number, int x, int y) {
    if (number.isEmpty()) {
      return null;
    }
    int numWidth = number.length();

    for (int i = max(0, x - numWidth - 1); i < min(width, x + 1); i++) {
      for (int j = max(0, y - 1); j < min(height, y + 2); j++) {
        if (input.get(j).charAt(i) == '*') {
          return new Coordinate(i, j);
        }
      }
    }

    return null;
  }
}
