package com.beiming.juc.atomic;

import com.beiming.juc.web.domain.Car;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 理解： 只允许n个请求 多余多的请求会被挂起
 * Semaphore是一个计数器，在计数器不为0的时候对线程就放行，一旦达到0，那么所有请求资源的新线程都会被阻塞，包括增加请求到许可的线程
 * ，也就是说Semaphore不是可重入的。每一次请求一个许可都会导致计数器减少1，同样每次释放一个许可都会导致计数器增加1，一旦达到了0，新的许可请求线程将被挂起。
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
