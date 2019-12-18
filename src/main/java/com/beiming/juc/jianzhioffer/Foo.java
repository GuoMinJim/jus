package com.beiming.juc.jianzhioffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Leetcode 1114  按序打印
 */
public class Foo {

  private ReentrantLock lock = new ReentrantLock(true);
  private Condition one = lock.newCondition();
  private Condition two = lock.newCondition();
  private Condition three = lock.newCondition();
  private boolean flag = false;

  public Foo() {

  }

  public static void main(String[] args) {
    Foo foo = new Foo();
    Thread thread = new Thread(() -> {
      try {
        foo.third(new Runnable() {
          @Override
          public void run() {
            System.out.println("third");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread1 = new Thread(() -> {
      try {
        foo.second(new Runnable() {
          @Override
          public void run() {
            System.out.println("second");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread2 = new Thread(() -> {
      try {
        foo.first(new Runnable() {
          @Override
          public void run() {
            System.out.println("First");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
    thread1.start();
    thread2.start();
  }

  // 123 132 213 231 321 312
  public void first(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    lock.lock();
    printFirst.run();
    while (!lock.hasWaiters(two)) {
      one.await();
    }
    two.signal();
    lock.unlock();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    lock.lock();
    if (lock.hasWaiters(one)) {
      one.signal();
    }
    two.await();
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();

    if (!lock.hasWaiters(three)) {
      // 没执行
      flag = true;
      two.await();
    }
    three.signal();
    lock.unlock();
  }

  public void third(Runnable printThird) throws InterruptedException {
    lock.lock();
    if (flag && lock.hasWaiters(two)) {
      two.signal();
      three.await();
    } else {
      three.await();
    }
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
    lock.unlock();
  }


}
