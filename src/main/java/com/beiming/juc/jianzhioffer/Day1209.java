package com.beiming.juc.jianzhioffer;

public class Day1209 {

  public static void main(String[] args) {
    System.out.println(2 ^ 7);
  }

  public int lastRemainingSolution(int n, int m) {
    if (n == 0) {
      return -1;
    }
    int s = 0;
    for (int i = 2; i <= n; i++) {
      s = (s + m) % i;
    }
    return s;
  }

}
