package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;
import ericgf13.adventofcode.bean.Disk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day07 extends Day {

    private Set<Disk> disks = new HashSet<>();
    private Disk badDiskParent;

    public Day07() {
        super(7);

        for (String line : getInputAsList()) {
            String name = line.substring(0, line.indexOf(" "));
            String weight = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
            Disk disk = new Disk(name, Integer.parseInt(weight));
            if (line.contains("->")) {
                String childrenStr = line.substring(line.indexOf("->") + 3).replace(" ", "");
                Set<String> children = new HashSet<>(Arrays.asList(childrenStr.split(",")));
                disk.setChildrenStr(children);
            }
            disks.add(disk);
        }

        // Set parent and children on each disk
        for (Disk disk : disks) {
            if (disk.getChildrenStr() != null) {
                for (String child : disk.getChildrenStr()) {
                    Disk childDisk = disks.stream().filter(d -> d.getName().equals(child)).findAny().orElse(null);
                    childDisk.setParent(disk);
                    disk.getChildren().add(childDisk);
                }
            }
        }
    }

    @Override
    public String part1() {
        Disk root = disks.stream().filter(d -> d.getParent() == null).findAny().orElse(null);
        return root.getName();
    }

    @Override
    public String part2() {
        Disk root = disks.stream().filter(d -> d.getParent() == null).findAny().orElse(null);
        calculateTrueWeight(root);

        int correctWeight = 0;
        for (Disk child : badDiskParent.getChildren()) {
            if (badDiskParent.getChildren().stream()
                    .noneMatch(d -> d.getTrueWeight() == child.getTrueWeight() && !d.getName().equals(child.getName()))) {
                Disk goodDisk = badDiskParent.getChildren().stream()
                        .filter(d -> d.getTrueWeight() != child.getTrueWeight()).findAny().orElse(null);
                correctWeight = child.getWeight() + goodDisk.getTrueWeight() - child.getTrueWeight();
            }
        }

        return String.valueOf(correctWeight);
    }

    private int calculateTrueWeight(Disk disk) {
        int trueWeight = disk.getWeight();

        int previousChildTrueWeight = -1;
        for (Disk child : disk.getChildren()) {
            int childTrueWeight = calculateTrueWeight(child);
            trueWeight += childTrueWeight;

            if (previousChildTrueWeight != -1 && previousChildTrueWeight != childTrueWeight) {
                if (badDiskParent == null) {
                    badDiskParent = disk;
                }
            }
            previousChildTrueWeight = child.getTrueWeight();
        }

        disk.setTrueWeight(trueWeight);
        return trueWeight;
    }
}
