package com.beiming.juc.atomic;

import com.beiming.juc.web.domain.Car;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 理解： 只允许n个请求 多余多的请求会被挂起
 */
public class SemaphoreDemo1 {

  public static void main(String[] args) {
    Random random = new Random();
    Semaphore semaphore = new Semaphore(5);
    for (int i = 0; i < 10; i++) {
      new Thread() {
        @Override
        public void run() {
          try {
            semaphore.acquire();
            Car car = new Car(String.valueOf(random.nextInt(1000)));
            car.ready();
            sleep(2000);
            semaphore.release();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }.start();
    }
  }
}
