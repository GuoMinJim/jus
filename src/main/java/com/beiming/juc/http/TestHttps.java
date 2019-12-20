package com.beiming.juc.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TestHttps {

  public static void main(String[] args) {
    // 获取opid
//    String s = TestHttps.httpsRequest(
//        "https://api.weixin.qq.com/sns/jscode2session?appid=wx39a7ef9befdf222d&secret=3774a5e28113de2637f85d83601a904e&js_code=061qwGC21SMNOQ1gj3C21Np0D21qwGCx&grant_type=authorization_code",
//        "GET", null);
//    System.out.println(s);
    //获取access_token
//    String s = TestHttps.httpsRequest(
//        "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx39a7ef9befdf222d&secret=3774a5e28113de2637f85d83601a904e",
//        "GET", null);
//    System.out.println(s);

//    String s = TestHttps.httpsRequest(
//        "https://api.weixin.qq.com/cgi-bin/user/info?access_token=27_-ktdZtWbf-X42jbwZq-D_hyBYrTk34fxEPiimhNRYw8qWC6jF4wOMsaqVlNoUmfHszbWL9DkfkVbffOVgazu_MInUfQKKta_dJ1cek1GdYQevnjLSFG0CBSlzu4WHTeAFAEPB&openid=odvO15BClQ50hFjl05dFmSjWww-8&lang=zh_CN",
//        "GET", null);
//    System.out.println(s);

//    String s= TestHttps.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=o2***kZ43f4uUkMuLY-6iB7iXvNS_w&lang=zh_CN","GET",null);
//    System.out.println(s);
    String token = "bearer "
        + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2Vfb2F1dGgiLCJyZXNvdXJjZV93b3JrIiwicmVzb3VyY2VfYnVzaW5lc3MiXSwiY3JlYXRlVGltZSI6MTU3NjU2NzYwNjQzMSwidXNlcl9uYW1lIjoi5YW05bqG5YWs5a6J6LC35a6B5bmzIiwiYXV0aG9yIjoid2giLCJzY29wZSI6WyJzY29wZV93c20iXSwicm9sZXMiOiLlhazlrokiLCJleHAiOjE1NzY2MTA4MDYsImF1dGhvcml0aWVzIjpbIuWFrOWuiSJdLCJqdGkiOiJkMGFiNWZjYS0wNmYyLTQyMzAtODhjNy02OGUwZDhlMGU1NTAiLCJjbGllbnRfaWQiOiJjbGllbnRfYnVzaW5lc3MifQ.36Wi09bURgacbqZ4KGcatSzmvSfebfGOlcwi1ANGPkM";
    String caseId = "116BCD804BC8CDACE053A50CC40AFD06";
    String s = TestHttps.httpRequest(
        "http://106.54.121.9:8086/case/info/ifDoByAjbsAndRybs?ajbs=116BCD804BC8CDACE053A50CC40AFD06&accessToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2Vfb2F1dGgiLCJyZXNvdXJjZV93b3JrIiwicmVzb3VyY2VfYnVzaW5lc3MiXSwiY3JlYXRlVGltZSI6MTU3NjYzMjExODA4OSwidXNlcl9uYW1lIjoi5YW05bqG5YWs5a6J6LC35a6B5bmzIiwiYXV0aG9yIjoid2giLCJzY29wZSI6WyJzY29wZV93c20iXSwicm9sZXMiOiLlhazlrokiLCJleHAiOjE1NzY2NzUzMTgsImF1dGhvcml0aWVzIjpbIuWFrOWuiSJdLCJqdGkiOiI0ZTlmMmU4Ny05YjY5LTRiYzQtODg4OS00YTViNmY3ZjM4NTQiLCJjbGllbnRfaWQiOiJjbGllbnRfYnVzaW5lc3MifQ.Gv_qk0QHHJDp3SOrCCMPgiA9tAIR80aAjGf-BaQlOEA",
        "POST", null);
    System.out.println(s);
  }

  public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
    StringBuffer buffer = null;
    try {
      //创建SSLContext
      SSLContext sslContext = SSLContext.getInstance("SSL");
      TrustManager[] tm = {new MyX509TrustManager()};
      //初始化
      sslContext.init(null, tm, new java.security.SecureRandom());
      //获取SSLSocketFactory对象
      SSLSocketFactory ssf = sslContext.getSocketFactory();
      URL url = new URL(requestUrl);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod(requestMethod);
      //设置当前实例使用的SSLSoctetFactory
      conn.setSSLSocketFactory(ssf);
      conn.connect();
      //往服务器端写内容
      if (null != outputStr) {
        OutputStream os = conn.getOutputStream();
        os.write(outputStr.getBytes(StandardCharsets.UTF_8));
        os.close();
      }

      //读取服务器端返回的内容
      InputStream is = conn.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(isr);
      buffer = new StringBuffer();
      String line = null;
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }


  public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
    StringBuffer buffer = null;
    try {
      URL url = new URL(requestUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setRequestMethod(requestMethod);
      conn.connect();
      //往服务器端写内容 也就是发起http请求需要带的参数
      if (null != outputStr) {
        OutputStream os = conn.getOutputStream();
        os.write(outputStr.getBytes(StandardCharsets.UTF_8));
        os.close();
      }

      //读取服务器端返回的内容
      InputStream is = conn.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(isr);
      buffer = new StringBuffer();
      String line = null;
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }

}
