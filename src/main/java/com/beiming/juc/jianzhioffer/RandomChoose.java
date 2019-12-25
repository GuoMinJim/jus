package com.beiming.juc.jianzhioffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 蓄水池采样算法
 */
public class RandomChoose {

  private int[] nums;
  private Random random = new Random();
  private List<Integer> list = new ArrayList<>();

  public RandomChoose(int[] nums) {
    this.nums = nums;
  }

  public static void main(String[] args) {
  }

  public int pick(int target) {
    list.clear();
    int point = 0;
    int length = nums.length;
    int res = 0;
    for (int i = 0; i < length; i++) {
      if (nums[i] == target) {
//        list.add(i);
        point++;
        if (random.nextInt(point + 1) == point) {
          res = i;
        }
      }
    }
    return res;
  }


}
