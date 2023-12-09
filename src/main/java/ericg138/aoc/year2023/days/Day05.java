package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day05 extends Day {
  private final List<String> input = getInputAsList();

  public Day05() {
    super(5);
  }

  @Override
  public String part1() {
    long result = -1;

    List<Long> seeds = Stream.of(input.get(0).substring(7).split(" ")).map(Long::parseLong).toList();

    List<Range> seedToSoil = new ArrayList<>();
    List<Range> soilToFertilizer = new ArrayList<>();
    List<Range> fertilizerToWater = new ArrayList<>();
    List<Range> waterToLight = new ArrayList<>();
    List<Range> lightToTemperature = new ArrayList<>();
    List<Range> temperatureToHumidity = new ArrayList<>();
    List<Range> humidityToLocation = new ArrayList<>();

    List<Range> currentList = null;
    for (String line : input.subList(2, input.size())) {
      if (line.isBlank()) {
        continue;
      }

      if (line.startsWith("seed-to-soil")) {
        currentList = seedToSoil;
      } else if (line.startsWith("soil-to-fertilizer")) {
        currentList = soilToFertilizer;
      } else if (line.startsWith("fertilizer-to-water")) {
        currentList = fertilizerToWater;
      } else if (line.startsWith("water-to-light")) {
        currentList = waterToLight;
      } else if (line.startsWith("light-to-temperature")) {
        currentList = lightToTemperature;
      } else if (line.startsWith("temperature-to-humidity")) {
        currentList = temperatureToHumidity;
      } else if (line.startsWith("humidity-to-location")) {
        currentList = humidityToLocation;
      } else {
        String[] split = line.split(" ");
        long destinationStart = Long.parseLong(split[0]);
        long sourceStart = Long.parseLong(split[1]);
        long length = Long.parseLong(split[2]);

        currentList.add(new Range(destinationStart, sourceStart, length));
      }
    }

    for (Long seed : seeds) {
      long soil = find(seedToSoil, seed);
      long fertilizer = find(soilToFertilizer, soil);
      long water = find(fertilizerToWater, fertilizer);
      long light = find(waterToLight, water);
      long temperature = find(lightToTemperature, light);
      long humidity = find(temperatureToHumidity, temperature);
      long location = find(humidityToLocation, humidity);

      if (result == -1 || location < result) {
        result = location;
      }
    }

    return "" + result;
  }

  @Override
  public String part2() {
    //return doPart2();
    return "47909639 (brute-force, takes around 30 minutes)";
  }

  private String doPart2() {
    long result = -1;

    List<Long> seeds = Stream.of(input.get(0).substring(7).split(" ")).map(Long::parseLong).toList();

    List<Range> seedToSoil = new ArrayList<>();
    List<Range> soilToFertilizer = new ArrayList<>();
    List<Range> fertilizerToWater = new ArrayList<>();
    List<Range> waterToLight = new ArrayList<>();
    List<Range> lightToTemperature = new ArrayList<>();
    List<Range> temperatureToHumidity = new ArrayList<>();
    List<Range> humidityToLocation = new ArrayList<>();

    List<Range> currentList = null;
    for (String line : input.subList(2, input.size())) {
      if (line.isBlank()) {
        continue;
      }

      if (line.startsWith("seed-to-soil")) {
        currentList = seedToSoil;
      } else if (line.startsWith("soil-to-fertilizer")) {
        currentList = soilToFertilizer;
      } else if (line.startsWith("fertilizer-to-water")) {
        currentList = fertilizerToWater;
      } else if (line.startsWith("water-to-light")) {
        currentList = waterToLight;
      } else if (line.startsWith("light-to-temperature")) {
        currentList = lightToTemperature;
      } else if (line.startsWith("temperature-to-humidity")) {
        currentList = temperatureToHumidity;
      } else if (line.startsWith("humidity-to-location")) {
        currentList = humidityToLocation;
      } else {
        String[] split = line.split(" ");
        long destinationStart = Long.parseLong(split[0]);
        long sourceStart = Long.parseLong(split[1]);
        long length = Long.parseLong(split[2]);

        currentList.add(new Range(destinationStart, sourceStart, length));
      }
    }

    for (int i = 0; i < seeds.size(); i = i + 2) {
      long start = seeds.get(i);
      long range = seeds.get(i + 1);

      for (long seed = start; seed < start + range; seed++) {
        long soil = find(seedToSoil, seed);
        long fertilizer = find(soilToFertilizer, soil);
        long water = find(fertilizerToWater, fertilizer);
        long light = find(waterToLight, water);
        long temperature = find(lightToTemperature, light);
        long humidity = find(temperatureToHumidity, temperature);
        long location = find(humidityToLocation, humidity);

        if (result == -1 || location < result) {
          result = location;
        }
      }
    }

    return "" + result;
  }

  private static Long find(List<Range> rangeList, long input) {
    return rangeList.stream().filter(e -> e.isInRange(input)).findFirst().map(e -> {
      long diff = input - e.sourceStart;
      return e.destinationStart + diff;
    }).orElse(input);
  }

  private record Range(Long destinationStart, Long sourceStart, Long length) {
    boolean isInRange(Long input) {
      return input >= sourceStart && input < sourceStart + length;
    }
  }
}
