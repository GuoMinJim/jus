package com.beiming.juc.mail;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private JavaMailSenderImpl mailSender;
  private String from = "13219811544@163.com";

  /**
   * 发送包含简单文本的邮件
   */
  public void sendTextMail() {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    // 设置收件人，寄件人
    simpleMailMessage.setTo("1850440367@qq.com");
    simpleMailMessage.setFrom("13219811544@163.com");
    simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
    simpleMailMessage.setText("这里是一段简单文本。");
    // 发送邮件
    mailSender.send(simpleMailMessage);
  }

  /**
   * 发送html格式
   */
  public void sendHtmlMail() throws MessagingException {
    MimeMessage mimeMailMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
    mimeMessageHelper.setTo("1850440367@qq.com");
    mimeMessageHelper.setFrom("13219811544@163.com");
    mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

    // 拼装邮件
    StringBuilder sb = new StringBuilder();
    sb.append("<html><head></head>");
    sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
    sb.append("</html>");
    //启用html
    mimeMessageHelper.setText(sb.toString(), true);
    // 发送邮件
    mailSender.send(mimeMailMessage);
  }

  /**
   * 发送附带附件
   */
  public void sendAttachmentsMail() throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    mimeMessageHelper.setTo("1850440367@qq.com");
    mimeMessageHelper.setFrom("13219811544@163.com");
    mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【邮件附件】");
    mimeMessage.setText("这是一个附带附件的邮件测试");
    String filePath = "F:\\cf0f8304fbe7109191f4ac8899a4d3f.jpg";
    File file1 = new File("F:\\cf0f8304fbe7109191f4ac8899a4d3f.jpg");
    if (!file1.exists()) {
      file1.mkdir();
    }
    FileSystemResource file = new FileSystemResource(file1);
    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
    mimeMessageHelper.addAttachment(fileName, file);
    mailSender.send(mimeMessage);
    System.out.println("邮件发送成功");
  }

  public void sendAttachmentsMailByUrl(String toAddr, String title, String content,
      String filePath) {
    MimeMessage message = mailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(from);
      helper.setTo(toAddr);
      helper.setSubject(title);
      helper.setText(content, true);

      URI url = new URI(filePath);
      FileSystemResource file = new FileSystemResource(new File(url));
      String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
      helper.addAttachment(fileName, file);

      mailSender.send(message);
      logger.info("带附件的邮件已经发送。");
    } catch (MessagingException e) {
      logger.error("发送带附件的邮件时发生异常！", e);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
