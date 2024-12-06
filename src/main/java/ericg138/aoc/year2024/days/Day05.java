package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day05 extends Day {
  private final List<String> input = getInputAsList();

  public Day05() {
    super(5);
  }

  @Override
  public String part1() {
    long result = 0;
    Map<Integer, List<Integer>> rules = new HashMap<>();
    boolean inRules = true;

    for (String line : input) {
      if (inRules) {
        if (line.isEmpty()) {
          inRules = false;
          continue;
        }
        String[] split = line.split("\\|");
        rules.putIfAbsent(Integer.parseInt(split[0]), new ArrayList<>());
        rules.get(Integer.parseInt(split[0])).add(Integer.parseInt(split[1]));
      } else {
        List<Integer> pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().toList();

        if (isOrdered(pages, rules)) {
          result += pages.get(pages.size() / 2);
        }
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;
    Map<Integer, List<Integer>> rules = new HashMap<>();
    boolean inRules = true;

    for (String line : input) {
      if (inRules) {
        if (line.isEmpty()) {
          inRules = false;
          continue;
        }
        String[] split = line.split("\\|");
        rules.putIfAbsent(Integer.parseInt(split[0]), new ArrayList<>());
        rules.get(Integer.parseInt(split[0])).add(Integer.parseInt(split[1]));
      } else {
        List<Integer> pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().toList();

        if (!isOrdered(pages, rules)) {
          List<Integer> newPages = reorderPages(pages, rules);
          result += newPages.get(newPages.size() / 2);
        }
      }
    }

    return "" + result;
  }

  private static boolean isOrdered(List<Integer> pages, Map<Integer, List<Integer>> rules) {
    for (int i = 0; i < pages.size(); i++) {
      int page = pages.get(i);
      List<Integer> mustBeBefore = rules.get(page);
      List<Integer> subList = pages.subList(0, i);
      if (mustBeBefore != null && mustBeBefore.stream().anyMatch(subList::contains)) {
        return false;
      }
    }
    return true;
  }

  private static List<Integer> reorderPages(List<Integer> pages, Map<Integer, List<Integer>> rules) {
    List<Integer> newPages = new ArrayList<>(pages);
    for (int i = 0; i < pages.size(); i++) {
      int page = pages.get(i);
      List<Integer> mustBeBefore = rules.get(page);
      if (mustBeBefore != null) {
        Integer minIndex = mustBeBefore.stream()
          .map(newPages::indexOf)
          .filter(index -> index != -1)
          .min(Comparator.naturalOrder())
          .orElse(null);
        if (minIndex != null && i > minIndex) {
          newPages.remove(i);
          newPages.add(minIndex, page);
        }
      }
    }
    return newPages;
  }
}
