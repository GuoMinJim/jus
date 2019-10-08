package com.beiming.juc.atomic;

import com.beiming.juc.web.domain.Car;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierDemo {

  final CyclicBarrier barrier;

  final int MAX_TASK;

  public CyclicBarrierDemo(int cnt) {
    barrier = new CyclicBarrier(cnt + 1);
    MAX_TASK = cnt;
  }

  public void doWork(final Runnable work) {
    new Thread() {

      public void run() {
        work.run();
        try {
          int index = barrier.await();
          doWithIndex(index);
        } catch (InterruptedException e) {
          return;
        } catch (BrokenBarrierException e) {
          return;
        }
      }
    }.start();
  }

  private void doWithIndex(int index) {
    if (index == MAX_TASK / 3) {
      System.out.println("Left 30%.");
    } else if (index == MAX_TASK / 2) {
      System.out.println("Left 50%");
    } else if (index == 0) {
      System.out.println("run over");
    }
  }

  public void waitForNext() {
    try {
      doWithIndex(barrier.await());
    } catch (InterruptedException e) {
      return;
    } catch (BrokenBarrierException e) {
      return;
    }
  }

  public static void main(String[] args) throws InterruptedException {

//    final int count = 10;
//    CyclicBarrierDemo demo = new CyclicBarrierDemo(count);
//    for (int i = 0; i < 100; i++) {
//      demo.doWork(new Runnable() {
//
//        public void run() {
//          //do something
//          try {
//            Thread.sleep(1000L);
//          } catch (Exception e) {
//            return;
//          }
//        }
//      });
//      if ((i + 1) % count == 0) {
//        demo.waitForNext();
//      }
//    }

        Random random = new Random();
    int max = 10;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    Thread[] threads = new Thread[max];
    for (int i = 0; i < max; i++) {
      threads[i] = new Thread(){
        @Override
        public void run() {
          Car car = new Car(String.valueOf(random.nextInt(1000)));
          car.ready();
          try {
            cyclicBarrier.await();
            car.run();
          } catch (BrokenBarrierException e) {
            e.printStackTrace();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
    }

    for (int i = 0; i < max; i++) {
      threads[i].start();
      if ((i+1) % 5 == 0 && i !=0 && i!=max-1) {
        cyclicBarrier.reset();
        Thread.sleep(1000);
      }
//      if (i>8 && i<10) {
//        cyclicBarrier.reset();
//      }
//      System.out.println("thread[" + i + "]开始运行....");
    }

    for (int i = 0; i < max; i++) {
      threads[i].join();
    }

  }
}
