package com.beiming.juc.mail.impl;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceCompositeResolver;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 * @author tangping
 * @date 2019/10/24 0024 21:34
 * @Desc:
 */
public class EmailTest {

  public static void main(String[] args) throws Exception {
    sendHtmlEmailWithImg();
  }

  public static void sendHtmlEmailWithImg() throws Exception {
    String htmlEmailContent = "这是一张用于测试的图片，请查收。";
    ImageHtmlEmail email = new ImageHtmlEmail(); //用ImageHtmlEmail来发送

    email.setDebug(true); //可以看到执行过程的debug信息
    email.setCharset("UTF-8"); //防止乱码
    email.setSSLCheckServerIdentity(true);
    email.setSslSmtpPort("465"); // 设定SSL端口

    // 解析本地图片和网络图片都有的html文件重点就是下面这两行；
    // ImageHtmlEmail通过setDataSourceResolver来识别并嵌入图片
    // 查看DataSourceResolver的继承结构发现有几个好用的子类
    DataSourceResolver[] dataSourceResolvers =
        new DataSourceResolver[]{new DataSourceFileResolver(), // 添加DataSourceFileResolver用于解析本地图片
            new DataSourceUrlResolver(
                new URL("http://"))}; // 添加DataSourceUrlResolver用于解析网络图片，注意：new URL("http://")
    // DataSourceCompositeResolver类可以加入多个DataSourceResolver,
    // 把需要的DataSourceResolver放到一个数组里传进去就可以了；
    email.setDataSourceResolver(new DataSourceCompositeResolver(dataSourceResolvers));
    // 邮箱登陆信息
    email.setHostName("smtp.163.com");
    email.addTo("1850440367@qq.com", "tang");
    email.setFrom("13219811544@163.com", "13219811544@163.com"); // 必须和登陆邮箱名一致，否则会报错
    email.setAuthenticator(new DefaultAuthenticator("13219811544@163.com", "022997zy")); // 邮箱名和登陆密码
    email.setSubject("发送一张图片，看看是否可以收到。");

    email.setHtmlMsg(htmlEmailContent);
    String url = "http://downsc.chinaz.net/Files/DownLoad/jianli/201806/jianli8167.rar";
    String name = "jianli8167.rar";
    addAttachment(email, url, name);
    //如果客户端不去持HTML格式会显示这句话，不过应该很少有不支持HTML格式的客户端了吧
    email.setTextMsg("你的邮箱客户端不支持HTML格式邮件");
    email.send();
  }

  /**
   * 为邮件添加附件
   *
   * @param email 邮件
   * @param url 附件链接
   * @param name 附件名称,最好有后缀名才能预览文件
   */
  public static void addAttachment(ImageHtmlEmail email, String url, String name) {
    // 保证附件名的长度和链接一一对应

    EmailAttachment attachment = new EmailAttachment();
    try {
      attachment.setURL(new URL(url));
      attachment.setDisposition(EmailAttachment.ATTACHMENT);
      // attachment.setDescription("Apache logo");
      attachment.setName(name);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    try {
      email.attach(attachment); // 将附件添加到邮件中
    } catch (EmailException e) {
      e.printStackTrace();
    }
  }

}
