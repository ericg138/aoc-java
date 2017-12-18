package ericgf13.adventofcode.runnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ericgf13.adventofcode.Main;

public class Program implements Runnable {

	private long id;
	private List<String> instructions;
	private BlockingQueue<Long> queueOut;
	private BlockingQueue<Long> queueIn;
	private Lock lock = new ReentrantLock();
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
		Map<String, Long> values = new HashMap<>();
		values.put("p", id);

		int i = 0;
		loop: while (true) {
			String instruction = instructions.get(i++);
			String[] splitInstruction = instruction.split(" ");
			String x = splitInstruction[1];
			String y = splitInstruction.length > 2 ? splitInstruction[2] : null;

			switch (splitInstruction[0]) {
			case "snd":
				try {
					queueOut.put(Main.getValue(x, values));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				sendCount++;
				break;
			case "set":
				values.put(x, Main.getValue(y, values));
				break;
			case "add":
				values.put(x, Main.getValue(x, values) + Main.getValue(y, values));
				break;
			case "mul":
				values.put(x, Main.getValue(x, values) * Main.getValue(y, values));
				break;
			case "mod":
				values.put(x, Main.getValue(x, values) % Main.getValue(y, values));
				break;
			case "rcv":
				try {
					lock.lock();
					Long in = queueIn.take();
					if (in.equals(Long.MAX_VALUE)) {
						break loop;
					}
					lock.unlock();
					values.put(x, in);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case "jgz":
				if (Main.getValue(x, values) > 0) {
					i += Main.getValue(y, values) - 1;
				}
				break;
			}
		}
	}
}
