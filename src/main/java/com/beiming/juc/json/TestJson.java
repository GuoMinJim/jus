package com.beiming.juc.json;

import com.alibaba.fastjson.JSONObject;
import com.beiming.juc.web.domain.Car;

public class TestJson {

  public static void main(String[] args) {
    Car car = new Car(1, "123", "12333");

    String s = JSONObject.toJSONString(car);
    JSONObject object = JSONObject.parseObject(s);
    Car car1 = object.toJavaObject(Car.class);
    System.out.println(car1.getName());
    System.out.println(car1.getId());
    System.out.println(car1.getBrand());
  }

}
