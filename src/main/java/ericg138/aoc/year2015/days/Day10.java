package ericg138.aoc.year2015.days;

import ericg138.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 extends Day {
    private final String input = getInputAsString();

    public Day10() {
        super(10);
    }

    @Override
    public String part1() {
        String current = input;
        for (int iteration = 0; iteration < 40; iteration++) {
            current = transform(current);
        }
        return "" + current.length();
    }

    private String transform(String current) {
        int index = 0;
        StringBuilder result = new StringBuilder();
        while (index < current.length()) {
            char currentChar = current.charAt(index);
            int occurences = 1;
            if (index != current.length() - 1 && current.charAt(index + 1) == currentChar) {
                occurences++;
                if (index != current.length() - 2 && current.charAt(index + 2) == currentChar) {
                    occurences++;
                }
            }
            result.append(occurences).append(currentChar);
            index += occurences;
        }
        return result.toString();
    }

    @Override
    public String part2() {
        Map<String, String> elementMap = new HashMap<>();
        elementMap.put("H", "22");
        elementMap.put("He", "13112221133211322112211213322112");
        elementMap.put("Li", "312211322212221121123222112");
        elementMap.put("Be", "111312211312113221133211322112211213322112");
        elementMap.put("B", "1321132122211322212221121123222112");
        elementMap.put("C", "3113112211322112211213322112");
        elementMap.put("N", "111312212221121123222112");
        elementMap.put("O", "132112211213322112");
        elementMap.put("F", "31121123222112");
        elementMap.put("Ne", "111213322112");
        elementMap.put("Na", "123222112");
        elementMap.put("Mg", "3113322112");
        elementMap.put("Al", "1113222112");
        elementMap.put("Si", "1322112");
        elementMap.put("P", "311311222112");
        elementMap.put("S", "1113122112");
        elementMap.put("Cl", "132112");
        elementMap.put("Ar", "3112");
        elementMap.put("K", "1112");
        elementMap.put("Ca", "12");
        elementMap.put("Sc", "3113112221133112");
        elementMap.put("Ti", "11131221131112");
        elementMap.put("V", "13211312");
        elementMap.put("Cr", "31132");
        elementMap.put("Mn", "111311222112");
        elementMap.put("Fe", "13122112");
        elementMap.put("Co", "32112");
        elementMap.put("Ni", "11133112");
        elementMap.put("Cu", "131112");
        elementMap.put("Zn", "312");
        elementMap.put("Ga", "13221133122211332");
        elementMap.put("Ge", "31131122211311122113222");
        elementMap.put("As", "11131221131211322113322112");
        elementMap.put("Se", "13211321222113222112");
        elementMap.put("Br", "3113112211322112");
        elementMap.put("Kr", "11131221222112");
        elementMap.put("Rb", "1321122112");
        elementMap.put("Sr", "3112112");
        elementMap.put("Y", "1112133");
        elementMap.put("Zr", "12322211331222113112211");
        elementMap.put("Nb", "1113122113322113111221131221");
        elementMap.put("Mo", "13211322211312113211");
        elementMap.put("Tc", "311322113212221");
        elementMap.put("Ru", "132211331222113112211");
        elementMap.put("Rh", "311311222113111221131221");
        elementMap.put("Pd", "111312211312113211");
        elementMap.put("Ag", "132113212221");
        elementMap.put("Cd", "3113112211");
        elementMap.put("In", "11131221");
        elementMap.put("Sn", "13211");
        elementMap.put("Sb", "3112221");
        elementMap.put("Te", "1322113312211");
        elementMap.put("I", "311311222113111221");
        elementMap.put("Xe", "11131221131211");
        elementMap.put("Cs", "13211321");
        elementMap.put("Ba", "311311");
        elementMap.put("La", "11131");
        elementMap.put("Ce", "1321133112");
        elementMap.put("Pr", "31131112");
        elementMap.put("Nd", "111312");
        elementMap.put("Pm", "132");
        elementMap.put("Sm", "311332");
        elementMap.put("Eu", "1113222");
        elementMap.put("Gd", "13221133112");
        elementMap.put("Tb", "3113112221131112");
        elementMap.put("Dy", "111312211312");
        elementMap.put("Ho", "1321132");
        elementMap.put("Er", "311311222");
        elementMap.put("Tm", "11131221133112");
        elementMap.put("Yb", "1321131112");
        elementMap.put("Lu", "311312");
        elementMap.put("Hf", "11132");
        elementMap.put("Ta", "13112221133211322112211213322113");
        elementMap.put("W", "312211322212221121123222113");
        elementMap.put("Re", "111312211312113221133211322112211213322113");
        elementMap.put("Os", "1321132122211322212221121123222113");
        elementMap.put("Ir", "3113112211322112211213322113");
        elementMap.put("Pt", "111312212221121123222113");
        elementMap.put("Au", "132112211213322113");
        elementMap.put("Hg", "31121123222113");
        elementMap.put("Tl", "111213322113");
        elementMap.put("Pb", "123222113");
        elementMap.put("Bi", "3113322113");
        elementMap.put("Po", "1113222113");
        elementMap.put("At", "1322113");
        elementMap.put("Rn", "311311222113");
        elementMap.put("Fr", "1113122113");
        elementMap.put("Ra", "132113");
        elementMap.put("Ac", "3113");
        elementMap.put("Th", "1113");
        elementMap.put("Pa", "13");
        elementMap.put("U", "3");

        Map<String, List<String>> decayMap = new HashMap<>();
        decayMap.put("H", List.of("H"));
        decayMap.put("He", List.of("Hf", "Pa", "H", "Ca", "Li"));
        decayMap.put("Li", List.of("He"));
        decayMap.put("Be", List.of("Ge", "Ca", "Li"));
        decayMap.put("B", List.of("Be"));
        decayMap.put("C", List.of("B"));
        decayMap.put("N", List.of("C"));
        decayMap.put("O", List.of("N"));
        decayMap.put("F", List.of("O"));
        decayMap.put("Ne", List.of("F"));
        decayMap.put("Na", List.of("Ne"));
        decayMap.put("Mg", List.of("Pm", "Na"));
        decayMap.put("Al", List.of("Mg"));
        decayMap.put("Si", List.of("Al"));
        decayMap.put("P", List.of("Ho", "Si"));
        decayMap.put("S", List.of("P"));
        decayMap.put("Cl", List.of("S"));
        decayMap.put("Ar", List.of("Cl"));
        decayMap.put("K", List.of("Ar"));
        decayMap.put("Ca", List.of("K"));
        decayMap.put("Sc", List.of("Ho", "Pa", "H", "Ca", "Co"));
        decayMap.put("Ti", List.of("Sc"));
        decayMap.put("V", List.of("Ti"));
        decayMap.put("Cr", List.of("V"));
        decayMap.put("Mn", List.of("Cr", "Si"));
        decayMap.put("Fe", List.of("Mn"));
        decayMap.put("Co", List.of("Fe"));
        decayMap.put("Ni", List.of("Zn", "Co"));
        decayMap.put("Cu", List.of("Ni"));
        decayMap.put("Zn", List.of("Cu"));
        decayMap.put("Ga", List.of("Eu", "Ca", "Ac", "H", "Ca", "Zn"));
        decayMap.put("Ge", List.of("Ho", "Ga"));
        decayMap.put("As", List.of("Ge", "Na"));
        decayMap.put("Se", List.of("As"));
        decayMap.put("Br", List.of("Se"));
        decayMap.put("Kr", List.of("Br"));
        decayMap.put("Rb", List.of("Kr"));
        decayMap.put("Sr", List.of("Rb"));
        decayMap.put("Y", List.of("Sr", "U"));
        decayMap.put("Zr", List.of("Y", "H", "Ca", "Tc"));
        decayMap.put("Nb", List.of("Er", "Zr"));
        decayMap.put("Mo", List.of("Nb"));
        decayMap.put("Tc", List.of("Mo"));
        decayMap.put("Ru", List.of("Eu", "Ca", "Tc"));
        decayMap.put("Rh", List.of("Ho", "Ru"));
        decayMap.put("Pd", List.of("Rh"));
        decayMap.put("Ag", List.of("Pd"));
        decayMap.put("Cd", List.of("Ag"));
        decayMap.put("In", List.of("Cd"));
        decayMap.put("Sn", List.of("In"));
        decayMap.put("Sb", List.of("Pm", "Sn"));
        decayMap.put("Te", List.of("Eu", "Ca", "Sb"));
        decayMap.put("I", List.of("Ho", "Te"));
        decayMap.put("Xe", List.of("I"));
        decayMap.put("Cs", List.of("Xe"));
        decayMap.put("Ba", List.of("Cs"));
        decayMap.put("La", List.of("Ba"));
        decayMap.put("Ce", List.of("La", "H", "Ca", "Co"));
        decayMap.put("Pr", List.of("Ce"));
        decayMap.put("Nd", List.of("Pr"));
        decayMap.put("Pm", List.of("Nd"));
        decayMap.put("Sm", List.of("Pm", "Ca", "Zn"));
        decayMap.put("Eu", List.of("Sm"));
        decayMap.put("Gd", List.of("Eu", "Ca", "Co"));
        decayMap.put("Tb", List.of("Ho", "Gd"));
        decayMap.put("Dy", List.of("Tb"));
        decayMap.put("Ho", List.of("Dy"));
        decayMap.put("Er", List.of("Ho", "Pm"));
        decayMap.put("Tm", List.of("Er", "Ca", "Co"));
        decayMap.put("Yb", List.of("Tm"));
        decayMap.put("Lu", List.of("Yb"));
        decayMap.put("Hf", List.of("Lu"));
        decayMap.put("Ta", List.of("Hf", "Pa", "H", "Ca", "W"));
        decayMap.put("W", List.of("Ta"));
        decayMap.put("Re", List.of("Ge", "Ca", "W"));
        decayMap.put("Os", List.of("Re"));
        decayMap.put("Ir", List.of("Os"));
        decayMap.put("Pt", List.of("Ir"));
        decayMap.put("Au", List.of("Pt"));
        decayMap.put("Hg", List.of("Au"));
        decayMap.put("Tl", List.of("Hg"));
        decayMap.put("Pb", List.of("Tl"));
        decayMap.put("Bi", List.of("Pm", "Pb"));
        decayMap.put("Po", List.of("Bi"));
        decayMap.put("At", List.of("Po"));
        decayMap.put("Rn", List.of("Ho", "At"));
        decayMap.put("Fr", List.of("Rn"));
        decayMap.put("Ra", List.of("Fr"));
        decayMap.put("Ac", List.of("Ra"));
        decayMap.put("Th", List.of("Ac"));
        decayMap.put("Pa", List.of("Th"));
        decayMap.put("U", List.of("Pa"));

        Map<String, Integer> sizeByElement = new HashMap<>();
        for (Map.Entry<String, String> entry : elementMap.entrySet()) {
            sizeByElement.put(entry.getKey(), entry.getValue().length());
        }

        List<String> elements = List.of("Po");
        for (int i = 0; i < 50; i++) {
            elements = elements.stream().flatMap(e -> decayMap.get(e).stream()).toList();
        }

        return "" + elements.stream().map(sizeByElement::get).mapToInt(Number::intValue).sum();
    }
}
