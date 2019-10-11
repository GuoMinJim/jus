package com.beiming.juc.atomic;

public class TestIncrement {



  public static void main(String[] args) throws InterruptedException {
    new TestIncrement().testaa();
  }


  static Integer value2 = 0;
  public void testaa() throws InterruptedException {
    int threadSize = 10;
    Thread[] ts = new Thread[threadSize];
    for (int i = 0; i < threadSize; i++) {
      ts[i] = new Thread() {
        public void run() {
            for (int j = 0; j < 10000; j++) {
              value2++;
          }
        }
      };
    }
    for (Thread t : ts) {
      t.start();
    }
    for (Thread t : ts) {
      t.join();
    }
    System.out.println(value2);
  }
}
