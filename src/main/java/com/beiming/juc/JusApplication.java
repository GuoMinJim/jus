package com.beiming.juc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.beiming.juc.web.dao")
@ComponentScan("com.beiming.juc")
public class JusApplication {

  public static void main(String[] args) {
    SpringApplication.run(JusApplication.class, args);
  }

}
