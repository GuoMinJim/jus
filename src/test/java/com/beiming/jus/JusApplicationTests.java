package com.beiming.jus;

import com.beiming.juc.web.dao.CarMapper;
import com.beiming.juc.web.domain.Car;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JusApplicationTests {

  @Resource
  CarMapper carMapper;

  @Test
  public void contextLoads() throws InterruptedException {


    Thread[] threads = new Thread[10];
    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(){
        @Override
        public void run() {
          for (int j = 0; j < 100; j++) {
            carMapper.insert(new Car("1"));
          }
        }
      };
    }
    for (Thread thread : threads) {
      thread.start();
      thread.join();
    }
  }
}
