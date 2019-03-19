package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 extends Day {
    public Day23() {
        super(23);
    }

    @Override
    public String part1() {
        List<String> input = getInputAsList();

        Map<String, Long> values = new HashMap<>();
        int mulCount = 0;

        int i = 0;
        while (i < input.size()) {
            String instruction = input.get(i++);
            String[] splitInstruction = instruction.split(" ");
            String x = splitInstruction[1];
            String y = splitInstruction.length > 2 ? splitInstruction[2] : null;

            switch (splitInstruction[0]) {
                case "set":
                    values.put(x, Day18.getValue(y, values));
                    break;
                case "sub":
                    values.put(x, Day18.getValue(x, values) - Day18.getValue(y, values));
                    break;
                case "mul":
                    values.put(x, Day18.getValue(x, values) * Day18.getValue(y, values));
                    mulCount++;
                    break;
                case "jnz":
                    if (Day18.getValue(x, values) != 0) {
                        i += Day18.getValue(y, values) - 1;
                    }
                    break;
            }
        }

        return String.valueOf(mulCount);
    }

    @Override
    public String part2() {
        return "";
    }
}
