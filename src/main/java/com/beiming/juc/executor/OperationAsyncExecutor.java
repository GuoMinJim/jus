package com.beiming.juc.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class OperationAsyncExecutor {

  public static final String OPERATION_ASYNC_EXECUTOR = "OperationSyncExecutor";
  private static String threadNamePrefix = "op-async-exec-";
  private int corePoolSize = 3;
  private int maxPoolSize = 6;
  private int queueCapacity = 50;

  @Bean(value = OPERATION_ASYNC_EXECUTOR)
  public Executor opAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix(threadNamePrefix);
    executor.setRejectedExecutionHandler(new CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

}
