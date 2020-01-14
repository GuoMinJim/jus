package com.beiming.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TestBlockingQueue {


  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);

    ConcurrentLinkedDeque q = new ConcurrentLinkedDeque();
    q.offer("张三");
    q.offer("李四");
    q.offer("王五");


  }

}
