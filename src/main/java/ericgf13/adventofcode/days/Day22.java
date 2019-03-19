package ericgf13.adventofcode.days;

import ericgf13.adventofcode.Day;
import ericgf13.adventofcode.beans.Node;
import ericgf13.adventofcode.enums.Direction;

import java.util.HashMap;
import java.util.Map;

public class Day22 extends Day {

    private Map<Node, Character> input = new HashMap<>();
    private int baseX;
    private int baseY;

    public Day22() {
        super(22);

        for (String line : getInputAsList()) {
            baseX = 0;
            for (char c : line.toCharArray()) {
                input.put(new Node(baseX++, baseY), c);
            }
            baseY++;
        }
    }

    @Override
    public String part1() {
        int x = baseX / 2;
        int y = baseY / 2;
        Direction dir = Direction.UP;
        int infections = 0;
        Map<Node, Character> map = new HashMap<>(input);

        for (int i = 0; i < 10_000; i++) {
            Node currNode = new Node(x, y);
            map.putIfAbsent(currNode, '.');

            if (map.get(currNode) == '#') {
                map.put(currNode, '.');

                switch (dir) {
                    case UP:
                        x++;
                        dir = Direction.RIGHT;
                        break;
                    case DOWN:
                        x--;
                        dir = Direction.LEFT;
                        break;
                    case RIGHT:
                        y++;
                        dir = Direction.DOWN;
                        break;
                    case LEFT:
                        y--;
                        dir = Direction.UP;
                        break;
                }
            } else {
                infections++;
                map.put(currNode, '#');

                switch (dir) {
                    case UP:
                        x--;
                        dir = Direction.LEFT;
                        break;
                    case DOWN:
                        x++;
                        dir = Direction.RIGHT;
                        break;
                    case RIGHT:
                        y--;
                        dir = Direction.UP;
                        break;
                    case LEFT:
                        y++;
                        dir = Direction.DOWN;
                        break;
                }
            }
        }

        return String.valueOf(infections);
    }

    @Override
    public String part2() {
        int x = baseX / 2;
        int y = baseY / 2;
        Direction dir = Direction.UP;
        int infections = 0;
        Map<Node, Character> map = new HashMap<>(input);

        for (int i = 0; i < 10_000_000; i++) {
            Node currNode = new Node(x, y);
            map.putIfAbsent(currNode, '.');

            switch (map.get(currNode)) {
                case '#':
                    map.put(currNode, 'F');

                    switch (dir) {
                        case UP:
                            x++;
                            dir = Direction.RIGHT;
                            break;
                        case DOWN:
                            x--;
                            dir = Direction.LEFT;
                            break;
                        case RIGHT:
                            y++;
                            dir = Direction.DOWN;
                            break;
                        case LEFT:
                            y--;
                            dir = Direction.UP;
                            break;
                    }
                    break;
                case '.':
                    map.put(currNode, 'W');

                    switch (dir) {
                        case UP:
                            x--;
                            dir = Direction.LEFT;
                            break;
                        case DOWN:
                            x++;
                            dir = Direction.RIGHT;
                            break;
                        case RIGHT:
                            y--;
                            dir = Direction.UP;
                            break;
                        case LEFT:
                            y++;
                            dir = Direction.DOWN;
                            break;
                    }
                    break;
                case 'W':
                    infections++;
                    map.put(currNode, '#');

                    switch (dir) {
                        case UP:
                            y--;
                            break;
                        case DOWN:
                            y++;
                            break;
                        case RIGHT:
                            x++;
                            break;
                        case LEFT:
                            x--;
                            break;
                    }
                    break;
                case 'F':
                    map.put(currNode, '.');

                    switch (dir) {
                        case UP:
                            y++;
                            dir = Direction.DOWN;
                            break;
                        case DOWN:
                            y--;
                            dir = Direction.UP;
                            break;
                        case RIGHT:
                            x--;
                            dir = Direction.LEFT;
                            break;
                        case LEFT:
                            x++;
                            dir = Direction.RIGHT;
                            break;
                    }
                    break;
            }
        }

        return String.valueOf(infections);
    }
}
