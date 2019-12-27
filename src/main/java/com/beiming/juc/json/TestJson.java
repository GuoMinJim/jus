package com.beiming.juc.json;

import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestJson {

  public static void main(String[] args) throws UnsupportedEncodingException {
//    Car car = new Car(1, "123", "12333");
//    String s = JSONObject.toJSONString(car);
//    JSONObject object = JSONObject.parseObject(s);
//    Car car1 = object.toJavaObject(Car.class);
//    System.out.println(car1.getName());
//    System.out.println(car1.getId());
//    System.out.println(car1.getBrand());
    String s = "{\"session_key\":\"3tEN98vPnC3GXqOI79o5kw==\",\"openid\":\"odvO15BClQ50hFjl05dFmSjWww-8\"}";
    Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(s);
    System.out.println(parse.get("openid"));
    String str = "%E8%AF%88%E9%AA%97%E7%BD%AA";
    str = new String(str.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
    System.out.println(str);
  }

}
