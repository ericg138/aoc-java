package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2017.runnables.Program;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Day18 extends Day {
    private final List<String> input = getInputAsList();

    public Day18() {
        super(18);
    }

    public static long getValue(String in, Map<String, Long> values) {
        if (NumberUtils.isCreatable(in)) {
            return Long.parseLong(in);
        } else {
            return values.get(in) != null ? values.get(in) : 0;
        }
    }

    @Override
    public String part1() {
        Map<String, Long> values = new HashMap<>();
        long lastSound = -1;

        int i = 0;
        boolean done = false;
        while (!done) {
            String instruction = input.get(i++);
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
                        done = true;
                    }
                    break;
                case "jgz":
                    if (getValue(x, values) > 0) {
                        i += getValue(y, values) - 1;
                    }
                    break;
            }
        }

        return String.valueOf(lastSound);
    }

    @Override
    public String part2() {
        try {
            BlockingQueue<Long> queue0 = new LinkedBlockingQueue<>();
            BlockingQueue<Long> queue1 = new LinkedBlockingQueue<>();

            Program prog0 = new Program(0, input, queue0, queue1);
            Program prog1 = new Program(1, input, queue1, queue0);

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

            return String.valueOf(prog1.getSendCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
