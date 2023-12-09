package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08 extends Day {
  private final List<String> input = getInputAsList();

  public Day08() {
    super(8);
  }

  @Override
  public String part1() {
    long steps = 0;

    String instructions = input.get(0);
    List<Node> nodes = new ArrayList<>();
    for (String line : input.subList(2, input.size())) {
      nodes.add(new Node(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15)));
    }
    Map<String, Node> idToNode = nodes.stream().collect(Collectors.toMap(Node::id, Function.identity()));

    String next = "AAA";
    int instructionIndex = 0;
    while (!next.equals("ZZZ")) {
      steps++;

      if (instructions.charAt(instructionIndex) == 'R') {
        next = idToNode.get(next).right;
      } else {
        next = idToNode.get(next).left;
      }

      if (++instructionIndex == instructions.length()) {
        instructionIndex = 0;
      }
    }

    return "" + steps;
  }

  @Override
  public String part2() {
    long steps = 0;

    String instructions = input.get(0);
    List<Node> nodes = new ArrayList<>();
    for (String line : input.subList(2, input.size())) {
      nodes.add(new Node(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15)));
    }
    Map<String, Node> idToNode = nodes.stream().collect(Collectors.toMap(Node::id, Function.identity()));

    List<String> nextNodes = nodes.stream().filter(n -> n.id.endsWith("A")).map(Node::id).toList();
    List<Long> stepsToZ = new ArrayList<>();

    int instructionIndex = 0;

    while (!nextNodes.isEmpty()) {
      steps++;

      List<String> temp = new ArrayList<>();
      for (String next : nextNodes) {
        if (instructions.charAt(instructionIndex) == 'R') {
          next = idToNode.get(next).right;
        } else {
          next = idToNode.get(next).left;
        }
        if (next.endsWith("Z")) {
          stepsToZ.add(steps);
        } else {
          temp.add(next);
        }
      }
      nextNodes = temp;

      if (++instructionIndex == instructions.length()) {
        instructionIndex = 0;
      }
    }

    return "" + lcm(stepsToZ.stream().mapToLong(x -> x).toArray());
  }

  private static long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  public static long lcm(long... numbers) {
    return Arrays.stream(numbers).reduce(1, (x, y) -> x * (y / gcd(x, y)));
  }

  private record Node(String id, String left, String right) {
  }
}
