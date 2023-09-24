package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 extends Day {

    private final List<String> programs = new ArrayList<>();
    private String firstPass;

    public Day16() {
        super(16);

        List<String> input = Arrays.stream(getInputAsString().split(",")).collect(Collectors.toList());

        for (char c = 97; c < 113; c++) {
            programs.add(String.valueOf(c));
        }
        String init = String.join("", programs);

        int i = 0;
        do {
            i++;
            dance(input, programs);
            if (firstPass == null) {
                firstPass = String.join("", programs);
            }
        } while (!String.join("", programs).equals(init));

        for (int y = 0; y < 1_000_000_000 % i; y++) {
            dance(input, programs);
        }
    }

    private void dance(List<String> input, List<String> programs) {
        for (String instruction : input) {
            switch (instruction.charAt(0)) {
                case 's':
                    int lengthToMove = Integer.parseInt(instruction.substring(1));
                    List<String> listToMove = new ArrayList<>(programs.subList(programs.size() - lengthToMove, programs.size()));
                    programs.removeAll(listToMove);
                    programs.addAll(0, listToMove);
                    break;
                case 'x':
                    int index1 = Integer.parseInt(instruction.substring(1, instruction.indexOf('/')));
                    int index2 = Integer.parseInt(instruction.substring(instruction.indexOf('/') + 1));
                    String prog1 = programs.get(index1);
                    String prog2 = programs.get(index2);
                    programs.set(index1, prog2);
                    programs.set(index2, prog1);
                    break;
                case 'p':
                    prog1 = instruction.substring(1, instruction.indexOf('/'));
                    prog2 = instruction.substring(instruction.indexOf('/') + 1);
                    index1 = programs.indexOf(prog1);
                    index2 = programs.indexOf(prog2);
                    programs.set(index1, prog2);
                    programs.set(index2, prog1);
                    break;
            }
        }
    }

    @Override
    public String part1() {
        return firstPass;
    }

    @Override
    public String part2() {
        return String.join("", programs);
    }
}
