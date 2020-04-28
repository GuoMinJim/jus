package com.beiming.juc.io.bio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class IOClient {

  public static void main(String[] args) throws IOException {

    Socket socket = new Socket("127.0.0.1", 3333);
    new Thread(() -> {
//      while (true) {
      try {
        OutputStream outputStream = socket.getOutputStream();
        File file = new File("C:\\Users\\18504\\Desktop\\file\\123.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        bis.read(bytes);
        outputStream.write(bytes);
        Thread.sleep(2000);
      } catch (IOException e) {

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      }
    }).start();
  }
}
