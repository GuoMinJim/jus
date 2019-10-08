package com.beiming.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

import static org.junit.Assert.*;

public class testAtomic {

   static Integer value3 = 5;
  @Test
  public void testAll() throws InterruptedException {
    final AtomicInteger value = new AtomicInteger(10);
    assertEquals(value.compareAndSet(1, 2), false);
    assertEquals(value.get(), 10);
    assertTrue(value.compareAndSet(10, 3));
    assertEquals(value.get(), 3);
    value.set(0);
    //
    assertEquals(value.incrementAndGet(), 1);
    assertEquals(value.getAndAdd(2), 1);
    assertEquals(value.getAndSet(5), 3);
    assertEquals(value.get(), 5);
    //
    long l = System.currentTimeMillis();
    final int threadSize = 100000;
    Thread[] ts = new Thread[threadSize];
    for (int i = 0; i < threadSize; i++) {
      ts[i] = new Thread() {
        public void run() {
          value.incrementAndGet();
        }
      };
    }
    //
    for (Thread t : ts) {
      t.start();
    }
    for (Thread t : ts) {
      t.join();
    }
    //
    System.out.println(value.get());
    System.out.println(System.currentTimeMillis() - l);
    assertEquals(value.get(), 5 + threadSize);

  }

  static Integer value2 = 5;
  @Test
  public void testInt() throws InterruptedException {

    final int threadSize = 10;
    Thread[] ts = new Thread[threadSize];
      for (int i = 0; i < threadSize; i++) {
        ts[i] = new Thread() {
          public void run() {
            for (int j = 0; j < 1000; j++) {
              value2++;
            }
          }
        };
      //
      for (Thread t : ts) {
        t.start();
      }
      for (Thread t : ts) {
        t.join(20000);
      }
    }
    System.out.println(value2);
  }

}
