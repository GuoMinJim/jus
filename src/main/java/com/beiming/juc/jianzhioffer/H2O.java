package com.beiming.juc.jianzhioffer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1117. H2O 生成
 */
public class H2O {


  private Semaphore hydrogen = new Semaphore(2);
  private Semaphore oxygen = new Semaphore(1);
  private volatile AtomicInteger num = new AtomicInteger(0);


  public H2O() {

  }

  public static void main(String[] args) {
    H2O h2O = new H2O();
    Thread thread = new Thread(() -> {
      for (int i = 0; i < 4; i++) {
        try {
          h2O.hydrogen(new Runnable() {
            @Override
            public void run() {
              System.out.print("h");
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 4; i++) {
        try {
          h2O.oxygen(new Runnable() {
            @Override
            public void run() {
              System.out.print("o");
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 4; i++) {
        try {
          h2O.hydrogen(new Runnable() {
            @Override
            public void run() {
              System.out.print("h");
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    thread.start();
    thread1.start();
    thread2.start();
  }

  // HOH
  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    // releaseHydrogen.run() outputs "H". Do not change or remove this line.
    hydrogen.acquire();
    releaseHydrogen.run();
    num.getAndIncrement();
    if (num.intValue() % 2 == 0 && num.intValue() != 0) {
//      System.out.println();
//      System.out.println(num.intValue());
      oxygen.release();
    }
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    // releaseOxygen.run() outputs "O". Do not change or remove this line.
    oxygen.acquire();
    releaseOxygen.run();
    if (num.intValue() % 2 == 0 && num.intValue() != 0) {
//      System.out.println();
//      System.out.println(num.intValue() + "---");
      hydrogen.release(2);
    }

  }


}
