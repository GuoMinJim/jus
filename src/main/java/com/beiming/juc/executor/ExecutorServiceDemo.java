package com.beiming.juc.executor;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

  static void log(String msg) {
    System.out.println(System.currentTimeMillis() + " -> " + msg);
  }

  static int getThreadPoolRunState(ThreadPoolExecutor pool)
      throws NoSuchFieldException, IllegalAccessException {
    Field f = ThreadPoolExecutor.class.getDeclaredField("runState");
    f.setAccessible(true);
    return f.getInt(pool);
  }

  public static void main(String[] args) throws Exception {
    ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(1));
    /**
     * DiscardPolicy  直接丢弃
     * DiscardOldestPolicy 丢弃最近的任务
     * AbortPolicy  异常策略
     * CallerRunsPolicy 不用线程池线程执行
     */
    pool.setRejectedExecutionHandler(new CallerRunsPolicy());
    for (int i = 0; i < 10; i++) {
      final int index = i;
      pool.submit(new Runnable() {
        @Override
        public void run() {
          log("run task:" + index + " -> " + Thread.currentThread().getName());
          try {
            Thread.sleep(1000L);
          } catch (Exception e) {
            e.printStackTrace();
          }
          log("run over:" + index + " -> " + Thread.currentThread().getName());
        }
      });
    }
    log("before sleep");
    Thread.sleep(4000L);
    log("before shutdown()");
    pool.shutdown();
    log("after shutdown(),pool.isTerminated=" + pool.isTerminated());
    pool.awaitTermination(1000L, TimeUnit.SECONDS);
    log("now,pool.isTerminated=" + pool.isTerminated() + ", state="
        + getThreadPoolRunState(pool));
  }

}
