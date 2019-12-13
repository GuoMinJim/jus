package com.beiming.juc.jianzhioffer;

public class Day1212 {

  public static void main(String[] args) {
    long l = System.currentTimeMillis();
    System.out.println(new Day1212().magicalString(99999));
    System.out.println(System.currentTimeMillis() - l);
  }

  /**
   * Leetcode 481
   */
  public int magicalString(int n) {
    // 先构建字符串
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }
    int num = 2;
    StringBuilder str = new StringBuilder("122");
    int count = 1;
    while (str.length() < n) {
      if (num % 2 == 1) {
        // 添加2
        int integer = Integer.valueOf(String.valueOf(str.charAt(num))).intValue();
        str = integer == 2 ? str.append("22") : str.append("2");
        num++;
      } else {
        // 添加1
        int integer = Integer.valueOf(String.valueOf(str.charAt(num))).intValue();
        str = integer == 2 ? str.append("11") : str.append("1");
        count = count + integer;
        if (str.length() > n) {
          count = count - (str.length() - n);
          break;
        }
        num++;
      }
    }
    return count;
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {

    if (s.val != t.val) {
      return isSubtree(s.left, t) || isSubtree(s.right, t);
    } else {
      return check(s, t);
    }
//    return false;
  }

  public boolean check(TreeNode s, TreeNode t) {

    return true;
  }

  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
