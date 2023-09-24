package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day09 extends Day {
    private final List<String> input = getInputAsList();

    public Day09() {
        super(9);
    }

    private List<Path> getPaths() {
        List<Path> paths = new ArrayList<>();
        input.forEach(instr -> {
            String[] split = instr.split(" = ");
            String[] citySplit = split[0].split(" to ");
            String city1 = citySplit[0];
            String city2 = citySplit[1];
            int distance = Integer.parseInt(split[1]);
            paths.add(new Path(city1, city2, distance));
            paths.add(new Path(city2, city1, distance));
        });
        return paths;
    }

    // Another approach could be to generate all possible combinations and calculate the distance for each

    @Override
    public String part1() {
        List<Path> paths = getPaths();
        Set<String> cities = paths.stream().map(Path::from).collect(Collectors.toSet());

        int smallestResult = 1000;
        for (String city : cities) {
            Set<String> toVisit = new HashSet<>(cities);
            toVisit.remove(city);

            int result = visitPart1(city, toVisit, paths);

            if (result < smallestResult) {
                smallestResult = result;
            }
        }

        return "" + smallestResult;
    }

    private int visitPart1(String city, Set<String> toVisit, List<Path> paths) {
        int smallestDistance = 1000;
        String nextDest = null;
        for (Path path : paths.stream().filter(p -> p.from.equals(city) && toVisit.contains(p.to)).toList()) {
            if (path.distance < smallestDistance) {
                smallestDistance = path.distance;
                nextDest = path.to;
            }
        }

        toVisit.remove(nextDest);
        if (!toVisit.isEmpty()) {
            smallestDistance += visitPart1(nextDest, toVisit, paths);
        }

        return smallestDistance;
    }

    @Override
    public String part2() {
        List<Path> paths = getPaths();
        Set<String> cities = paths.stream().map(Path::from).collect(Collectors.toSet());

        int largestResult = 0;
        for (String city : cities) {
            Set<String> toVisit = new HashSet<>(cities);
            toVisit.remove(city);

            int result = visitPart2(city, toVisit, paths);

            if (result > largestResult) {
                largestResult = result;
            }
        }

        return "" + largestResult;
    }

    private int visitPart2(String city, Set<String> toVisit, List<Path> paths) {
        int largestDistance = 0;
        String nextDest = null;
        for (Path path : paths.stream().filter(p -> p.from.equals(city) && toVisit.contains(p.to)).toList()) {
            if (path.distance > largestDistance) {
                largestDistance = path.distance;
                nextDest = path.to;
            }
        }

        toVisit.remove(nextDest);
        if (!toVisit.isEmpty()) {
            largestDistance += visitPart2(nextDest, toVisit, paths);
        }

        return largestDistance;
    }

    private record Path(String from, String to, int distance) {
    }
}
