package com.beiming.juc.jianzhioffer;

public class Day1209 {

  public static void main(String[] args) {
    int i = 5;
    System.out.println(++i);
    int j = 5;
    System.out.println(j++);
//    int[] array = {3,4,2, 5, 3, 6, 9, 7, 8};
//    System.out.println(new Day1209().lengthOfLIS3(array));
  }

  public static int sert(int[] dp, int i, int len, int key) {
    if (dp[len] < key) {
      return len + 1;
    }
    while (i < len) {
      int mid = (i + len) / 2;
      if (dp[mid] < key) {
        i = mid + 1;
      } else {
        len = mid;
      }

    }
    return i;
  }

  /**
   * leetCode 300
   */
  public int lengthOfLIS(int[] nums) {
    int maxL = 0;
    int[] dp = new int[nums.length];
    for (int num : nums) {
      // 二分法查找, 也可以调用库函数如binary_search
      int low = 0;
      int high = maxL;
      while (low < high) {
        int mid = low + (high - low) / 2;
        if (dp[mid] < num) {
          low = mid + 1;
        } else {
          high = mid;
        }
      }
      dp[low] = num;
      if (low == maxL) {
        maxL++;
      }
    }
    return maxL;
  }

  /**
   * 第一个子问题
   */
  public int first(int[] nums, int min, int count, int index) {
    if (nums.length - index == 0) {
      return 1;
    }
    for (int i = index + 1; i < nums.length; i++) {
      if (nums[i] < min) {
        // 一旦找到最小值就开始新的运算
        int first = first(nums, nums[i], count, i);
        return first > count ? first : count;
      } else {
        // 处理递增但是多种子序列的情况

      }
    }
    return 0;
  }

  /**
   * 第二个子问题
   */
  public int two(int[] nums, int index, int count, int min) {
    for (int i = index + 1; i < nums.length; i++) {
      if (nums[i] < min) {
        return count;
      } else {

      }
    }
    return 0;
  }

  public int lengthOfLIS1(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] dp = new int[n];
    int len = 0;
    dp[0] = nums[0];
    for (int i = 1; i < n; i++) {
      int pos = sert(dp, 0, len, nums[i]);
      dp[pos] = nums[i];
      if (len < pos) {
        len = pos;
      }
    }
    return len + 1;
  }

  public int lengthOfLIS3(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      int max = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          max = Math.max(max, dp[j] + 1);
        }
      }
      dp[i] = max;
    }
    return dp[n - 1];
  }
}