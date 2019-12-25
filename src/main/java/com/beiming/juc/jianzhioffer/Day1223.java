package com.beiming.juc.jianzhioffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1223 {


  private Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
//    TreeNode root = new TreeNode(1);
//    root.left = new TreeNode(2);
//    root.right = new TreeNode(5);
//    root.left.left = new TreeNode(3);
//    root.left.right = new TreeNode(4);
//    root.right.right = new TreeNode(6);
//    new Day1223().flatten(root);
//    while (root != null) {
//      System.out.print(root.val + " ");
//      root = root.right;
//    }
//    char[] s = new char[]{'h','e','l','l','o'};
//    new Day1223().reverseString(s);
//    for (int i = 0; i < s.length; i++) {
//      System.out.println(s[i]);
//    }
//    System.out.println();
//    List<List<Integer>> generate = new Day1223().generate(5);
////    System.out.println(generate);
//    long l = System.currentTimeMillis();
//    List<Integer> row = new Day1223().getRow(29);
//    System.out.println(System.currentTimeMillis() - l);
//
//    long l1 = System.currentTimeMillis();
//    List<Integer> row1 = new Day1223().get(29);
//    System.out.println(System.currentTimeMillis() - l1);
//    System.out.println(row1);

    double v = new Day1223().myPow(2.00000, -2);
    System.out.println(v);
  }

  /**
   * 114. 二叉树展开为链表
   */
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    flatten(root.left);
    flatten(root.right);
    TreeNode tmp = root.right;
    root.right = root.left;
    root.left = null;
    while (root.right != null) {
      root = root.right;
    }
    root.right = tmp;
  }

  public int candy(int[] ratings) {
    int length = ratings.length;
    int[] num = new int[length];
    for (int i = 0; i < length; i++) {
      num[i] = 1;
    }
    for (int i = 0; i < length - 1; i++) {
      if (ratings[i + 1] > ratings[i]) {
        num[i + 1] = num[i] + 1;
      }
    }
    for (int i = length - 1; i > 0; i--) {
      if (ratings[i] < ratings[i - 1] && num[i] >= num[i - 1]) {
        num[i - 1] = num[i] + 1;
      }
    }
    int sum = 0;
    for (int i = 0; i < length; i++) {
      sum = sum + num[i];
    }
    return sum;
  }

  public void reverseString(char[] s) {
    if (s.length == 0) {
      return;
    }
    int length = s.length;
    char[] chars = new char[length];
    revert(s, 0);
  }

  private void revert(char[] s, int i) {
    if (i > s.length / 2) {
      return;
    }
    revert(s, i + 1);
    if (i == 0) {
      return;
    }
    char tmp = '0';
    tmp = s[s.length - i];
    s[s.length - i] = s[i - 1];
    s[i - 1] = tmp;
  }

  public ListNode swapPairs(ListNode head) {
    swapPairs1(head);
    return head;
  }

  private void swapPairs1(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    swapPairs(head.next.next);
    int temp = head.next.val;
    head.next.val = head.val;
    head.val = temp;
  }

  public List<Integer> get(int num) {
    List<List<Integer>> generate = generate(num);
    return generate.get(num - 1);
  }

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> lists = new ArrayList<>();
    for (int i = 1; i <= numRows; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 1; j <= i; j++) {
        if (j == 1 || j == i) {
          list.add(1);
        } else {
          list.add(lists.get(i - 2).get(j - 1) + lists.get(i - 2).get(j - 2));
        }
      }
      lists.add(list);
    }
    return lists;
  }

  public List<Integer> getRow(int rowIndex) {
    List<Integer> objects = new ArrayList<>();
    handle(objects, rowIndex + 1, 1);
    return objects;
  }

  private void handle(List<Integer> objects, int rowIndex, int num) {
    if (num == rowIndex) {
      objects.add(1);
      return;
    }
    handle(objects, rowIndex, num + 1);
    if (num == 1) {
      objects.add(1);
    } else {
      objects.add(getValue(rowIndex, num));
    }
  }

  private int getValue(int i, int num) {
    if (num == 1 || num == i) {
      return 1;
    }
    return getValue(i - 1, num) + getValue(i - 1, num - 1);
  }

  public int fib(int n) {
    if (n == 0) {
      map.put(0, 0);
      return 0;
    }
    if (n == 1) {
      map.put(1, 1);
      return 1;
    }
    if (map.containsKey(n)) {
      return map.get(n);
    }
    return fib(n - 1) + fib(n - 2);
  }

  public int climbStairs(int n) {
    if (map.containsKey(n)) {
      return map.get(n);
    }
    if (n == 0) {
      map.put(0, 0);
      return 0;
    }
    if (n == 1) {
      map.put(1, 1);
      return 1;
    }
    if (n == 2) {
      map.put(2, 2);
      return 2;
    }
    int i = climbStairs(n - 1) + climbStairs(n - 2);
    map.put(n, i);
    System.out.println(i);
    return i;
  }

//  public int climbStairs(int n) {
//    if (n==0) {
//      return 0;
//    }
//    if (n==1){
//      return 1;
//    }
//    if (n==2) {
//      return 2;
//    }
//    return climbStairs(n-1) + climbStairs(n-2);
//  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.right) + 1, maxDepth(root.right) + 1);
  }

  public double myPow(double x, int n) {
    if (n > 0) {
      if (n == 1) {
        return x;
      }
      return myPow(x, n - 1) * x;
    } else if (n < 0) {
      if (n == -1) {
        return (double) 1 / x;
      }
      return myPow(x, n + 1) * ((double) 1 / x);
    } else {
      return 1;
    }

  }
}
