package com.beiming.juc.queue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegax {

  public static void main(String[] args) {
    String str =
        "犯罪嫌疑人张三，男，1992年8月4日生，身份证号码662871199208045383，汉族，小学，广东省清远市英德市人，户籍地清远市英德市大站镇，无业。2017年1月因涉嫌抢劫罪被我局刑事拘留，经英德人民检察院批准，于同年2月15日被依法逮捕，现羁押在英德市看守所。\n"
            + "犯罪嫌疑人李小四，男，1988年10月11日，身份证号64158219881011901X，汉族，初中，河源市东源县人，现住址广东省广州市白云区白云大道，无业。2017年1月12日因涉嫌抢劫罪被我局刑事拘留，经英德市人民检察院批准，于同年2月被依法逮捕，现羁押在广州市第三看守所。\n犯罪嫌疑人张三，男，1992年8月4日生，身份证号码662871199208045383，汉族，小学，广东省清远市英德市人，户籍地清远市英德市大站镇，无业。2017年1月因涉嫌抢劫罪被我局刑事拘留，经英德人民检察院批准，于同年2月15日被依法逮捕，现羁押在英德市看守所。\n"
            + "犯罪嫌疑人李小四，男，1988年10月11日，身份证号64158219881011901X，汉族，初中，河源市东源县人，现住址广东省广州市白云区白云大道，无业。2017年1月12日因涉嫌抢劫罪被我局刑事拘留，经英德市人民检察院批准，于同年2月被依法逮捕，现羁押在广州市第三看守所。\n";
    Pattern pattern = Pattern.compile("犯罪嫌疑人(?<name>),");
    Matcher matcher = pattern.matcher(str);
    while (matcher.find()) {
      System.out.println(matcher.group());
      System.out.println(matcher.group("name"));
    }
  }
}
