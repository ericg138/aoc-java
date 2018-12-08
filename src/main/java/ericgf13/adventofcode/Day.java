package ericgf13.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {
    private int dayNum;

    public Day(int dayNum) {
        this.dayNum = dayNum;
    }

    int getDayNum() {
        return dayNum;
    }

    public abstract String part1();

    public abstract String part2();

    protected List<String> getInputAsList() {
        try {
            return Files.readAllLines(getFile());
        } catch (IOException | URISyntaxException e) {
            System.err.println("Failed to read input file for day " + dayNum);
            return null;
        }
    }

    protected String getInputAsString() {
        try {
            return new String(Files.readAllBytes(getFile()));
        } catch (IOException | URISyntaxException e) {
            System.err.println("Failed to read input file for day " + dayNum);
            return null;
        }
    }

    private Path getFile() throws URISyntaxException {
        return Paths.get(this.getClass().getResource("/day" + String.format("%02d", dayNum) + ".txt").toURI());
    }
}
