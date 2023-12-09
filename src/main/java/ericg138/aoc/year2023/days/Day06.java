package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Day06 extends Day {
  private final List<String> input = getInputAsList();

  public Day06() {
    super(6);
  }

  @Override
  public String part1() {
    long result = 1;

    List<Integer> times = Stream.of(StringUtils.normalizeSpace(input.get(0).substring(5)).split(" ")).map(Integer::parseInt).toList();
    List<Integer> distances = Stream.of(StringUtils.normalizeSpace(input.get(1).substring(9)).split(" ")).map(Integer::parseInt).toList();

    for (int i = 0; i < times.size(); i++) {
      int time = times.get(i);
      int distance = distances.get(i);
      result *= getWaysToWin(time, distance);
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long time = Long.parseLong(input.get(0).substring(5).replace(" ", ""));
    long distance = Long.parseLong(input.get(1).substring(9).replace(" ", ""));

    return "" + getWaysToWin(time, distance);
  }

  private static long getWaysToWin(long time, long targetDistance) {
    long wait = 0;
    long waysToWin = 0;

    while (wait < time) {
      long distance = wait * (time - wait);
      if (distance > targetDistance) {
        waysToWin++;
      }
      wait++;
    }

    return waysToWin;
  }
}
