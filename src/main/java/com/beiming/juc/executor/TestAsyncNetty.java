package com.beiming.juc.executor;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class TestAsyncNetty {

  public static void main(String[] args) throws InterruptedException {
    long l = System.currentTimeMillis();
    EventExecutorGroup group = new DefaultEventExecutorGroup(4);
    Future<Integer> submit = group.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        System.out.println("执行耗时操作。。。。");
        timeConsumingOperation();
        return 100;
      }
    });
    submit.addListener(new FutureListener<Object>() {

      @Override
      public void operationComplete(Future<Object> objectFuture) throws Exception {
        System.out.println("计算结果：" + (objectFuture.get()));
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
