package com.beiming.juc.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock需要严格区分读写操作，如果读操作使用了写入锁， 那么降低读操作的吞吐量 如果写操作使用了读取锁，那么就可能发生数据错误。
 *
 *
 * ，只是在获取读取锁和写入锁的方式上不一样
 */
public class TestReadWriteLock {

  static Integer value = 5;

  public static void main(String[] args) {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock r = lock.readLock();
    Lock w = lock.writeLock();

    new Thread(() -> {
      for (int i = 0; i < 100000; i++) {
        w.lock();
        value++;
        w.unlock();
      }
    }).start();
    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        r.lock();
        System.out.println(value);
        r.unlock();
      }).start();
    }

    new Thread(() -> {
      w.tryLock();
      System.out.println(value + 1);
      w.unlock();
    }).start();
  }

}
