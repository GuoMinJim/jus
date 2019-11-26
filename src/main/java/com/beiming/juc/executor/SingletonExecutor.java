package com.beiming.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonExecutor {

  private static ExecutorService executorService;

  private SingletonExecutor() {

  }

  public static synchronized ExecutorService getExecutorService() {
    if (executorService == null) {
      executorService = Executors.newCachedThreadPool();
    }
    return executorService;
  }
}
