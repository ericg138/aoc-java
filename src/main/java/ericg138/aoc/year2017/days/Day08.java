package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2017.beans.Condition;
import ericg138.aoc.year2017.beans.Instruction;

import java.util.*;

public class Day08 extends Day {

    private final int highestVal;
    private int highestValEver;

    public Day08() {
        super(8);

        Map<String, Integer> registers = new HashMap<>();
        List<Instruction> instructions = new ArrayList<>();

        for (String line : getInputAsList()) {
            String[] split = line.split(" ");
            registers.put(split[0], 0);

            boolean increment = split[1].equals("inc");
            Condition condition = new Condition(split[4], split[5], Integer.parseInt(split[6]));
            Instruction instruction = new Instruction(split[0], increment, Integer.parseInt(split[2]), condition);
            instructions.add(instruction);
        }

        for (Instruction instruction : instructions) {
            Condition condition = instruction.getCondition();
            boolean valid = false;
            switch (condition.getComparator()) {
                case ">":
                    valid = registers.get(condition.getRegister()) > condition.getValue();
                    break;
                case "<":
                    valid = registers.get(condition.getRegister()) < condition.getValue();
                    break;
                case ">=":
                    valid = registers.get(condition.getRegister()) >= condition.getValue();
                    break;
                case "<=":
                    valid = registers.get(condition.getRegister()) <= condition.getValue();
                    break;
                case "==":
                    valid = registers.get(condition.getRegister()) == condition.getValue();
                    break;
                case "!=":
                    valid = registers.get(condition.getRegister()) != condition.getValue();
                    break;
            }

            if (valid) {
                int newVal = registers.get(instruction.getRegister());
                if (instruction.isIncrement()) {
                    newVal += instruction.getAmount();
                } else {
                    newVal -= instruction.getAmount();
                }
                registers.put(instruction.getRegister(), newVal);

                if (newVal > highestValEver) {
                    highestValEver = newVal;
                }
            }
        }

        highestVal = registers.values().stream().max(Comparator.naturalOrder()).get();
    }

    @Override
    public String part1() {
        return String.valueOf(highestVal);
    }

    @Override
    public String part2() {
        return String.valueOf(highestValEver);
    }
}
