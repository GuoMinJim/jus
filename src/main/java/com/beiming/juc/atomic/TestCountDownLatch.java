package com.beiming.juc.atomic;

import com.beiming.juc.web.domain.Car;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 测试功能  CountDownLathc是一次性的
 */
public class TestCountDownLatch {





  public static void main(String[] args) throws InterruptedException {
    int max = 10;
    Random random = new Random();
    CountDownLatch countDownLatch = new CountDownLatch(max);
    Thread[] threads = new Thread[max];
    for (int i = 0; i < max; i++) {
      threads[i] = new Thread(){
        @Override
        public void run() {
          Car car = new Car(String.valueOf(random.nextInt(1000)));
          car.ready();
          try {
            countDownLatch.countDown();
            countDownLatch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          car.run();
        }
      };
    }

    for (int i = 0; i < max; i++) {
      threads[i].start();
      System.out.println("thread[" + i + "]开始运行....");
    }

    for (int i = 0; i < max; i++) {
      threads[i].join();
    }

  }


}
