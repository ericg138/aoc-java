package ericg138.aoc.year2023.days;

import ericg138.aoc.Day;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day07 extends Day {
  private final List<String> input = getInputAsList();

  public Day07() {
    super(7);
  }

  @Override
  public String part1() {
    long result = 0;

    Map<String, Long> handToBid = new TreeMap<>(new HandComparator(false));
    for (String line : input) {
      handToBid.put(line.substring(0, 5), Long.parseLong(line.substring(6)));
    }

    Long[] bids = handToBid.values().toArray(new Long[0]);
    for (int i = 0; i < handToBid.size(); i++) {
      result += (i + 1) * bids[i];
    }

    return "" + result;
  }

  @Override
  public String part2() {
    long result = 0;

    Map<String, Long> handToBid = new TreeMap<>(new HandComparator(true));
    for (String line : input) {
      handToBid.put(line.substring(0, 5), Long.parseLong(line.substring(6)));
    }

    Long[] bids = handToBid.values().toArray(new Long[0]);
    for (int i = 0; i < handToBid.size(); i++) {
      result += (i + 1) * bids[i];
    }

    return "" + result;
  }

  private static class HandComparator implements Comparator<String> {
    private final boolean withJokers;

    public HandComparator(boolean withJokers) {
      this.withJokers = withJokers;
    }

    @Override
    public int compare(String o1, String o2) {
      Map<Character, Integer> cardToCount1 = getCardToCount(o1);
      Map<Character, Integer> cardToCount2 = getCardToCount(o2);

      // Five of a kind
      if (hasType(cardToCount1, 5) || hasType(cardToCount2, 5)) {
        if (hasType(cardToCount1, 5) && !hasType(cardToCount2, 5)) {
          return 1;
        } else if (!hasType(cardToCount1, 5)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      // Four of a kind
      if (hasType(cardToCount1, 4) || hasType(cardToCount2, 4)) {
        if (hasType(cardToCount1, 4) && !hasType(cardToCount2, 4)) {
          return 1;
        } else if (!hasType(cardToCount1, 4)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      // Full house
      if (hasFullHouse(cardToCount1) || hasFullHouse(cardToCount2)) {
        if (hasFullHouse(cardToCount1) && !hasFullHouse(cardToCount2)) {
          return 1;
        } else if (!hasFullHouse(cardToCount1)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      // Three of a kind
      if (hasType(cardToCount1, 3) || hasType(cardToCount2, 3)) {
        if (hasType(cardToCount1, 3) && !hasType(cardToCount2, 3)) {
          return 1;
        } else if (!hasType(cardToCount1, 3)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      // Two pairs
      if (hasTwoPairs(cardToCount1) || hasTwoPairs(cardToCount2)) {
        if (hasTwoPairs(cardToCount1) && !hasTwoPairs(cardToCount2)) {
          return 1;
        } else if (!hasTwoPairs(cardToCount1)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      // One pair
      if (hasType(cardToCount1, 2) || hasType(cardToCount2, 2)) {
        if (hasType(cardToCount1, 2) && !hasType(cardToCount2, 2)) {
          return 1;
        } else if (!hasType(cardToCount1, 2)) {
          return -1;
        } else {
          return strongestByOrder(o1, o2);
        }
      }

      return strongestByOrder(o1, o2);
    }

    private int strongestByOrder(String o1, String o2) {
      List<Character> cardsOrderedByStrength;
      if (withJokers) {
        cardsOrderedByStrength = List.of('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A');
      } else {
        cardsOrderedByStrength = List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
      }

      for (int i = 0; i < o1.length(); i++) {
        char char1 = o1.charAt(i);
        char char2 = o2.charAt(i);
        if (cardsOrderedByStrength.indexOf(char1) > cardsOrderedByStrength.indexOf(char2)) {
          return 1;
        } else if (cardsOrderedByStrength.indexOf(char2) > cardsOrderedByStrength.indexOf(char1)) {
          return -1;
        }
      }
      return 0;
    }

    private Map<Character, Integer> getCardToCount(String hand) {
      Map<Character, Integer> cardToCount = new HashMap<>();
      for (Character card : hand.toCharArray()) {
        cardToCount.putIfAbsent(card, 0);
        cardToCount.put(card, cardToCount.get(card) + 1);
      }
      return cardToCount;
    }

    private boolean hasType(Map<Character, Integer> cardToCount, int count) {
      if (withJokers && cardToCount.containsKey('J')) {
        int jokerCount = cardToCount.get('J');
        for (Integer value : cardToCount.entrySet().stream().filter(e -> e.getKey() != 'J').map(Map.Entry::getValue).toList()) {
          if (value + jokerCount >= count) {
            return true;
          }
        }
      }
      return cardToCount.values().stream().anyMatch(v -> v == count);
    }

    private boolean hasFullHouse(Map<Character, Integer> cardToCount) {
      if (withJokers && cardToCount.containsKey('J') && hasTwoPairs(cardToCount)) {
        return true;
      }
      return cardToCount.containsValue(2) && cardToCount.containsValue(3);
    }

    private boolean hasTwoPairs(Map<Character, Integer> cardToCount) {
      return cardToCount.values().stream().sorted().toList().equals(List.of(1, 2, 2));
    }
  }
}
