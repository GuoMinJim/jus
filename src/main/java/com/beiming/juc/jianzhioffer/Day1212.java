package com.beiming.juc.jianzhioffer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1212 {

  public static void main(String[] args) throws UnsupportedEncodingException {
//    long l = System.currentTimeMillis();
//    System.out.println(new Day1212().magicalString(99999));
//    System.out.println(System.currentTimeMillis() - l);
//      TreeNode root = new TreeNode(20);
//      root.left = new TreeNode(10);
//      root.right = new TreeNode(100);
//      root.left.left = new TreeNode(10);
//      root.left.right = new TreeNode(5);
//      root.left.right.left = new TreeNode(6);
//    int[] array = new int[]{0, 1, 0, 3, 12};
//    new Day1212().moveZeroes(array);
//    System.out.println();
//    int i = new Day1212().lastStoneWeight(array);
//    System.out.println(i);
//    new Day1212().smallestFromLeaf(root);
    String[] strs = new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer",
        "ForceFeedBack"};
    String pattern = "FB";
    List<Boolean> list = new Day1212().camelMatch(strs, pattern);
    System.out.println(list);
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

  /**
   * 988. 从叶结点开始的最小字符串
   */

  public String smallestFromLeaf(TreeNode root) {
    if (root == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    StringBuilder right = getRight(root.right, sb.append(new StringBuffer(root.val)));
    System.out.println(right);
    return "";
  }

  public StringBuilder getRight(TreeNode treeNode, StringBuilder sb) {
    if (treeNode == null) {
      return sb;
    }
    if (treeNode.left == null) {
      if (treeNode.right == null) {
        return sb.append(treeNode.val);
      } else {
        return getRight(treeNode.right, sb.append(treeNode.val));
      }
    } else {
      if (treeNode.right == null) {
        return getRight(treeNode.right, sb.append(treeNode.val));
      } else {
        if (treeNode.left.val > treeNode.right.val) {
          return getRight(treeNode.right, sb.append(treeNode.val));
        } else {
          return getRight(treeNode.left, sb.append(treeNode.val));
        }
      }
    }
  }


  /**
   * 1046. 最后一块石头的重量
   */
  public int lastStoneWeight(int[] stones) {
    if (stones.length == 0) {
      return 0;
    }
    if (stones.length == 1) {
      return stones[0];
    }
    if (stones.length == 2) {
      return Math.abs(stones[0] - stones[1]);
    }
    Arrays.sort(stones);
    int size = stones.length - 1;
    while (stones[size - 2] != 0) {
      stones[size] = Math.abs(stones[size] - stones[size - 1]);
      stones[size - 1] = 0;
      Arrays.sort(stones);
    }
    return Math.abs(stones[size] - stones[size - 1]);
  }

  /**
   * 238. 除自身以外数组的乘积 输入: [1,2,3,4] 输出: [24,12,8,6]
   */
  public int[] productExceptSelf(int[] nums) {
    int length = nums.length;
    if (length == 0) {
      return new int[]{0};
    }
    int[] re = new int[length];
    int help = 1;
    for (int i = 0; i < length; i++) {
      re[i] = help;
      help *= nums[i];
    }
    help = 1;
    for (int i = length - 1; i >= 0; i--) {
      re[i] *= help;
      help *= nums[i];
    }
    return re;
  }

  public void moveZeroes(int[] nums) {

    int length = nums.length;
    for (int i = length - 1; i >= 0; i--) {
      if (nums[i] == 0) {
        int temp;
        for (int j = i + 1; j < length; j++) {
          temp = nums[j];
          nums[j - 1] = temp;
        }
        nums[length - 1] = 0;
      }
    }
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }


  public List<Boolean> camelMatch(String[] queries, String pattern)
      throws UnsupportedEncodingException {

    List<Boolean> list = new ArrayList<>();
    for (int i = 0; i < queries.length; i++) {
      byte[] bytes = queries[i].getBytes();
      byte[] patternBytes = pattern.getBytes();
      int length = bytes.length;
      int num = 0;
      boolean flag = false;
      for (int j = 0; j < length; j++) {
//        if (num >= patternBytes.length) {
//          list.add(true);
//          break;
//        }
        flag = patternBytes[num] >= 'a';
        if (bytes[j] == patternBytes[num]) {
          num++;
          continue;
        } else if (flag) {
          list.add(false);
          break;
        } else if (!flag && num >= patternBytes.length) {
          list.add(false);
        } else if (j == length - 1) {
          list.add(true);
        } else {
          continue;
        }
      }
    }
    return list;
  }
}
