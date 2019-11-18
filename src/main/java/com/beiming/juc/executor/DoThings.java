package com.beiming.juc.executor;

import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class DoThings {

  @Async("OperationSyncExecutor")
  public Future<String> doSomething(String str) {
    int a = 0;
    for (int i = 0; i < 50000; i++) {
      a++;
    }
    return new AsyncResult<>(str + "World!");
  }

}
