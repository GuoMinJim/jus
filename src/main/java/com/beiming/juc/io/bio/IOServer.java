package com.beiming.juc.io.bio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(3333);
    new Thread(() -> {
      while (true) {
//        try {
        Socket socket = null;
        try {
          socket = serverSocket.accept();
        } catch (IOException e) {

        }
        // 每一个新的连接都创建一个线程，负责读取数据
        Socket finalSocket = socket;
        new Thread(() -> {
          try {
            int len;
            byte[] data = new byte[1024];
            InputStream inputStream = finalSocket.getInputStream();
            FileOutputStream fis = new FileOutputStream(
                new File("C:\\Users\\18504\\Desktop\\file\\321.jpg"));
            // 按字节流方式读取数据
            while ((len = inputStream.read(data)) != -1) {
              fis.write(data);
//                System.out.println(new String(data, 0, len));
            }
          } catch (IOException e) {
          }
        }).start();
//        } catch (IOException e) {
//        }
      }
    }).start();
  }
}
