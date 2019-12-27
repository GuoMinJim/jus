package com.beiming.juc.jianzhioffer;

public class Day1225 {


  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    node1.next = new ListNode(3);
    node1.next.next = new ListNode(5);

    ListNode node2 = new ListNode(2);
    node2.next = new ListNode(4);
    node2.next.next = new ListNode(6);

    ListNode node = new Day1225().mergeTwoLists(node1, node2);
    System.out.println(new Day1225().kthGrammar(5, 2));
    System.out.println(node);
    int[] nums = new int[]{-3, -2, -1};
    System.out.println(new Day1225().maxSubArray(nums));
  }

  /**
   * leetcode递归总结
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode node = new ListNode(0);
    mergeTwoList(l1, l2, node);
    return node.next;
  }

  public void mergeTwoList(ListNode l1, ListNode l2, ListNode node) {
    if (l1 == null) {
      node.next = l2;
      return;
    }
    if (l2 == null) {
      node.next = l1;
      return;
    }
    if (l1.val < l2.val) {
      node.next = new ListNode(l1.val);
      mergeTwoList(l1.next, l2, node.next);
    } else {
      node.next = new ListNode(l2.val);
      mergeTwoList(l1, l2.next, node.next);
    }
  }


  /**
   * 第k个字符
   */
  public int kthGrammar(int n, int k) {
    if (n == 2 && k == 1) {
      return 0;
    }
    if (n == 2 && k == 2) {
      return 1;
    }
    int num = kthGrammar(n - 1, (k + 1) / 2);
    if (num == 0 && k % 2 == 0) {
      return 1;
    } else if (num == 0 && k % 2 == 1) {
      return 0;
    } else if (num == 1 && k % 2 == 0) {
      return 0;
    } else {
      return 1;
    }
  }


  public int removeDuplicates(int[] nums) {

    int length = nums.length;
    int temp;
    int count = 0;
    for (int i = 0; i < length - 1; i++) {
      if (count >= length - 1) {
        break;
      }
      if (nums[i] == nums[i + 1]) {
        temp = nums[i + 1];
        for (int j = i + 1; j < length - 1; j++) {
          nums[j] = nums[j + 1];
        }
        nums[length - 1] = temp;
        i--;
        count++;
      }

    }
    for (int i = 0; i < length - 1; i++) {
      if (nums[i] >= nums[i + 1]) {
        return i + 1;
      }
    }
    return length;

  }


  /**
   * 金国民的动态规划
   */


  public int maxProfit(int[] prices) {
    int length = prices.length;
    if (length == 0) {
      return 0;
    }
    int[] nums = new int[length];
    nums[0] = 0;
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        if (prices[j] > prices[i]) {
          nums[j] = Math.max(Math.max(nums[j - 1], prices[j] - prices[i]), nums[j]);
        } else {
          nums[j] = nums[j - 1];
        }
      }
    }
    return nums[length - 1];
  }


  public int maxSubArray(int[] nums) {
    int length = nums.length;
    if (length == 0) {
      return 0;
    }
    if (length == 1) {
      return nums[0];
    }
    int i = 1;
    int sum = nums[0];
    int tempsum = nums[0];
    while (i < length) {
      if (nums[i] + tempsum > tempsum || nums[i] + tempsum > 0) {
        if (nums[i] > tempsum + nums[i]) {
          tempsum = nums[i];
        } else {
          tempsum = tempsum + nums[i];
        }
      } else {
        tempsum = nums[i];
      }
      sum = Math.max(sum, tempsum);
      i++;
    }
    return sum;
  }

  /**
   * if (nums[i] < 0 && sum ==0) { i++; } else if (nums[i] < 0 && sum>=0){ if (sum+nums[i] <0) {
   * tempsum = 0; i++; } else { tempsum = sum+nums[i]; i++; } } else { tempsum = tempsum + nums[i];
   * sum = Math.max(tempsum,sum); i++; }
   */

  public int rob(int[] nums) {
//dp 方程 dp[i] = max(dp[i-2]+nums[i], dp[i-1])
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    // memo[i] 表示考虑抢劫 nums[i...n-1] 所能获得最大收益（不是说一定从 i 开始抢劫）
    int[] memo = new int[n];
    // 先考虑最简单的情况
    memo[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      // memo[i] 的取值在考虑抢劫 i 号房子和不考虑抢劫之间取最大值
      memo[i] = Math.max(nums[i] + (i + 2 >= n ? 0 : memo[i + 2]),
          nums[i + 1] + (i + 3 >= n ? 0 : memo[i + 3]));
    }
    return memo[0];
  }

}
