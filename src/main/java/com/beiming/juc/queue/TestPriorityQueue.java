package com.beiming.juc.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {

  public static void main(String[] args) {

    Comparator comparator = new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        if ((Integer) o1 > (Integer) o2) {
          return -1;
        } else if ((Integer) o1 < (Integer) o2) {
          return 1;
        } else {
          return 0;
        }
      }
    };
    PriorityQueue<Integer> queue = new PriorityQueue(comparator);
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    System.out.println(queue.size());
    for (int i = 0; i < 4; i++) {
      System.out.println(queue.poll() + "~~");
    }
  }
}
