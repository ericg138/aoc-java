package ericgf13.adventofcode.bean;

public abstract class ParticleElement {

	private long x;
	private long y;
	private long z;

	public ParticleElement(String in) {
		int firstComma = in.indexOf(',');
		int secondComma = in.indexOf(',', firstComma + 1);

		this.x = Integer.parseInt(in.substring(0, firstComma));
		this.y = Integer.parseInt(in.substring(firstComma + 1, secondComma));
		this.z = Integer.parseInt(in.substring(secondComma + 1));
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return z;
	}

	public void setZ(long z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return x + "," + y + "," + z;
	}
}
