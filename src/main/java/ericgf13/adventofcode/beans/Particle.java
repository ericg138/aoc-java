package ericgf13.adventofcode.beans;

public class Particle {

    private ParticlePosition position;
    private ParticleVelocity velocity;
    private ParticleAcceleration acceleration;

    public Particle(ParticlePosition p, ParticleVelocity v, ParticleAcceleration a) {
        this.position = p;
        this.velocity = v;
        this.acceleration = a;
    }

    public void changeVelocity() {
        this.velocity.setX(this.velocity.getX() + this.acceleration.getX());
        this.velocity.setY(this.velocity.getY() + this.acceleration.getY());
        this.velocity.setZ(this.velocity.getZ() + this.acceleration.getZ());
    }

    public void changePosition() {
        this.position.setX(this.position.getX() + this.velocity.getX());
        this.position.setY(this.position.getY() + this.velocity.getY());
        this.position.setZ(this.position.getZ() + this.velocity.getZ());
    }

    public long getManhattanDistance() {
        return Math.abs(this.position.getX()) + Math.abs(this.position.getY()) + Math.abs(this.position.getZ());
    }

    public ParticlePosition getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "p=" + position + ",v=" + velocity + ",a=" + acceleration;
    }
}
