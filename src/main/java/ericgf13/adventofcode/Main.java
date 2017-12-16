package ericgf13.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import ericgf13.adventofcode.bean.Condition;
import ericgf13.adventofcode.bean.Disk;
import ericgf13.adventofcode.bean.Instruction;

public class Main {

	private static String INPUT_DIRECTORY = "src/main/resources/";

	public static void main(String[] args) throws IOException, InterruptedException {
		day1();
		day2();
		day3();
		day4();
		day5();
		day6();
		day7();
		day8();
		day9();
		day10part1();
		day10part2();
		day11();
		day12();
		day13part1();
		day13part2();
		day14();
		day15();
		day16();
	}

	private static void day1() throws IOException {
		System.out.println("===== DAY 1 =====");

		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day1.txt"))) {
			line = br.readLine();
		}

		int sumPart1 = 0;
		int sumPart2 = 0;
		char previousDigit = 0;
		int steps = line.length() / 2;
		int i = 0;

		for (char digit : line.toCharArray()) {
			if (digit == previousDigit) {
				sumPart1 += Character.getNumericValue(digit);
			}
			previousDigit = digit;

			int indexToCheck;
			if (i + steps >= line.length()) {
				indexToCheck = i + steps - line.length();
			} else {
				indexToCheck = i + steps;
			}

			if (digit == line.charAt(indexToCheck)) {
				sumPart2 += Character.getNumericValue(digit);
			}
			i++;
		}

		char firstDigit = line.charAt(0);
		if (firstDigit == previousDigit) {
			sumPart1 += Character.getNumericValue(firstDigit);
		}

