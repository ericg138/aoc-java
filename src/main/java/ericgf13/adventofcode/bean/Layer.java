package ericgf13.adventofcode.bean;

public class Layer {

	private int range;
	private int scannerPos;
	private boolean down = true;

	public Layer(int range) {
		super();
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getScannerPos() {
		return scannerPos;
	}

	public void setScannerPos(int scannerPos) {
		this.scannerPos = scannerPos;
	}

	public void moveScanner() {
		if (down) {
			if (scannerPos < range - 1) {
				scannerPos++;
			} else {
				scannerPos--;
				down = false;
			}
		} else {
			if (scannerPos > 0) {
				scannerPos--;
			} else {
				scannerPos++;
				down = true;
			}
		}
	}

	public void reset(int delay) {
		down = true;
		scannerPos = 0;
		for (int i = 0; i < delay; i++) {
			moveScanner();
		}
	}
}
