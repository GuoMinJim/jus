package com.beiming.juc.jianzhioffer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 */
public class ZeroEvenOddTest {

  private static ReentrantLock sharedReentrantLock = new ReentrantLock(true);
  // 0 1 0 2 0 3 0 4
  private int n;
  private volatile AtomicInteger num = new AtomicInteger(0);
  private volatile int flag = 0;
  private Condition zero = sharedReentrantLock.newCondition();
  private Condition odd = sharedReentrantLock.newCondition();
  private Condition even = sharedReentrantLock.newCondition();

  public ZeroEvenOddTest(int n) {
    this.n = n;
  }

  public static void main(String[] args) {
    ZeroEvenOddTest zeroEvenOdd = new ZeroEvenOddTest(5);
    IntConsumer intConsumer = new IntConsumer() {
      @Override
      public void accept(int value) {
        System.out.print(value);
      }
    };
    Thread thread = new Thread(() -> {
      try {
        zeroEvenOdd.zero(intConsumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread1 = new Thread(() -> {
      try {
        zeroEvenOdd.odd(intConsumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread2 = new Thread(() -> {
      try {
        zeroEvenOdd.even(intConsumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
    thread1.start();
    thread2.start();

  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    sharedReentrantLock.lock();
    boolean revert = false;
    for (int i = 0; i < n; i++) {
      printNumber.accept(0);
      if (!revert) {
        revert = true;
        while (!sharedReentrantLock.hasWaiters(odd)) {
          zero.await();
        }
        odd.signal();
      } else {
        revert = false;
        while (!sharedReentrantLock.hasWaiters(even)) {
          zero.await();
        }
        even.signal();
      }
      zero.await();
    }
    sharedReentrantLock.unlock();
  }

  // 输出奇数
  public void odd(IntConsumer printNumber) throws InterruptedException {
    sharedReentrantLock.lock();
    int temp = n / 2 + n % 2;
    zero.signal();
    for (int i = 0; i < temp; i++) {
      odd.await();
      printNumber.accept(i * 2 + 1);
      zero.signal();
    }
    sharedReentrantLock.unlock();

  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    sharedReentrantLock.lock();
    int temp = n / 2;
    zero.signal();
    for (int i = 1; i <= temp; i++) {
      even.await();
      printNumber.accept(i * 2);
      zero.signal();
    }
    sharedReentrantLock.unlock();
  }
}
