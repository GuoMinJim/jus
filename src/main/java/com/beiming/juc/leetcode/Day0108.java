package com.beiming.juc.leetcode;

import com.beiming.juc.jianzhioffer.ListNode;
import java.util.HashMap;
import java.util.Map;

public class Day0108 {


  Map<String, Integer> map = new HashMap<>();
  /**
   * 2. 两数相加
   *
   * @param l1
   * @param l2
   * @return
   */
  private int num1 = 0;
  private int num2 = 0;

  public static void main(String[] args) {
//    System.out.println(new Day0108().getNum(10212,0));
    ListNode node = new ListNode(2);
    node.next = new ListNode(4);
    node.next.next = new ListNode(3);

    ListNode node1 = new ListNode(5);
    node1.next = new ListNode(6);
    node1.next.next = new ListNode(4);
    System.out.println(new Day0108().addTwoNumbers(node, node1).val);
  }

//  /**
//   * 454. 四数相加 II
//   */
//  public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
//    int length = a.length;
//    int count = Math.min(twoCount(a, b, length), twoCount(d, d, length)) + Math
//        .min(twoCount(A, C, length), twoCount(B, D, length)) + Math
//        .min(twoCount(A, D, length), twoCount(B, C, length));
//    return count;
//  }

  public int twoCount(int[] array1, int[] array2, int length) {
    int count = 0;
    for (int i = 0; i < length; i++) {
      if (sumZero(array1[i], array2, 0, length)) {
        count++;
      }
    }
    return count;
  }

  public boolean sumZero(int value, int[] array2, int begin, int length) {
    if (value + array2[(begin + length) / 2] == 0) {
      return true;
    }
    if (begin + 1 == length || begin == length) {
      return false;
    }

    if (value + array2[(begin + length) / 2] < 0) {
      return sumZero(value, array2, (begin + length) / 2, length);
    } else {
      return sumZero(value, array2, begin, (begin + length) / 2);
    }
  }

  /**
   * 495. 提莫攻击
   */
  public int findPoisonedDuration(int[] timeSeries, int duration) {
    if (timeSeries.length == 0) {
      return 0;
    }
    if (timeSeries.length == 1) {
      return duration;
    }
    return handle(1, timeSeries, duration, duration + timeSeries[0], duration);
  }

  public int handle(int index, int[] timeSeries, int duration, int end, int sum) {
    if (index >= timeSeries.length) {
      return sum;
    }
    if (timeSeries[index] >= end) {
      return handle(index + 1, timeSeries, duration, timeSeries[index], sum + duration);
    } else {
      return handle(index + 1, timeSeries, duration, timeSeries[index] + duration,
          sum + timeSeries[index] - timeSeries[index - 1]);
    }

  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    }
//    if (l1 != null && l1.next == null ) {
//      map.put("num1",1);
//      num1 = l1.val;
//    }
//    if (l1!=null && l2.next == null ) {
//      map.put("num2",1);
//      num2 = l2.val;
//    }
    addTwoNumbers(l1.next, l2.next);
    if (l1 != null) {
      if (map.containsKey("num1")) {
        num1 = num1 + l1.val * (int) Math.pow(10.0, map.get("num1"));
        map.put("num1", map.get("num1") + 1);
      } else {
        map.put("num1", 1);
        num1 = l1.val;
      }
    }
    if (l2 != null) {
      if (map.containsKey("num2")) {
        num2 += l2.val * (int) Math.pow(10.0, map.get("num2"));
        map.put("num2", map.get("num2") + 1);
      } else {
        map.put("num2", 1);
        num2 = l2.val;
      }
    }
    return new ListNode(num1 + num2);
  }

}
