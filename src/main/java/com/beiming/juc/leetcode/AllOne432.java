package com.beiming.juc.leetcode;

import java.util.HashMap;
import java.util.Map;

public class AllOne432 {

  private Map<Integer, Object> map;

  private String max = "";

  private String min = "";

  /**
   * Initialize your data structure here.
   */
  public AllOne432() {
    map = new HashMap<>(Integer.MAX_VALUE);
  }

  /**
   * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
   */
  public void inc(String key) {
    Integer integer = Integer.valueOf(key);
    if (integer.intValue() == 1) {
      map.put(1, "1");
      if ("".equals(max)) {
        max = "1";
      }
      if ("".equals(min)) {
        max = "1";
      }
    } else {
      map.put(integer.intValue() + 1, 1);
      if (integer.intValue() + 1 > Integer.valueOf(max)) {
        max = String.valueOf(integer.intValue() + 1);
      }
      if (integer.intValue() + 1 < Integer.valueOf(min)) {
        min = String.valueOf(integer.intValue() + 1);
      }
    }
  }

  /**
   * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
   */
  public void dec(String key) {
    Integer integer = Integer.valueOf(key);
    if (integer.intValue() == 1) {
      map.remove(1, "1");
    } else {
      map.put(integer.intValue() - 1, 1);
      if (integer.intValue() + 1 > Integer.valueOf(max)) {
        max = String.valueOf(integer.intValue() + 1);
      }
      if (integer.intValue() + 1 < Integer.valueOf(min)) {
        min = String.valueOf(integer.intValue() + 1);
      }
    }
  }

  /**
   * Returns one of the keys with maximal value.
   */
  public String getMaxKey() {
    return max;
  }

  /**
   * Returns one of the keys with Minimal value.
   */
  public String getMinKey() {
    return min;
  }
}
