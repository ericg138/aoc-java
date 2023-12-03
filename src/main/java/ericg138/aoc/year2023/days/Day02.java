package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day02 extends Day {
  private final List<String> input = getInputAsList();

  public Day02() {
    super(2);
  }

  @Override
  public String part1() {
    int result = 0;

    for (String line : input) {
      int id = Integer.parseInt(line.substring(5, line.indexOf(":")));
      String[] sets = line.substring(line.indexOf(":") + 1).split(";");

      boolean possible = true;
      for (String set : sets) {
        Map<String, Integer> colorToCount = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));

        Stream.of(set.split(",")).map(String::trim).forEach(s -> {
          int count = Integer.parseInt(s.substring(0, s.indexOf(" ")));
          String color = s.substring(s.indexOf(" ") + 1);
          colorToCount.put(color, count);
        });

        if (colorToCount.get("red") > 12 || colorToCount.get("green") > 13 || colorToCount.get("blue") > 14) {
          possible = false;
        }
      }

      if (possible) {
        result += id;
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    int result = 0;

    for (String line : input) {
      String[] sets = line.substring(line.indexOf(":") + 1).split(";");

      Map<String, Integer> colorToCount = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
      for (String set : sets) {
        Stream.of(set.split(",")).map(String::trim).forEach(s -> {
          int count = Integer.parseInt(s.substring(0, s.indexOf(" ")));
          String color = s.substring(s.indexOf(" ") + 1);
          colorToCount.put(color, Math.max(count, colorToCount.get(color)));
        });
      }

      result += colorToCount.get("red") * colorToCount.get("green") * colorToCount.get("blue");
    }

    return "" + result;
  }
}
