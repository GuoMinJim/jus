package com.beiming.juc.jianzhioffer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 */
public class ZeroEvenOdd {

  // 0 1 0 2 0 3 0 4
  private int n;

  private volatile AtomicInteger num = new AtomicInteger(0);


  private ReentrantLock lock = new ReentrantLock(true);
  private Condition zero = lock.newCondition();
  private Condition odd = lock.newCondition();
  private Condition even = lock.newCondition();

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  public static void main(String[] args) {
    ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
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

    lock.lock();
    for (int i = 0; i < n; i++) {
      printNumber.accept(0);
      if (num.intValue() % 2 == 0) {
        //唤醒奇数
        while (!lock.hasWaiters(odd)) {
          zero.await();
        }
        num.getAndIncrement();
        odd.signal();
        zero.await();
      } else {
        while (!lock.hasWaiters(even)) {
          zero.await();
        }
        num.getAndIncrement();
        even.signal();
        zero.await();
      }
    }
    lock.unlock();
//    System.out.println("zero已经释放锁");

  }

  // 输出奇数
  public void odd(IntConsumer printNumber) throws InterruptedException {
    int oddTime = n % 2 + n / 2;
    lock.lock();
    zero.signal();
    for (int i = 1; i <= n; i = i + 2) {
//      System.out.println("odd开始等待");
      odd.await();
      printNumber.accept(i);
      zero.signal();
    }
    lock.unlock();
//    System.out.println("odd已经释放锁");
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    int evenTime = n / 2;
//    System.out.println("A");
    lock.lock();
//    System.out.println("B");
    zero.signal();
    for (int i = 2; i <= n; i = i + 2) {
//      System.out.println("even开始等待");
      even.await();
      printNumber.accept(i);
      zero.signal();
    }
    lock.unlock();
//    System.out.println("even已经释放锁");
  }
}
