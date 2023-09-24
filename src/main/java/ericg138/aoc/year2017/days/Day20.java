package ericg138.aoc.year2017.days;

import ericg138.aoc.Day;
import ericg138.aoc.year2017.beans.Particle;
import ericg138.aoc.year2017.beans.ParticleAcceleration;
import ericg138.aoc.year2017.beans.ParticlePosition;
import ericg138.aoc.year2017.beans.ParticleVelocity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day20 extends Day {
    private final List<String> input = getInputAsList();

    public Day20() {
        super(20);
    }

    private Particle createParticle(String line) {
        int firstClosingBracket = line.indexOf('>');
        int secondClosingBracket = line.indexOf('>', firstClosingBracket + 1);

        ParticlePosition p = new ParticlePosition(line.substring(line.indexOf('<') + 1, firstClosingBracket));
        ParticleVelocity v = new ParticleVelocity(
                line.substring(line.indexOf('<', firstClosingBracket) + 1, secondClosingBracket));
        ParticleAcceleration a = new ParticleAcceleration(
                line.substring(line.indexOf('<', secondClosingBracket) + 1, line.length() - 1));

        return new Particle(p, v, a);
    }

    @Override
    public String part1() {
        List<Particle> inputList = input.stream()
                .map(this::createParticle)
                .collect(Collectors.toList());

        for (int i = 0; i < 1000; i++) {
            for (Particle p : inputList) {
                p.changeVelocity();
                p.changePosition();
            }
        }

        long minDist = -1;
        int indexMinDist = 0;
        for (int i = 0; i < inputList.size(); i++) {
            Particle p = inputList.get(i);
            if (minDist == -1 || p.getManhattanDistance() < minDist) {
                minDist = p.getManhattanDistance();
                indexMinDist = i;
            }
        }

        return String.valueOf(indexMinDist);
    }

    @Override
    public String part2() {
        Set<Particle> inputSet = input.stream()
                .map(this::createParticle)
                .collect(Collectors.toSet());

        for (int i = 0; i < 40; i++) {
            for (Particle p : inputSet) {
                p.changeVelocity();
                p.changePosition();
            }

            Set<Particle> particlesToRemove = new HashSet<>();
            for (Particle p : inputSet) {
                Set<Particle> particles = inputSet.stream()
                        .filter(pa -> pa.getPosition().toString().equals(p.getPosition().toString()))
                        .collect(Collectors.toSet());

                if (particles.size() > 1) {
                    particlesToRemove.addAll(particles);
                }
            }

            inputSet.removeAll(particlesToRemove);
        }

        return String.valueOf(inputSet.size());
    }
}
