package ericgf13.adventofcode.runnable;

import java.util.concurrent.BlockingQueue;

public class Generator implements Runnable {

	private static int DIVISOR = 2147483647;
	private boolean running = true;
	private long value;
	private int factor;
	private int multiple;
	private BlockingQueue<Long> queue;

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
				}
			}
		}
	}

	public void shutDown() {
		running = false;
	}
}
