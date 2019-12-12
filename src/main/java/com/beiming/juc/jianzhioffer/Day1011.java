package com.beiming.juc.jianzhioffer;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;

public class Day1011 {

  public static void main(String[] args) {
    String s = replaceIdCard("511023199711039574");
    System.out.println(s);
  }

  public static String replaceIdCard(String idCard) {
    String value = idCard.charAt(0) + "";
    if (StringUtils.isEmpty(idCard)) {
      return idCard;
    } else {
      for (int i = 0; i < idCard.length(); i++) {
        if (i == idCard.length() - 1) {
          value = Joiner.on("*").join(value, idCard.charAt(i));
        } else {
          value = Joiner.on("*").join(value, "");
        }
      }
    }
    return value;
  }

  /**
   * 输入一个递增排序的数组和一个数字S，在数组中查找两个数， 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的 1 2 3 4 5 6 7 8 9
   */

  public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
    ArrayList<Integer> list = new ArrayList<>();
    if (array.length == 0) {
      return new ArrayList<>();
    }
    if (array.length == 2) {
      for (int i = 0; i < array.length; i++) {
        list.add(array[i]);
      }
      return list;
    }
    int s = array[array.length - 1] * array[array.length - 1];
    int right = array.length;
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if ((array[i] + array[j]) > sum) {
          break;
        } else if ((array[i] + array[j]) < sum) {
          continue;
        } else {
          if (array[i] * array[j] < s) {
            s = array[i] * array[j];
            list.clear();
            list.add(array[i]);
            list.add(array[j]);
          }
        }
      }
    }
    return list;
  }

  /**
   * 字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”
   */
  public String leftRotateString(String str, int n) {
    if (str.length() < n) {
      return str;
    }
    String substring = str.substring(0, n);
    str = str.substring(n);
    return str + substring;
  }

  /**
   * “student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”
   */
  public String reverseSentence(String str) {
    if (" ".equals(str)) {
      return " ";
    }
    String[] s = str.split(" ");
    if (s.length == 1) {
      return s[0];
    } else {
      str = "";
      for (int i = s.length - 1; i >= 0; i--) {
        if (i == 0) {
          str = str + s[i];
        } else {
          str = str + s[i] + " ";
        }
      }
    }
    return str;
  }

  // 12345  02345 00345 12040 02340
  public boolean isContinuous(int[] numbers) {
    if (numbers.length == 0) {
      return false;
    }
    int max = 0;
    int min = 0;
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] != 0) {
        min = numbers[i];
        max = numbers[i];
        break;
      }
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == 0) {
        continue;
      }
      if (map.containsKey(numbers[i])) {
        return false;
      }
      map.put(numbers[i], numbers[i]);
      min = numbers[i] < min ? numbers[i] : min;
      max = numbers[i] > max ? numbers[i] : max;
      if (max - min > 4) {
        return false;
      }
    }
    return true;
  }

  /**
   * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
   * 1 2 3 4 5
   */
  public int lastRemainingSolution(int n, int m) {
    if (n == 0) {
      return -1;
    }
    int num = n;
    //标所在的位置
    int a = 0;
    m = m - 1;
    while (num != 1) {
      a = a + m;
      a = a > n ? a % n : a;
      num--;
    }
    return a;
  }

}
