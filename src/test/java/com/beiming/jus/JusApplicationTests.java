package com.beiming.jus;

import com.beiming.juc.JusApplication;
import com.beiming.juc.mail.MailServiceTest;
import com.beiming.juc.web.dao.CarMapper;
import com.beiming.juc.web.domain.Car;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JusApplication.class)
public class JusApplicationTests {

  @Resource
  CarMapper carMapper;

  @Resource
  MailServiceTest mailService;

  @Resource
  MailServiceTest mailService1;

  @Test
  public void contextLoads() throws InterruptedException {

    Thread[] threads = new Thread[10];
    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread() {
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

  @Test
  public void test() {
    try {
      for (int i = 0; i < 10; i++) {
        new Thread(() -> {
          for (int j = 0; j < 100000; j++) {
            carMapper.insert(new Car("AAAAAA"));
            System.out.println("插入正常~~");
          }
        }).start();
      }
    } catch (Exception e) {
      System.out.println("插入数据库失败");
    }
  }

  /**
   * 发送邮件
   */
  @Test
  public void sendMail() throws MessagingException, InterruptedException {
    mailService.sendTextMail();
//    Thread.sleep(2000);
//    mailService.sendHtmlMail();
//    mailService.sendAttachmentsMail();
//    mailService.sendAttachmentsMailByUrl("1850440367@qq.com", "spring boot 邮件附件测试", "这是一个测试",
//        " https://preservation-1255516392.cos.ap-chengdu.myqcloud.com/dev/1564973381953/保单保函.pdf");
  }
}
