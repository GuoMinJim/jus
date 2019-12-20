package com.beiming.juc.jianzhioffer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {

  private ReentrantLock lock = new ReentrantLock(true);

  private Condition fizz = lock.newCondition();
  private Condition buzz = lock.newCondition();
  private Condition fizzbuzz = lock.newCondition();
  private Condition number = lock.newCondition();
  private volatile AtomicInteger num = new AtomicInteger(1);
  private int n;

  public FizzBuzz(int n) {
    this.n = n;
  }

  public static void main(String[] args) {
    FizzBuzz fizzBuzz = new FizzBuzz(15);

    Thread thread = new Thread(() -> {
      try {
        fizzBuzz.number(new IntConsumer() {
          @Override
          public void accept(int value) {
            System.out.println(value + "");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread1 = new Thread(() -> {
      try {
        fizzBuzz.fizz(new Runnable() {
          @Override
          public void run() {
            System.out.print("Fizz ");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        fizzBuzz.buzz(new Runnable() {
          @Override
          public void run() {
            System.out.print("Buzz ");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread3 = new Thread(() -> {
      try {
        fizzBuzz.fizzbuzz(new Runnable() {
          @Override
          public void run() {
            System.out.print("fizzbuzz ");
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
    thread1.start();
    thread2.start();
    thread3.start();
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    lock.lock();
    if (lock.hasWaiters(number)) {
      number.signal();
    }
    fizz.await();
    while (num.intValue() <= n) {
      printFizz.run();
      num.getAndIncrement();
      number.signal();
      fizz.await();
    }
    lock.unlock();
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {
    lock.lock();
    if (lock.hasWaiters(number)) {
      number.signal();
    }
    buzz.await();
    while (num.intValue() <= n) {
      printBuzz.run();
      num.getAndIncrement();
      number.signal();
      buzz.await();
    }
    lock.unlock();
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    lock.lock();
    if (lock.hasWaiters(number)) {
      number.signal();
    }
    fizzbuzz.await();
    while (num.intValue() <= n) {
      printFizzBuzz.run();
      num.getAndIncrement();
      number.signal();
      fizzbuzz.await();
    }
    lock.unlock();
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    lock.lock();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        while (!lock.hasWaiters(fizzbuzz)) {
          number.await();
        }
        fizzbuzz.signal();
        number.await();
      } else if (i % 5 == 0) {
        while (!lock.hasWaiters(buzz)) {
          number.await();
        }
        buzz.signal();
        number.await();
      } else if (i % 3 == 0) {
        while (!lock.hasWaiters(fizz)) {
          number.await();
        }
        fizz.signal();
        number.await();
      } else {
        printNumber.accept(i);
        num.getAndIncrement();
      }
    }
    fizz.signal();
    buzz.signal();
    fizzbuzz.signal();
    lock.unlock();
  }
}

