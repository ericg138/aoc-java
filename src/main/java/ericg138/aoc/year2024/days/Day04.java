package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2015.util.Coordinate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day04 extends Day {
  private final List<String> input = getInputAsList();

  public Day04() {
    super(4);
  }

  @Override
  public String part1() {
    long result = 0;
    int height = input.size();
    int width = input.get(0).length();

    Map<Coordinate, Character> valueMap = new HashMap<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        valueMap.put(newCoord(x, y), input.get(y).charAt(x));
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (valueMap.get(newCoord(x, y)) == 'X') {

          // Right
          if (x < width - 3
              && valueMap.get(newCoord(x + 1, y)) == 'M'
              && valueMap.get(newCoord(x + 2, y)) == 'A'
              && valueMap.get(newCoord(x + 3, y)) == 'S') {
            result++;
          }

          // Left
          if (x > 2
              && valueMap.get(newCoord(x - 1, y)) == 'M'
              && valueMap.get(newCoord(x - 2, y)) == 'A'
              && valueMap.get(newCoord(x - 3, y)) == 'S') {
            result++;
          }

          // Down
          if (y < height - 3
              && valueMap.get(newCoord(x, y + 1)) == 'M'
              && valueMap.get(newCoord(x, y + 2)) == 'A'
              && valueMap.get(newCoord(x, y + 3)) == 'S') {
            result++;
          }

          // Up
          if (y > 2
              && valueMap.get(newCoord(x, y - 1)) == 'M'
              && valueMap.get(newCoord(x, y - 2)) == 'A'
              && valueMap.get(newCoord(x, y - 3)) == 'S') {
            result++;
          }

          // Down-Right
          if (x < width - 3 && y < height - 3
              && valueMap.get(newCoord(x + 1, y + 1)) == 'M'
              && valueMap.get(newCoord(x + 2, y + 2)) == 'A'
              && valueMap.get(newCoord(x + 3, y + 3)) == 'S') {
            result++;
          }

          // Down-Left
          if (x > 2 && y < height - 3
              && valueMap.get(newCoord(x - 1, y + 1)) == 'M'
              && valueMap.get(newCoord(x - 2, y + 2)) == 'A'
              && valueMap.get(newCoord(x - 3, y + 3)) == 'S') {
            result++;
          }

          // Up-Right
          if (x < width - 3 && y > 2
              && valueMap.get(newCoord(x + 1, y - 1)) == 'M'
              && valueMap.get(newCoord(x + 2, y - 2)) == 'A'
              && valueMap.get(newCoord(x + 3, y - 3)) == 'S') {
            result++;
          }

          // Up-Left
          if (x > 2 && y > 2
              && valueMap.get(newCoord(x - 1, y - 1)) == 'M'
              && valueMap.get(newCoord(x - 2, y - 2)) == 'A'
              && valueMap.get(newCoord(x - 3, y - 3)) == 'S') {
            result++;
          }
        }
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;
    int height = input.size();
    int width = input.get(0).length();

    Map<Coordinate, Character> valueMap = new HashMap<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        valueMap.put(newCoord(x, y), input.get(y).charAt(x));
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (valueMap.get(newCoord(x, y)) == 'A') {
          Character valueBottomRight = valueMap.get(newCoord(x + 1, y + 1));
          Character valueTopLeft = valueMap.get(newCoord(x - 1, y - 1));
          Character valueBottomLeft = valueMap.get(newCoord(x - 1, y + 1));
          Character valueTopRight = valueMap.get(newCoord(x + 1, y - 1));

          if (x < width - 1 && x > 0 && y < height - 1 && y > 0
              && (((valueBottomRight == 'M' && valueTopLeft == 'S')
                   || (valueBottomRight == 'S' && valueTopLeft == 'M'))
                  && ((valueBottomLeft == 'M' && valueTopRight == 'S')
                      || (valueBottomLeft == 'S' && valueTopRight == 'M')))) {
            result++;
          }
        }
      }
    }

    return "" + result;
  }

  private static Coordinate newCoord(int x, int y) {
    return new Coordinate(x, y);
  }
}
