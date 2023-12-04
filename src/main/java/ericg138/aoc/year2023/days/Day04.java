package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Day04 extends Day {
  private final List<String> input = getInputAsList();

  public Day04() {
    super(4);
  }

  @Override
  public String part1() {
    int result = 0;

    for (String card : input) {
      List<Integer> winningNumbers = Stream
        .of(StringUtils.normalizeSpace(card.substring(card.indexOf(":") + 1, card.indexOf("|"))).split(" "))
        .map(Integer::parseInt)
        .toList();
      List<Integer> numbers = Stream
        .of(StringUtils.normalizeSpace(card.substring(card.indexOf("|") + 1)).split(" "))
        .map(Integer::parseInt)
        .toList();

      int winningCount = numbers.stream().filter(winningNumbers::contains).toList().size();
      int score = winningCount > 0 ? 1 : 0;
      for (int i = 0; i < winningCount - 1; i++) {
        score *= 2;
      }

      result += score;
    }

    return "" + result;
  }

  @Override
  public String part2() {
    Map<Integer, Integer> cardToCount = new LinkedHashMap<>();
    for (int i = 0; i < input.size(); i++) {
      cardToCount.put(i, 1);
    }

    for (int i = 0; i < input.size(); i++) {
      String card = input.get(i);
      List<Integer> winningNumbers = Stream
        .of(StringUtils.normalizeSpace(card.substring(card.indexOf(":") + 1, card.indexOf("|"))).split(" "))
        .map(Integer::parseInt)
        .toList();
      List<Integer> numbers = Stream
        .of(StringUtils.normalizeSpace(card.substring(card.indexOf("|") + 1)).split(" "))
        .map(Integer::parseInt)
        .toList();

      int winningCount = numbers.stream().filter(winningNumbers::contains).toList().size();
      for (int j = i + 1; j <= i + winningCount; j++) {
        cardToCount.put(j, cardToCount.get(j) + cardToCount.get(i));
      }
    }

    return "" + cardToCount.values().stream().mapToInt(Integer::valueOf).sum();
  }
}
