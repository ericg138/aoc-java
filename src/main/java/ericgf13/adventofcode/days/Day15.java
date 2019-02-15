package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;
import ericgf13.adventofcode.runnable.Generator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Day15 extends Day {
    public Day15() {
        super(15);
    }

    private static final int A_START = 679;
    private static final int B_START = 771;
    private static final int A_FACTOR = 16807;
    private static final int B_FACTOR = 48271;
    private static final int DIVISOR = 2147483647;

    @Override
    public String part1() {
        long a = A_START;
        long b = B_START;

        int matchCount = 0;
        for (int i = 0; i < 40_000_000; i++) {
            a = (a * A_FACTOR) % DIVISOR;
            b = (b * B_FACTOR) % DIVISOR;

            if ((a & 0xFFFF) == (b & 0xFFFF)) {
                matchCount++;
            }
        }

        return String.valueOf(matchCount);
    }

    @Override
    public String part2() {
        BlockingQueue<Long> queueA = new LinkedBlockingQueue<>();
        BlockingQueue<Long> queueB = new LinkedBlockingQueue<>();

        Generator generatorA = new Generator(A_START, A_FACTOR, 4, queueA);
        Generator generatorB = new Generator(B_START, B_FACTOR, 8, queueB);

        new Thread(generatorA).start();
        new Thread(generatorB).start();

        int matchCount = 0;
        for (int i = 0; i < 5_000_000; i++) {
            try {
                if ((queueA.take() & 0xFFFF) == (queueB.take() & 0xFFFF)) {
                    matchCount++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        generatorA.shutDown();
        generatorB.shutDown();

        return String.valueOf(matchCount);
    }
}
