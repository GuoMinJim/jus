package com.beiming.juc.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Day0424 {

  private Map<Byte, Object> map = new HashMap<>();
  private int[] ints = new int[9];

  public Day0424() {
//    for (int i = 0; i < ints.length; i++) {
//      ints[i] = i;
//    }
  }

  public static void main(String[] args) {
    System.out.println(new Day0424().numDupDigitsAtMostN(5987490));
  }

  public int numDupDigitsAtMostN(int n) {
    int sum = 0;
    while (0 < n) {
      sum = dup(String.valueOf(n)) ? sum + 1 : sum;
      n--;
    }
    return sum;
  }

  private boolean dup(String str) {
    map.clear();
    byte[] bytes = str.getBytes();
    for (int i = 0; i < bytes.length; i++) {
      if (map.containsKey(bytes[i])) {
        return true;
      } else {
        map.put(bytes[i], 0);
      }
    }
    return false;
  }
}
