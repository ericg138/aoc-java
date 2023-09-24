package ericg138.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

public abstract class Day {
    private final int dayNum;

    protected Day(int dayNum) {
        this.dayNum = dayNum;
    }

    private static String measureTime(Supplier<String> exec) {
        long start = System.currentTimeMillis();
        String result = exec.get();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        return result + " - " + timeElapsed + " ms";
    }

    public int getDayNum() {
        return dayNum;
    }

    public abstract String part1();

    public abstract String part2();

    protected List<String> getInputAsList() {
        try {
            return Files.readAllLines(getFile());
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Failed to read input file for day " + dayNum);
        }
    }

    protected String getInputAsString() {
        try {
            return new String(Files.readAllBytes(getFile()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Failed to read input file for day " + dayNum);
        }
    }

    private Path getFile() throws URISyntaxException {
        return Paths.get(this.getClass().getResource("/" + getYear() + "/day" + String.format("%02d", dayNum) + ".txt").toURI());
    }

    private String getYear() {
        return this.getClass().getPackageName().split("\\.")[2];
    }

    public void execute() {
        System.out.println("===== DAY " + this.getDayNum() + " =====");
        System.out.println("Part 1: " + measureTime(this::part1));
        System.out.println("Part 2: " + measureTime(this::part2));
    }
}
