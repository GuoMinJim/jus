package com.beiming.juc.jianzhioffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. 交替打印FooBar
 */
public class FooBar {


  private ReentrantLock lock = new ReentrantLock(true);
  private Condition foo = lock.newCondition();
  private Condition bar = lock.newCondition();
  private volatile boolean flag = false;
  private int n;

  public FooBar(int n) {
    this.n = n;
  }

  public static void main(String[] args) {
    FooBar fooBar = new FooBar(100);
    Thread thread = new Thread(() -> {
      try {
        fooBar.foo(new Runnable() {
          @Override
          public void run() {
            System.out.print("foo");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread1 = new Thread(() -> {
      try {
        fooBar.bar(new Runnable() {
          @Override
          public void run() {
            System.out.print("bar");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
    thread1.start();
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    lock.lock();
    while (!lock.hasWaiters(bar)) {
      foo.await();
    }
    for (int i = 0; i < n; i++) {
      printFoo.run();
      bar.signal();
      if (i == n - 1) {
        break;
      }
      foo.await();
    }
    lock.unlock();
  }

  public void bar(Runnable printBar) throws InterruptedException {
    lock.lock();
    foo.signal();
    bar.await();

    for (int i = 0; i < n; i++) {
      printBar.run();
      foo.signal();
      if (i == n - 1) {
        break;
      }
      bar.await();
    }
    lock.unlock();
  }

}
