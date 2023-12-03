package ericg138.aoc.year2023;

import ericg138.aoc.Day;
import ericg138.aoc.year2023.days.Day01;
import ericg138.aoc.year2023.days.Day02;
import ericg138.aoc.year2023.days.Day03;
import java.util.List;

public class Main2023 {

  public static void main(String[] args) {
    List.of(
      new Day01(),
      new Day02(),
      new Day03()
    ).forEach(Day::execute);
  }
}
