package ericg138.aoc.year2024.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2015.util.Coordinate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Day {
  private final List<String> input = getInputAsList();

  public Day06() {
    super(6);
  }

  @Override
  public String part1() {
    int height = input.size();
    int width = input.get(0).length();

    Set<Coordinate> visited = new HashSet<>();
    Set<Coordinate> obstacles = new HashSet<>();
    Coordinate guard = new Coordinate(0, 0);
    char direction = '^';

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        char c = input.get(y).charAt(x);
        if (c == '#') {
          obstacles.add(new Coordinate(x, y));
        } else if (c == direction) {
          guard = new Coordinate(x, y);
        }
      }
    }

    navigate(guard, direction, obstacles, visited, height, width);

    return "" + visited.size();
  }

  @Override
  public String part2() {
    int height = input.size();
    int width = input.get(0).length();
    int result = 0;

    Set<Coordinate> obstacles = new HashSet<>();
    Coordinate guard = new Coordinate(0, 0);
    char direction = '^';

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        char c = input.get(y).charAt(x);
        if (c == '#') {
          obstacles.add(new Coordinate(x, y));
        } else if (c == direction) {
          guard = new Coordinate(x, y);
        }
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Coordinate coordObstacle = new Coordinate(x, y);
        if (!obstacles.contains(coordObstacle)) {
          Set<Coordinate> simulationObstacles = new HashSet<>(obstacles);
          simulationObstacles.add(coordObstacle);
          Coordinate simulationGuard = new Coordinate(guard.getX(), guard.getY());

          if (!navigate(simulationGuard, direction, simulationObstacles, null, height, width)) {
            result++;
          }
        }
      }
    }

    return "" + result;
  }

  private static boolean navigate(Coordinate guard, char direction, Set<Coordinate> obstacles, Set<Coordinate> visited, int height,
                                  int width) {
    int iterations = 0;
    do {
      iterations++;
      Coordinate newPosition = new Coordinate(guard.getX(), guard.getY());

      switch (direction) {
        case '^' -> newPosition.offsetY(-1);
        case 'v' -> newPosition.offsetY(1);
        case '<' -> newPosition.offsetX(-1);
        case '>' -> newPosition.offsetX(1);
      }

      if (obstacles.contains(newPosition)) {
        direction = switch (direction) {
          case '^' -> '>';
          case 'v' -> '<';
          case '<' -> '^';
          case '>' -> 'v';
          default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
      } else {
        if (visited != null) {
          visited.add(newPosition);
        }
        guard = newPosition;
      }
    } while (iterations < 7000 && guard.getX() < width - 1 && guard.getX() >= 0 && guard.getY() < height - 1 && guard.getY() >= 0);

    return iterations < 7000;
  }
}
