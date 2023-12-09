package ericg138.aoc.year2023.days;

import com.google.common.collect.Iterables;
import ericg138.aoc.Day;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Day01 extends Day {
  private final List<String> input = getInputAsList();

  public Day01() {
    super(1);
  }

  @Override
  public String part1() {
    int result = 0;
    for (String line : input) {
      List<String> numList = Stream.of(line.split("")).filter(StringUtils::isNumeric).toList();
      result += Integer.parseInt(numList.get(0) + Iterables.getLast(numList));
    }
    return "" + result;
  }

  @Override
  public String part2() {
    int result = 0;
    Map<String, String> digits = Map.of(
      "one", "1",
      "two", "2",
      "three", "3",
      "four", "4",
      "five", "5",
      "six", "6",
      "seven", "7",
      "eight", "8",
      "nine", "9");
    for (String line : input) {
      String min = null;
      String max = null;
      for (int i = 0; i < line.length(); i++) {
        String value = String.valueOf(line.charAt(i));
        if (StringUtils.isNumeric(value)) {
          if (min == null) {
            min = value;
          }
          max = value;
        } else {
          for (Map.Entry<String, String> entry : digits.entrySet()) {
            if (line.startsWith(entry.getKey(), i)) {
              if (min == null) {
                min = entry.getValue();
              }
              max = entry.getValue();
              break;
            }
          }
        }
      }
      result += Integer.parseInt(min + max);
    }
    return "" + result;
  }
}
