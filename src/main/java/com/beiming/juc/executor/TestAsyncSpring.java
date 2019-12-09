package com.beiming.juc.executor;

import com.beiming.juc.JusApplication;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JusApplication.class)
public class TestAsyncSpring {

  @Autowired
  public DoThings doThings;


  @Test
  public void testAsync() throws ExecutionException, InterruptedException {
    long l = System.currentTimeMillis();
    Future<String> hello = doThings.doSomething("Hello");
//    int a = 0;
//    for (int i = 0; i < 50000; i++) {
//      a++;
//    }
    int b = 0;
    for (int i = 0; i < 50000; i++) {
      b++;
    }
    hello.get();
//    String str = hello.get();
    long l1 = System.currentTimeMillis();
    System.out.println("时间" + (l1 - l));
  }
}
