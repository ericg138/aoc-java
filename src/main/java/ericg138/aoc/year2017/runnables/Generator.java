package ericg138.aoc.year2017.runnables;

import java.util.concurrent.BlockingQueue;

public class Generator implements Runnable {

    private static final int DIVISOR = 2147483647;
    private final int factor;
    private final int multiple;
    private final BlockingQueue<Long> queue;
    private boolean running = true;
    private long value;

    public Generator(long value, int factor, int multiple, BlockingQueue<Long> queue) {
        this.value = value;
        this.factor = factor;
        this.multiple = multiple;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            value = (value * factor) % DIVISOR;
            if (value % multiple == 0) {
                try {
                    queue.put(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void shutDown() {
        running = false;
    }
}
