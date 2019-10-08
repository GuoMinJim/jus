package com.beiming.juc.atomic;

public class TestIncrement {

  static Integer value = 0;

  volatile int a = 0;

  public static void main(String[] args) throws InterruptedException {
    new TestIncrement().testaa();
  }


  public void testaa() throws InterruptedException {
    int threadSize = 10;
    Thread[] ts = new Thread[threadSize];
    for (int i = 0; i < threadSize; i++) {
      ts[i] = new Thread() {
        public void run() {
          for (int j = 0; j < 100000; j++) {
              synchronized (this) {
                value++;
              }
          }
        }
      };
      //
    }
    for (Thread t : ts) {
      t.start();
    }
    for (Thread t : ts) {
      t.join();
    }
    System.out.println(value);
  }


}
