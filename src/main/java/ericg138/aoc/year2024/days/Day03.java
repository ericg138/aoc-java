package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {
  private final String input = getInputAsString();
  private static final Pattern PATTERN = Pattern.compile("^mul\\((\\d+),(\\d+)\\)");

  public Day03() {
    super(3);
  }

  @Override
  public String part1() {
    long result = 0;

    for (int i = 0; i < input.length(); i++) {
      String substring = input.substring(i);
      Matcher matcher = PATTERN.matcher(substring);
      if (matcher.find()) {
        result += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;
    boolean enabled = true;

    for (int i = 0; i < input.length(); i++) {
      String substring = input.substring(i);

      if (substring.startsWith("do()")) {
        enabled = true;
      } else if (substring.startsWith("don't()")) {
        enabled = false;
      }

      if (enabled) {
        Matcher matcher = PATTERN.matcher(substring);
        if (matcher.find()) {
          result += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
        }
      }
    }

    return "" + result;
  }
}
