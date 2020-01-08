package com.beiming.juc.jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day1227 {

  public static void main(String[] args) {

    String str = "dcabfge";
    List<List<Integer>> pairs = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    list.add(0);
    list.add(3);
    List<Integer> list1 = new ArrayList<>();
    list1.add(1);
    list1.add(2);
    List<Integer> list2 = new ArrayList<>();
    list2.add(0);
    list2.add(2);
    List<Integer> list3 = new ArrayList<>();
    list2.add(4);
    list2.add(6);
    pairs.add(list);
    pairs.add(list1);
    pairs.add(list2);
    pairs.add(list3);
    System.out.println(new Day1227().smallestStringWithSwaps(str, pairs));
  }

  public int[] sortedSquares(int[] a) {
    int length = a.length;
    for (int i = 0; i < length; i++) {
      a[i] = a[i] * a[i];
    }
    Arrays.sort(a);
    return a;
  }

  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    int size = pairs.size();
    char[] chars = s.toCharArray();
    Map<Integer, List<Integer>> map = new HashMap<>();
    int num = 0;
    for (int i = 0; i < size; i++) {
      List<Integer> list = pairs.get(i);
      List<Integer> temp = map.get(num);
      for (int j = 0; j < list.size(); j++) {
        if (isExist(map, list.get(j)) != 0) {
          temp.addAll(list);
          break;
        }
        if (j == list.size() - 1 && isExist(map, list.get(j)) == 0) {
          num++;
          map.put(num, list);
        }
      }
    }
    return new String(chars);
  }

  public int isExist(Map<Integer, List<Integer>> map, int num) {
    Set<Integer> set = map.keySet();
    for (Integer n : set) {
      if (map.get(n).contains(num)) {
        return n;
      }
    }
    return 0;
  }


}
