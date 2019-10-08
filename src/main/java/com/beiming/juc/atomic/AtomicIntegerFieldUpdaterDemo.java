package com.beiming.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
public class AtomicIntegerFieldUpdaterDemo {

  class DemoData{
    public volatile  int value1 = 1;
    volatile int value2 = 2;
    protected  volatile  int value3 = 3;
    private volatile  int value4 = 4;
  }
  AtomicIntegerFieldUpdater<DemoData> getUpdater(String filedName) {
    return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, filedName);
  }

  /**
   * 因为反射 修饰符不可见 /
   */
  void doit(){
    DemoData data = new DemoData();
    System.out.println("1 ==> "+getUpdater("value1").getAndSet(data, 10));
    System.out.println("2 ==> "+getUpdater("value2").incrementAndGet(data));
    System.out.println("3 ==> "+getUpdater("value3").decrementAndGet(data));
    System.out.println("true ==> "+getUpdater("value4").compareAndSet(data, 4, 5));
  }
  public static void main(String[] args) {
    AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
    demo.doit();
  }
}
