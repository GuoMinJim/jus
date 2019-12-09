package com.beiming.juc.atomic;

import java.util.regex.Pattern;

public class TestIncrement {


  public static void main(String[] args) throws InterruptedException {
    String a = "admin_organ_AMG4H4";
    System.out.println(Pattern.matches("(admin_organ){1}[a-zA-Z0-9_]{7}", a));
//    new TestIncrement().testaa();
  }


  static Integer value2 = 0;

  public void testaa() throws InterruptedException {
    int threadSize = 10;
    Thread[] ts = new Thread[threadSize];
    for (int i = 0; i < threadSize; i++) {
      ts[i] = new Thread(() -> {
        for (int j = 0; j < 100000; j++) {
          synchronized (this) {
            value2++;
          }
        }
      });
    }
    for (Thread t : ts) {
      t.start();
    }
    for (Thread t : ts) {
      t.join();
      System.out.println(value2);
    }
    System.out.println(value2);
  }
}
