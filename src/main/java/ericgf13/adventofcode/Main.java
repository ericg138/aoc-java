package ericgf13.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import ericgf13.adventofcode.bean.Bridge;
import ericgf13.adventofcode.bean.Component;
import ericgf13.adventofcode.bean.Condition;
import ericgf13.adventofcode.bean.Disk;
import ericgf13.adventofcode.bean.Instruction;
import ericgf13.adventofcode.bean.Node;
import ericgf13.adventofcode.bean.Particle;
import ericgf13.adventofcode.bean.ParticleAcceleration;
import ericgf13.adventofcode.bean.ParticlePosition;
import ericgf13.adventofcode.bean.ParticleVelocity;
import ericgf13.adventofcode.bean.StateInstruction;
import ericgf13.adventofcode.runnable.Generator;
import ericgf13.adventofcode.runnable.Program;

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
		day17();
		day18part1();
		day18part2();
		day19();
		day20part1();
		day20part2();
		day22part1();
		day22part2();
		day23();
		day24();
		day25();
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
			if (isScannerPos0(entry.getKey(), entry.getValue())) {
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

		int delay = 0;

		delay_loop: while (true) {
			delay++;
			for (Entry<Integer, Integer> entry : layers.entrySet()) {
				if (isScannerPos0(entry.getKey() + delay, entry.getValue())) {
					continue delay_loop;
				}
			}
			break;
		}

		System.out.println(delay);
	}

	private static boolean isScannerPos0(Integer delay, Integer range) {
		return delay % (range * 2 - 2) == 0;
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

	private static void day17() {
		System.out.println("===== DAY 17 =====");

		List<Integer> buffer = new ArrayList<>();
		buffer.add(0);

		int steps = 343;
		int pos = 0;

		for (int i = 1; i <= 2017; i++) {
			pos = (pos + steps) % buffer.size() + 1;
			buffer.add(pos, i);
		}

		int pos2 = 0;
		int result = 0;

		for (int i = 1; i <= 50_000_000; i++) {
			pos2 = (pos2 + steps) % i + 1;

			if (pos2 == 1) {
				result = i;
			}
		}

		System.out.println("part1=" + buffer.get(pos + 1) + " part2=" + result);
	}

	private static void day18part1() throws IOException {
		System.out.println("===== DAY 18 Part 1 =====");

		List<String> instructions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day18.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				instructions.add(line);
			}
		}

		Map<String, Long> values = new HashMap<>();
		long lastSound = -1;

		int i = 0;
		loop: while (true) {
			String instruction = instructions.get(i++);
			String[] splitInstruction = instruction.split(" ");
			String x = splitInstruction[1];
			String y = splitInstruction.length > 2 ? splitInstruction[2] : null;

			switch (splitInstruction[0]) {
			case "snd":
				lastSound = getValue(x, values);
				break;
			case "set":
				values.put(x, getValue(y, values));
				break;
			case "add":
				values.put(x, getValue(x, values) + getValue(y, values));
				break;
			case "mul":
				values.put(x, getValue(x, values) * getValue(y, values));
				break;
			case "mod":
				values.put(x, getValue(x, values) % getValue(y, values));
				break;
			case "rcv":
				if (getValue(x, values) != 0) {
					break loop;
				}
				break;
			case "jgz":
				if (getValue(x, values) > 0) {
					i += getValue(y, values) - 1;
				}
				break;
			}
		}

		System.out.println(lastSound);
	}

	private static void day18part2() throws IOException, InterruptedException {
		System.out.println("===== DAY 18 Part 2 =====");

		List<String> instructions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day18.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				instructions.add(line);
			}
		}

		BlockingQueue<Long> queue0 = new LinkedBlockingQueue<>();
		BlockingQueue<Long> queue1 = new LinkedBlockingQueue<>();

		Program prog0 = new Program(0, instructions, queue0, queue1);
		Program prog1 = new Program(1, instructions, queue1, queue0);

		new Thread(prog0).start();
		new Thread(prog1).start();

		while (true) {
			Thread.sleep(10);
			boolean lock0 = prog0.getLock().tryLock(100, TimeUnit.MILLISECONDS);
			boolean lock1 = prog1.getLock().tryLock(100, TimeUnit.MILLISECONDS);
			if (!lock0 && !lock1) {
				break;
			}
			if (lock0) {
				prog0.getLock().unlock();
			}
			if (lock1) {
				prog1.getLock().unlock();
			}
		}

		queue0.put(Long.MAX_VALUE);
		queue1.put(Long.MAX_VALUE);

		System.out.println(prog1.getSendCount());
	}

	public static long getValue(String in, Map<String, Long> values) {
		if (NumberUtils.isCreatable(in)) {
			return Long.parseLong(in);
		} else {
			return values.get(in) != null ? values.get(in) : 0;
		}
	}

	private static void day19() throws IOException {
		System.out.println("===== DAY 19 =====");

		List<String> diagram = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day19.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				diagram.add(line);
			}
		}

		int x = diagram.get(0).indexOf('|');
		int y = 0;
		int steps = 0;
		Direction dir = Direction.DOWN;
		String letters = "";

		char charac;
		while ((charac = diagram.get(y).charAt(x)) != ' ') {
			switch (charac) {
			case '+':
				if (dir == Direction.UP || dir == Direction.DOWN) {
					if (diagram.get(y).charAt(x + 1) != ' ') {
						dir = Direction.RIGHT;
						x++;
					} else if (diagram.get(y).charAt(x - 1) != ' ') {
						dir = Direction.LEFT;
						x--;
					}
				} else if (dir == Direction.RIGHT || dir == Direction.LEFT) {
					if (diagram.get(y - 1).charAt(x) != ' ') {
						dir = Direction.UP;
						y--;
					} else if (diagram.get(y + 1).charAt(x) != ' ') {
						dir = Direction.DOWN;
						y++;
					}
				}
				break;
			default:
				switch (dir) {
				case DOWN:
					y++;
					break;
				case UP:
					y--;
					break;
				case RIGHT:
					x++;
					break;
				case LEFT:
					x--;
					break;
				}
				if (charac >= 'A' && charac <= 'Z') {
					letters += charac;
				}
				break;
			}
			steps++;
		}

		System.out.println("letters=" + letters + " steps=" + steps);
	}

	public enum Direction {
		UP, DOWN, RIGHT, LEFT
	}

	private static void day20part1() throws IOException {
		System.out.println("===== DAY 20 Part 1 =====");

		List<Particle> input = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day20.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				input.add(createParticle(line));
			}
		}

		for (int i = 0; i < 1000; i++) {
			for (Particle p : input) {
				p.changeVelocity();
				p.changePosition();
			}
		}

		long minDist = -1;
		int indexMinDist = 0;
		for (int i = 0; i < input.size(); i++) {
			Particle p = input.get(i);
			if (minDist == -1 || p.getManhattanDistance() < minDist) {
				minDist = p.getManhattanDistance();
				indexMinDist = i;
			}
		}

		System.out.println(indexMinDist);
	}

	private static void day20part2() throws IOException {
		System.out.println("===== DAY 20 Part 2 =====");

		Set<Particle> input = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day20.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				input.add(createParticle(line));
			}
		}

		for (int i = 0; i < 40; i++) {
			for (Particle p : input) {
				p.changeVelocity();
				p.changePosition();
			}

			Set<Particle> particlesToRemove = new HashSet<>();
			for (Particle p : input) {
				Set<Particle> particles = input.stream()
						.filter(pa -> pa.getPosition().toString().equals(p.getPosition().toString()))
						.collect(Collectors.toSet());

				if (particles.size() > 1) {
					particlesToRemove.addAll(particles);
				}
			}

			input.removeAll(particlesToRemove);
		}

		System.out.println(input.size());
	}

	private static Particle createParticle(String line) {
		int firstClosingBracket = line.indexOf('>');
		int secondClosingBracket = line.indexOf('>', firstClosingBracket + 1);

		ParticlePosition p = new ParticlePosition(line.substring(line.indexOf('<') + 1, firstClosingBracket));
		ParticleVelocity v = new ParticleVelocity(
				line.substring(line.indexOf('<', firstClosingBracket) + 1, secondClosingBracket));
		ParticleAcceleration a = new ParticleAcceleration(
				line.substring(line.indexOf('<', secondClosingBracket) + 1, line.length() - 1));

		return new Particle(p, v, a);
	}

	private static void day22part1() throws IOException {
		System.out.println("===== DAY 22 Part 1 =====");

		Map<Node, Character> input = new HashMap<>();
		int x = 0;
		int y = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day22.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				x = 0;
				for (char c : line.toCharArray()) {
					input.put(new Node(x++, y), c);
				}
				y++;
			}
		}

		x = x / 2;
		y = y / 2;
		Direction dir = Direction.UP;
		int infections = 0;

		for (int i = 0; i < 10_000; i++) {
			Node currNode = new Node(x, y);
			input.putIfAbsent(currNode, '.');

			if (input.get(currNode) == '#') {
				input.put(currNode, '.');

				switch (dir) {
				case UP:
					x++;
					dir = Direction.RIGHT;
					break;
				case DOWN:
					x--;
					dir = Direction.LEFT;
					break;
				case RIGHT:
					y++;
					dir = Direction.DOWN;
					break;
				case LEFT:
					y--;
					dir = Direction.UP;
					break;
				}
			} else {
				infections++;
				input.put(currNode, '#');

				switch (dir) {
				case UP:
					x--;
					dir = Direction.LEFT;
					break;
				case DOWN:
					x++;
					dir = Direction.RIGHT;
					break;
				case RIGHT:
					y--;
					dir = Direction.UP;
					break;
				case LEFT:
					y++;
					dir = Direction.DOWN;
					break;
				}
			}
		}

		System.out.println(infections);
	}

	private static void day22part2() throws IOException {
		System.out.println("===== DAY 22 Part 2 =====");

		Map<Node, Character> input = new HashMap<>();
		int x = 0;
		int y = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day22.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				x = 0;
				for (char c : line.toCharArray()) {
					input.put(new Node(x++, y), c);
				}
				y++;
			}
		}

		x = x / 2;
		y = y / 2;
		Direction dir = Direction.UP;
		int infections = 0;

		for (int i = 0; i < 10_000_000; i++) {
			Node currNode = new Node(x, y);
			input.putIfAbsent(currNode, '.');

			switch (input.get(currNode)) {
			case '#':
				input.put(currNode, 'F');

				switch (dir) {
				case UP:
					x++;
					dir = Direction.RIGHT;
					break;
				case DOWN:
					x--;
					dir = Direction.LEFT;
					break;
				case RIGHT:
					y++;
					dir = Direction.DOWN;
					break;
				case LEFT:
					y--;
					dir = Direction.UP;
					break;
				}
				break;
			case '.':
				input.put(currNode, 'W');

				switch (dir) {
				case UP:
					x--;
					dir = Direction.LEFT;
					break;
				case DOWN:
					x++;
					dir = Direction.RIGHT;
					break;
				case RIGHT:
					y--;
					dir = Direction.UP;
					break;
				case LEFT:
					y++;
					dir = Direction.DOWN;
					break;
				}
				break;
			case 'W':
				infections++;
				input.put(currNode, '#');

				switch (dir) {
				case UP:
					y--;
					break;
				case DOWN:
					y++;
					break;
				case RIGHT:
					x++;
					break;
				case LEFT:
					x--;
					break;
				}
				break;
			case 'F':
				input.put(currNode, '.');

				switch (dir) {
				case UP:
					y++;
					dir = Direction.DOWN;
					break;
				case DOWN:
					y--;
					dir = Direction.UP;
					break;
				case RIGHT:
					x--;
					dir = Direction.LEFT;
					break;
				case LEFT:
					x++;
					dir = Direction.RIGHT;
					break;
				}
				break;
			}
		}

		System.out.println(infections);
	}

	private static void day23() throws IOException {
		System.out.println("===== DAY 23 =====");

		List<String> instructions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day23.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				instructions.add(line);
			}
		}

		Map<String, Long> values = new HashMap<>();
		// values.put("a", 1l);
		int mulCount = 0;

		int i = 0;
		while (i < instructions.size()) {
			String instruction = instructions.get(i++);
			String[] splitInstruction = instruction.split(" ");
			String x = splitInstruction[1];
			String y = splitInstruction.length > 2 ? splitInstruction[2] : null;

			switch (splitInstruction[0]) {
			case "set":
				values.put(x, getValue(y, values));
				break;
			case "sub":
				values.put(x, getValue(x, values) - getValue(y, values));
				break;
			case "mul":
				values.put(x, getValue(x, values) * getValue(y, values));
				mulCount++;
				break;
			case "jnz":
				if (getValue(x, values) != 0) {
					i += getValue(y, values) - 1;
				}
				break;
			}
		}

		System.out.println(mulCount);
	}

	private static void day24() throws IOException {
		System.out.println("===== DAY 24 =====");

		Set<Component> components = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day24.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				components.add(new Component(Integer.parseInt(line.substring(0, line.indexOf('/'))),
						Integer.parseInt(line.substring(line.indexOf('/') + 1))));
			}
		}

		Set<Bridge> bridges = new HashSet<>();

		components.stream().filter(c -> c.getPort1() == 0 || c.getPort2() == 0).forEach(c -> {
			Bridge b = new Bridge();
			b.getComponents().add(c);
			buildBridges(c.getOtherPort(0), components, b, bridges);
		});

		Set<Bridge> longestBridges = new HashSet<>();

		int maxLength = 0;
		for (Bridge b : bridges) {
			if (b.getComponents().size() == maxLength) {
				longestBridges.add(b);
			} else if (b.getComponents().size() > maxLength) {
				longestBridges.clear();
				longestBridges.add(b);
				maxLength = b.getComponents().size();
			}
		}

		int maxStength = bridges.stream().max(Comparator.comparing(Bridge::getStrength)).get().getStrength();
		int strengthLongest = longestBridges.stream().max(Comparator.comparing(Bridge::getStrength)).get()
				.getStrength();

		System.out.println("part1=" + maxStength + " part2=" + strengthLongest);
	}

	private static void buildBridges(int port, Set<Component> components, Bridge bridge, Set<Bridge> bridges) {
		Set<Component> componentsCompatiblePort = components.stream()
				.filter(c -> !bridge.getComponents().contains(c) && (c.getPort1() == port || c.getPort2() == port))
				.collect(Collectors.toSet());

		if (componentsCompatiblePort.isEmpty()) {
			bridges.add(bridge);
		} else {
			for (Component c : componentsCompatiblePort) {
				Bridge b = bridge.clone();
				b.getComponents().add(c);
				buildBridges(c.getOtherPort(port), components, b, bridges);
			}
		}
	}

	private static void day25() throws IOException {
		System.out.println("===== DAY 25 =====");

		Map<String, StateInstruction> stateInstructions = new HashMap<>();
		int steps = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_DIRECTORY + "day25.txt"))) {
			String line;
			StateInstruction ins = null;
			int val = 0;
			while ((line = br.readLine()) != null) {
				line = line.trim();

				if (line.startsWith("Perform a diagnostic checksum after")) {
					steps = Integer.parseInt(line.substring(36, line.indexOf(' ', 36)));
				} else if (line.startsWith("In state")) {
					ins = new StateInstruction();
					stateInstructions.put(line.substring(9, 10), ins);
				} else if (line.startsWith("If the current value is")) {
					val = Integer.parseInt(line.substring(24, 25));
				} else if (line.startsWith("- Write the value")) {
					ins.setWriteVal(val, line.substring(18, 19));
				} else if (line.startsWith("- Move one slot to the")) {
					if (line.substring(23, 28).equals("right")) {
						ins.setMoveDir(val, Direction.RIGHT);
					} else {
						ins.setMoveDir(val, Direction.LEFT);
					}
				} else if (line.startsWith("- Continue with state")) {
					ins.setNextState(val, line.substring(22, 23));
				}
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

			StateInstruction ins = stateInstructions.get(state);
			int currVal = tape.get(pos);

			tape.set(pos, ins.getWriteVal(currVal));
			if (ins.getMoveDir(currVal) == Direction.RIGHT) {
				pos++;
			} else {
				pos--;
			}
			state = ins.getNextState(currVal);
		}

		System.out.println(tape.stream().filter(i -> i == 1).count());
	}
}
