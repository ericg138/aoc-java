package ericg138.aoc.year2023;

import ericg138.aoc.Day;
import ericg138.aoc.year2023.days.Day01;
import ericg138.aoc.year2023.days.Day02;
import ericg138.aoc.year2023.days.Day03;
import ericg138.aoc.year2023.days.Day04;
import ericg138.aoc.year2023.days.Day05;
import ericg138.aoc.year2023.days.Day06;
import ericg138.aoc.year2023.days.Day07;
import ericg138.aoc.year2023.days.Day08;
import ericg138.aoc.year2023.days.Day09;
import java.util.List;

public class Main2023 {

  public static void main(String[] args) {
    List.of(
      new Day01(),
      new Day02(),
      new Day03(),
      new Day04(),
      new Day05(),
      new Day06(),
      new Day07(),
      new Day08(),
      new Day09()
    ).forEach(Day::execute);
  }
}
