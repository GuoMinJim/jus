package com.beiming.juc.jianzhioffer;

import java.util.ArrayList;

public class Day1011 {

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10};
    new Day1011().findNumbersWithSum(array, 12);
  }

  /**
   * 输入一个递增排序的数组和一个数字S，在数组中查找两个数， 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的 1 2 3 4 5 6 7 8 9
   */

  public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
    ArrayList<Integer> list = new ArrayList<>();
    int s = 0;
    int right = array.length;
    for (int i = 0; i < array.length; i++) {
      for (int j = right - 1; j > 0; j--) {
        if ((array[i] + array[j]) > sum) {
          continue;
        } else if ((array[i] + array[j]) < sum) {
          right--;
          continue;
        } else {
          if (array[i] * array[j] > s) {
            continue;
          }
          list = new ArrayList<>();
          list.add(array[i]);
          list.add(array[j]);
        }
      }
    }
    return (list == null) ? new ArrayList<>() : list;
  }


  public ArrayList<Integer> findNumbersWithSum1(int[] array, int sum) {
    ArrayList<Integer> list = new ArrayList<>();

    return list;
  }

  public void handle(int left, int right, int sum) {
    handle(left + 1, right, sum);
    handle(left, right - 1, sum);
  }

}
