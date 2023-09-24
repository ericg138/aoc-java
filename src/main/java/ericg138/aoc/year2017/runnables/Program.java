package ericg138.aoc.year2017.runnables;

import ericg138.aoc.year2017.days.Day18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Program implements Runnable {

    private final long id;
    private final List<String> instructions;
    private final BlockingQueue<Long> queueOut;
    private final BlockingQueue<Long> queueIn;
    private final Lock lock = new ReentrantLock();
    private int sendCount;

    public Program(long id, List<String> instructions, BlockingQueue<Long> queueOut, BlockingQueue<Long> queueIn) {
        this.id = id;
        this.instructions = instructions;
        this.queueOut = queueOut;
        this.queueIn = queueIn;
    }

    public Lock getLock() {
        return lock;
    }

    public int getSendCount() {
        return sendCount;
    }

    @Override
    public void run() {
        try {
            Map<String, Long> values = new HashMap<>();
            values.put("p", id);

            int i = 0;
            boolean done = false;
            while (!done) {
                String instruction = instructions.get(i++);
                String[] splitInstruction = instruction.split(" ");
                String x = splitInstruction[1];
                String y = splitInstruction.length > 2 ? splitInstruction[2] : null;

                switch (splitInstruction[0]) {
                    case "snd":
                        queueOut.put(Day18.getValue(x, values));
                        sendCount++;
                        break;
                    case "set":
                        values.put(x, Day18.getValue(y, values));
                        break;
                    case "add":
                        values.put(x, Day18.getValue(x, values) + Day18.getValue(y, values));
                        break;
                    case "mul":
                        values.put(x, Day18.getValue(x, values) * Day18.getValue(y, values));
                        break;
                    case "mod":
                        values.put(x, Day18.getValue(x, values) % Day18.getValue(y, values));
                        break;
                    case "rcv":
                        lock.lock();
                        Long in = queueIn.take();
                        if (in.equals(Long.MAX_VALUE)) {
                            done = true;
                        }
                        lock.unlock();
                        values.put(x, in);
                        break;
                    case "jgz":
                        if (Day18.getValue(x, values) > 0) {
                            i += Day18.getValue(y, values) - 1;
                        }
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
