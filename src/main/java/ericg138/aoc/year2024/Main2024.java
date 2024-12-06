package ericg138.aoc.year2024;

import ericg138.aoc.Day;
import ericg138.aoc.year2024.days.Day01;
import ericg138.aoc.year2024.days.Day02;
import ericg138.aoc.year2024.days.Day03;
import ericg138.aoc.year2024.days.Day04;
import ericg138.aoc.year2024.days.Day05;
import java.util.List;

public class Main2024 {

  public static void main(String[] args) {
    List.of(
      new Day01(),
      new Day02(),
      new Day03(),
      new Day04(),
      new Day05()
    ).forEach(Day::execute);
  }
}
