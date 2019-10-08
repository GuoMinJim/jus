package com.beiming.juc.atomic;

import com.beiming.juc.web.domain.Car;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个对象池  java in concurrency里面的代码  敲一敲
 */
public class ObjectCache<T> {

  public interface ObjectFactory<T>{
    T makeObject();
  }
  class Node{
    T obj;
    Node next;
  }

  final int capacity;

  final ObjectFactory<T> factory;

  final Lock lock = new ReentrantLock();

  final Semaphore semaphore;

  private Node head;

  private Node tail;

  public ObjectCache(int capacity, ObjectFactory<T> factory) {
    this.capacity = capacity;
    this.factory = factory;
    this.semaphore = new Semaphore(this.capacity);
    this.head = null;
    this.tail = null;
  }

  private T getObject() throws InterruptedException {
    semaphore.acquire();
    return getNextObject();
  }

  private T getNextObject() {
    lock.lock();
    try {
      if (head == null) {
        return factory.makeObject();
      } else {
        Node ret = head;
        head = head.next;
        if (head ==null) tail =null;
        ret.next = null;
        return ret.obj;
      }
    } finally {
      lock.unlock();
    }
  }

  private void returnObjectToPool(T t) {
    lock.lock();
    try {
      Node node = new Node();
      node.obj = t;
      if (tail == null) {
        head = tail = node;
      } else {
        tail.next =node;
        tail = node;
      }
    } finally {
      lock.unlock();
    }
  }

  public void returnObject(T t) {
    returnObjectToPool(t);
    semaphore.release();
  }

  public static void main(String[] args) {
    ObjectFactory<Car> carObjectFactory = new ObjectFactory<Car>() {
      @Override
      public Car makeObject() {
        return new Car(this.hashCode());
      }
    };
    ObjectCache<Car> carObjectCache = new ObjectCache<>(2,carObjectFactory);
    for (int i = 0; i < 10; i++) {
      new Thread(){
        @Override
        public void run() {
          Car nextObject = carObjectCache.getNextObject();
          System.out.println(nextObject.getId());
        }
      }.start();
    }
  }
}
