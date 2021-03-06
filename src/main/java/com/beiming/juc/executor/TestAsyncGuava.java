package com.beiming.juc.executor;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class TestAsyncGuava {

  public static void main(String[] args) throws InterruptedException {

    long l = System.currentTimeMillis();
//    Executors.newFixedThreadPool(5); 所以这个方法创建的线程池适合能估算出需要多少核心线程数量的场景。
//    Executors.newCachedThreadPool(); //这种队列本身不会存任务，只做转发，所以newCachedThreadPool适合执行大量的，轻量级任务。
//    Executors.newSingleThreadExecutor(); // 适合任务顺序执行，缺点但是不能充分利用CPU多核性能
//    Executors.newScheduledThreadPool(5); //执行周期性任务，类似定时器。
    ExecutorService executorService = SingletonExecutor.getExecutorService();
    ListeningExecutorService service = MoreExecutors
        .listeningDecorator(executorService);
    ListenableFuture<Integer> fu = service.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        System.out.println("执行耗时操作...");
        timeConsumingOperation();
        return 100;
      }
    });
    Futures.addCallback(fu, new FutureCallback<Integer>() {
      @Override
      public void onSuccess(Integer integer) {
        System.out.println("计算结果:" + integer);
      }

      @Override
      public void onFailure(Throwable throwable) {
        System.out.println("异步处理失败,e=" + throwable);
      }
    });
    System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
    new CountDownLatch(1).await();
  }


  static void timeConsumingOperation() {
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