		System.out.println("sumPart1=" + sumPart1 + " sumPart2=" + sumPart2);
	}

	private static void day2() throws IOException {
		System.out.println("===== DAY 2 =====");

		List<String[]> matrix = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day2.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				matrix.add(line.split("\t"));
			}
		}

		int sumPart1 = 0;
		int sumPart2 = 0;

		for (String[] line : matrix) {
			int min = -1;
			int max = -1;
			for (String num : line) {
				int intNum = Integer.parseInt(num);
				if (min == -1 || intNum < min) {
					min = intNum;
				}
				if (max == -1 || intNum > max) {
					max = intNum;
				}
			}
			sumPart1 += max - min;

			loop: for (String num : line) {
				int intNum = Integer.parseInt(num);
				for (String num2 : line) {
					int intNum2 = Integer.parseInt(num2);
					if (intNum != intNum2 && intNum % intNum2 == 0) {
						sumPart2 += intNum / intNum2;
						break loop;
					}
				}
			}
		}

		System.out.println("sumPart1=" + sumPart1 + " sumPart2=" + sumPart2);
	}

	private static void day3() {
		System.out.println("===== DAY 3 =====");

		int max = 368078;
		int steps = 0;

		int square = (int) Math.ceil(Math.sqrt(max));
		if (square % 2 == 0) {
			square++;
		}
		int offset = square / 2;

		int bottomRightValue = (int) Math.pow(square, 2);
		int bottomLeftValue = bottomRightValue - square + 1;
		int topLeftValue = bottomLeftValue - square + 1;
		int topRightValue = topLeftValue - square + 1;

		int topValue;
		if (bottomLeftValue < max) {
			topValue = bottomRightValue;
		} else if (topLeftValue < max) {
			topValue = bottomLeftValue;
		} else if (topRightValue < max) {
			topValue = topLeftValue;
		} else {
			topValue = topRightValue;
		}

		int delta = topValue - max;

		if (delta > offset) {
			steps += delta - offset;
		} else {
			steps += offset - delta;
		}

		steps += offset;

		System.out.println(steps);
	}

	private static void day4() throws IOException {
		System.out.println("===== DAY 4 =====");

		int nbValid = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day4.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");

				Set<String> checker = new HashSet<>();
				boolean valid = true;
				for (String word : words) {
					word = sortString(word); // Part2
					if (checker.contains(word)) {
						valid = false;
						break;
					}
					checker.add(word);
				}

				if (valid) {
					nbValid++;
				}
			}
		}

		System.out.println(nbValid);
	}

	private static String sortString(String word) {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	private static void day5() throws IOException {
		System.out.println("===== DAY 5 =====");

		List<Integer> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day5.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				input.add(Integer.parseInt(line));
			}
		}

		int length = input.size();
		int pos = 0;
		int steps = 0;

		while (pos < length) {
			int jump = input.get(pos);
			// if (jump >= 3) {
			// input.add(pos, jump - 1);
			// } else {
			input.set(pos, jump + 1);
			// }
			pos = pos + jump;
			steps++;
		}

		System.out.println(steps);
	}

	private static void day6() throws IOException {
		System.out.println("===== DAY 6 =====");

		List<Integer> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day6.txt"))) {
			String line = br.readLine();
			String[] strNums = line.split("\t");
			for (String s : strNums) {
				input.add(Integer.parseInt(s));
			}
		}

		int steps = 0;
		int inputSize = input.size();
		List<String> snapshots = new ArrayList<>();

		String snapshot = getSnapshot(input);
		while (!snapshots.contains(snapshot)) {
			snapshots.add(snapshot);

			int indexWithMaxVal = -1;
			int maxVal = -1;
			for (int i = 0; i < inputSize; i++) {
				if (input.get(i) > maxVal) {
					indexWithMaxVal = i;
					maxVal = input.get(i);
				}
			}

			input.set(indexWithMaxVal, 0);

			int i = indexWithMaxVal == inputSize - 1 ? 0 : indexWithMaxVal + 1;
			while (maxVal > 0) {
				input.set(i, input.get(i) + 1);

				if (i == inputSize - 1) {
					i = 0;
				} else {
					i++;
				}

				maxVal--;
			}

			steps++;
			snapshot = getSnapshot(input);
		}

		int cycles = snapshots.size() - snapshots.indexOf(snapshot);

		System.out.println("steps=" + steps + " cycles=" + cycles);
	}

	private static String getSnapshot(List<Integer> input) {
		StringBuilder snapshot = new StringBuilder();
		input.stream().forEach(num -> snapshot.append(num).append(','));
		return snapshot.toString();
	}

	private static Disk badDiskParent;

	private static void day7() throws IOException {
		System.out.println("===== DAY 7 =====");

		Set<Disk> input = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day7.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String name = line.substring(0, line.indexOf(" "));
				String weight = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
				Disk disk = new Disk(name, Integer.parseInt(weight));
				if (line.contains("->")) {
					String childrenStr = line.substring(line.indexOf("->") + 3).replace(" ", "");
					Set<String> children = new HashSet<>(Arrays.asList(childrenStr.split(",")));
					disk.setChildrenStr(children);
				}
				input.add(disk);
			}
		}

		// Set parent and children on each disk
		for (Disk disk : input) {
			if (disk.getChildrenStr() != null) {
				for (String child : disk.getChildrenStr()) {
					Disk childDisk = input.stream().filter(d -> d.getName().equals(child)).findAny().orElse(null);
					childDisk.setParent(disk);
					disk.getChildren().add(childDisk);
				}
			}
		}

		Disk root = input.stream().filter(d -> d.getParent() == null).findAny().orElse(null);
		calculateTrueWeight(root);

		int correctWeight = 0;
		for (Disk child : badDiskParent.getChildren()) {
			if (badDiskParent.getChildren().stream()
					.noneMatch(d -> d.getTrueWeight() == child.getTrueWeight() && d.getName() != child.getName())) {
				Disk goodDisk = badDiskParent.getChildren().stream()
						.filter(d -> d.getTrueWeight() != child.getTrueWeight()).findAny().orElse(null);
				correctWeight = child.getWeight() + goodDisk.getTrueWeight() - child.getTrueWeight();
			}
		}

		System.out.println("root=" + root.getName() + " correctWeight=" + correctWeight);
	}

	private static int calculateTrueWeight(Disk disk) {
		int trueWeight = disk.getWeight();

		int previousChildTrueWeight = -1;
		for (Disk child : disk.getChildren()) {
			int childTrueWeight = calculateTrueWeight(child);
			trueWeight += childTrueWeight;

			if (previousChildTrueWeight != -1 && previousChildTrueWeight != childTrueWeight) {
				if (badDiskParent == null) {
					badDiskParent = disk;
				}
			}
			previousChildTrueWeight = child.getTrueWeight();
		}

		disk.setTrueWeight(trueWeight);
		return trueWeight;
	}

	private static void day8() throws IOException {
		System.out.println("===== DAY 8 =====");

		Map<String, Integer> registers = new HashMap<>();
		List<Instruction> instructions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day8.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				registers.put(split[0], 0);

				boolean increment = split[1].equals("inc") ? true : false;
				Condition condition = new Condition(split[4], split[5], Integer.parseInt(split[6]));
				Instruction instruction = new Instruction(split[0], increment, Integer.parseInt(split[2]), condition);
				instructions.add(instruction);
			}
		}

		int highestValEver = 0;
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

		int highestVal = registers.values().stream().reduce(Integer::max).get();

		System.out.println("highestVal=" + highestVal + " highestValEver=" + highestValEver);
	}

	private static void day9() throws IOException {
		System.out.println("===== DAY 9 =====");

		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day9.txt"))) {
			line = br.readLine();
		}

		int groupVal = 1;
		int score = 0;
		int garbageCount = 0;
		boolean inGarbage = false;
		boolean ignore = false;

		for (char charac : line.toCharArray()) {
			if (ignore) {
				ignore = false;
				continue;
			}

			if (charac == '!') {
				ignore = true;
			} else if (!inGarbage && charac == '<') {
				inGarbage = true;
			} else if (charac == '>') {
				inGarbage = false;
			} else if (!inGarbage && charac == '{') {
				score += groupVal;
				groupVal++;
			} else if (!inGarbage && charac == '}') {
				groupVal--;
			} else if (inGarbage) {
				garbageCount++;
			}
		}

		System.out.println("score=" + score + " garbageCount=" + garbageCount);
	}

	private static void day10part1() throws IOException {
		System.out.println("===== DAY 10 Part 1 =====");

		List<Integer> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day10.txt"))) {
			String line = br.readLine();
			for (String num : line.split(",")) {
				input.add(Integer.parseInt(num));
			}
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			list.add(i);
		}

		int pos = 0;
		int skipSize = 0;

		for (int length : input) {

			List<Integer> subList = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				subList.add(list.get((pos + i) % list.size()));
			}

			Collections.reverse(subList);

			for (int i = 0; i < length; i++) {
				list.set((pos + i) % list.size(), subList.get(0));
				subList.remove(0);
			}

			pos = (pos + length) % list.size() + skipSize++;
		}

		int result = list.get(0) * list.get(1);

		System.out.println(result);
	}

	private static void day10part2() throws IOException {
		System.out.println("===== DAY 10 Part 2 =====");

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day10.txt"))) {
			System.out.println(knotHash(br.readLine()));
		}
	}

	private static String knotHash(String inputString) {
		List<Integer> input = new ArrayList<>();
		for (char c : inputString.toCharArray()) {
			input.add((int) c);
		}
		input.addAll(Arrays.asList(17, 31, 73, 47, 23));

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			list.add(i);
		}

		int pos = 0;
		int skipSize = 0;

		for (int y = 0; y < 64; y++) {
			for (int length : input) {

				List<Integer> subList = new ArrayList<>();
				for (int i = 0; i < length; i++) {
					subList.add(list.get((pos + i) % list.size()));
				}

				Collections.reverse(subList);

				for (int i = 0; i < length; i++) {
					list.set((pos + i) % list.size(), subList.get(0));
					subList.remove(0);
				}

				pos = (pos + length) % list.size() + skipSize++;
			}
		}

		List<Integer> denseHash = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			int hash = 0;
			for (int y = i * 16; y < (i + 1) * 16; y++) {
				hash ^= list.get(y);
			}
			denseHash.add(hash);
		}

		return denseHash.stream().map(p -> String.format("%02x", p)).collect(Collectors.joining());
	}

	private static void day11() throws IOException {
		System.out.println("===== DAY 11 =====");

		List<String> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day11.txt"))) {
			String line = br.readLine();
			for (String dir : line.split(",")) {
				input.add(dir);
			}
		}

		int x = 0;
		int y = 0;
		int steps = 0;
		int mostSteps = 0;

		for (String dir : input) {
			switch (dir) {
			case "n":
				y += 2;
				break;
			case "ne":
				y++;
				x++;
				break;
			case "nw":
				y++;
				x--;
				break;
			case "s":
				y -= 2;
				break;
			case "se":
				y--;
				x++;
				break;
			case "sw":
				y--;
				x--;
				break;
			}

			int xAbs = Math.abs(x);
			int yAbs = Math.abs(y);

			steps = xAbs + (yAbs - xAbs) / 2;

			if (steps > mostSteps) {
				mostSteps = steps;
			}
		}

		System.out.println("steps=" + steps + " mostSteps=" + mostSteps);
	}

	private static void day12() throws IOException {
		System.out.println("===== DAY 12 =====");

		Map<Integer, Set<Integer>> input = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day12.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				Integer num = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				Set<Integer> links = new HashSet<>();
				for (String s : line.substring(line.indexOf("<->") + 4).split(", ")) {
					links.add(Integer.parseInt(s));
				}
				links.removeIf(l -> l.equals(num));
				input.put(num, links);
			}
		}

		Set<Integer> connected = new HashSet<>();
		connected.add(0);

		findConnected(0, input, connected, new HashSet<>());

		int nbLinkTo0 = connected.size();
		int nbGroups = 1;

		for (int num : input.keySet()) {
			if (!connected.contains(num)) {
				findConnected(num, input, connected, new HashSet<>());
				nbGroups++;
			}
		}

		System.out.println("nbLinkTo0=" + nbLinkTo0 + " nbGroups=" + nbGroups);
	}

	private static void findConnected(int num, Map<Integer, Set<Integer>> input, Set<Integer> connected,
			Set<Integer> processed) {
		for (int link : input.get(num)) {
			if (!processed.contains(link)) {
				processed.add(link);
				connected.add(link);
				findConnected(link, input, connected, processed);
			}
		}
	}

	private static void day13part1() throws IOException {
		System.out.println("===== DAY 13 Part 1 =====");

		Map<Integer, Integer> layers = new LinkedHashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day13.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				int depth = Integer.parseInt(line.substring(0, line.indexOf(":")));
				int range = Integer.parseInt(line.substring(line.indexOf(":") + 2));
				layers.put(depth, range);
			}
		}

		int severity = 0;

		for (Entry<Integer, Integer> entry : layers.entrySet()) {
			if (moveScanner(entry.getKey(), entry.getValue()) == 0) {
				severity += entry.getValue() * entry.getKey();
			}
		}

		System.out.println(severity);
	}

	private static void day13part2() throws IOException {
		System.out.println("===== DAY 13 Part 2 =====");

		Map<Integer, Integer> layers = new LinkedHashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day13.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				int depth = Integer.parseInt(line.substring(0, line.indexOf(":")));
				int range = Integer.parseInt(line.substring(line.indexOf(":") + 2));
				layers.put(depth, range);
			}
		}

		// int delay = -1;
		int delay = 3875830;

		delay_loop: while (true) {
			delay++;
			for (Entry<Integer, Integer> entry : layers.entrySet()) {
				if (moveScanner(entry.getKey() + delay, entry.getValue()) == 0) {
					continue delay_loop;
				}
			}
			break;
		}

		System.out.println(delay);
	}

	private static int moveScanner(Integer delay, Integer range) {
		// TODO optimize
		int pos = 0;
		boolean down = true;
		for (int i = 0; i < delay; i++) {
			if (down) {
				if (pos < range - 1) {
					pos++;
				} else {
					pos--;
					down = false;
				}
			} else {
				if (pos > 0) {
					pos--;
				} else {
					pos++;
					down = true;
				}
			}
		}
		return pos;
	}

	private static void day14() {
		System.out.println("===== DAY 14 =====");

		String input = "hxtvlmkl";
		List<String> list = new ArrayList<>();

		for (int i = 0; i < 128; i++) {
			list.add(input + "-" + i);
		}

		List<String> hashList = list.stream().map(p -> knotHash(p)).collect(Collectors.toList());
		List<String> binaryHashList = hashList.stream().map(p -> new BigInteger(p, 16).toString(2))
				.collect(Collectors.toList());

		int squareCount = 0;
		for (String binaryHash : binaryHashList) {
			squareCount += binaryHash.replace("0", "").length();
		}

		System.out.println(squareCount);
	}

	private static void day15() throws InterruptedException {
		System.out.println("===== DAY 15 =====");

		long a = 679;
		long b = 771;
		int aFactor = 16807;
		int bFactor = 48271;
		int divisor = 2147483647;

		int matchCount = 0;
		for (int i = 0; i < 40_000_000; i++) {
			a = (a * aFactor) % divisor;
			b = (b * bFactor) % divisor;

			if ((a & 0xFFFF) == (b & 0xFFFF)) {
				matchCount++;
			}
		}

		BlockingQueue<Long> queueA = new LinkedBlockingQueue<>();
		BlockingQueue<Long> queueB = new LinkedBlockingQueue<>();

		Generator generatorA = new Generator(679, aFactor, 4, queueA);
		Generator generatorB = new Generator(771, bFactor, 8, queueB);

		new Thread(generatorA).start();
		new Thread(generatorB).start();

		int matchCount2 = 0;
		for (int i = 0; i < 5_000_000; i++) {
			if ((queueA.take() & 0xFFFF) == (queueB.take() & 0xFFFF)) {
				matchCount2++;
			}
		}

		generatorA.shutDown();
		generatorB.shutDown();

		System.out.println("part1=" + matchCount + " part2=" + matchCount2);
	}

	private static void day16() throws IOException {
		System.out.println("===== DAY 16 =====");

		List<String> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day16.txt"))) {
			input.addAll(Arrays.asList(br.readLine().split(",")));
		}

		List<String> programs = new ArrayList<>();
		for (char c = 97; c < 113; c++) {
			programs.add(String.valueOf(c));
		}
		String init = programs.stream().collect(Collectors.joining());

		String firstPass = null;
		int i = 0;
		do {
			i++;
			dance(input, programs);
			if (firstPass == null) {
				firstPass = programs.stream().collect(Collectors.joining());
			}
		} while (!programs.stream().collect(Collectors.joining()).equals(init));

		for (int y = 0; y < 1_000_000_000 % i; y++) {
			dance(input, programs);
		}

		System.out.println("part1=" + firstPass + " part2=" + programs.stream().collect(Collectors.joining()));
	}

	private static void dance(List<String> input, List<String> programs) {
		for (String instruction : input) {
			switch (instruction.charAt(0)) {
			case 's':
				int lengthToMove = Integer.parseInt(instruction.substring(1));
				List<String> listToMove = new ArrayList<>(
						programs.subList(programs.size() - lengthToMove, programs.size()));
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
}
