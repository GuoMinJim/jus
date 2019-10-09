package com.beiming.juc.atomic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {


  public static void main(String[] args) throws InterruptedException {
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition = lock.newCondition();

    Thread[] threads = new Thread[10];
    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + "线程开始运行");
          lock.lock();
          condition.await();
          lock.unlock();
          System.out.println(Thread.currentThread().getName() + "线程已经唤醒");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    for (Thread thread : threads) {
      thread.start();
    }
    Thread.sleep(2000);
    System.out.println("开始释放锁");
    for (int i = 0; i < 10; i++) {
      condition.signal();
    }

  }
}
