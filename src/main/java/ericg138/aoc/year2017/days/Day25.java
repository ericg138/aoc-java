package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2017.beans.StateInstruction;
import ericg138.aoc.year2017.enums.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day25 extends Day {
    public Day25() {
        super(25);
    }

    @Override
    public String part1() {
        Map<String, StateInstruction> stateInstructions = new HashMap<>();
        int steps = 0;

        StateInstruction instruction = new StateInstruction();
        int val = 0;
        for (String line : getInputAsList()) {
            line = line.trim();

            if (line.startsWith("Perform a diagnostic checksum after")) {
                steps = Integer.parseInt(line.substring(36, line.indexOf(' ', 36)));
            } else if (line.startsWith("In state")) {
                instruction = new StateInstruction();
                stateInstructions.put(line.substring(9, 10), instruction);
            } else if (line.startsWith("If the current value is")) {
                val = Integer.parseInt(line.substring(24, 25));
            } else if (line.startsWith("- Write the value")) {
                instruction.setWriteVal(val, line.substring(18, 19));
            } else if (line.startsWith("- Move one slot to the")) {
                if (line.startsWith("right", 23)) {
                    instruction.setMoveDir(val, Direction.RIGHT);
                } else {
                    instruction.setMoveDir(val, Direction.LEFT);
                }
            } else if (line.startsWith("- Continue with state")) {
                instruction.setNextState(val, line.substring(22, 23));
            }
        }

        int pos = 0;
        List<Integer> tape = new ArrayList<>();
        String state = "A";

        for (int i = 0; i < steps; i++) {
            if (pos < 0) {
                tape.add(0, 0);
                pos = 0;
            } else if (pos >= tape.size()) {
                tape.add(0);
            }

            StateInstruction instru = stateInstructions.get(state);
            int currVal = tape.get(pos);

            tape.set(pos, instru.getWriteVal(currVal));
            if (instru.getMoveDir(currVal) == Direction.RIGHT) {
                pos++;
            } else {
                pos--;
            }
            state = instru.getNextState(currVal);
        }

        return String.valueOf(tape.stream().filter(i -> i == 1).count());
    }

    @Override
    public String part2() {
        return "";
    }
}
