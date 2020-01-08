package com.beiming.juc.jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1230 {


  int num = 0;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
//    new Day1230().levelOrder(root);
    System.out.println(new Day1230().levelOrder(root));

    String[] strings = new String[]{"cats", "dog", "sand", "and", "cat"};
    Arrays.sort(strings);
    System.out.println(strings[0]);
  }

  public TreeNode convertBST(TreeNode root) {
    if (root != null) {
      //遍历右子树
      convertBST(root.right);
      //遍历顶点
      root.val = root.val + num;
      num = root.val;
      //遍历左子树
      convertBST(root.left);
      return root;
    }
    return null;
  }


  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
      return lists;
    }
    handle(lists, root, num);
    return lists;
  }

  private void handle(List<List<Integer>> lists, TreeNode root, int num) {
    if (root == null) {
      return;
    }
    if (lists.size() <= num) {
      List<Integer> temp = new ArrayList<Integer>();
      temp.add(root.val);
      lists.add(temp);
    } else {
      lists.get(num).add(root.val);
    }
    lists.get(num).add(root.val);
    handle(lists, root.right, num + 1);
    handle(lists, root.left, num + 1);
  }

  private void level(List<TreeNode> nodes, List<List<Integer>> lists) {
    if (nodes.size() == 0) {
      return;
    }
    List<TreeNode> nodess = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    for (TreeNode node : nodes) {
      if (node.left != null) {
        list.add(node.left.val);
        nodess.add(node.left);
      }
      if (node.right != null) {
        list.add(node.right.val);
        nodess.add(node.right);
      }
    }
    if (list.size() != 0) {
      lists.add(list);
    }
    level(nodess, lists);
  }


  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();

    boolean[] memo = new boolean[n + 1];
    memo[0] = true;
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        if (memo[j] && wordDict.contains(s.substring(j, i))) {
          memo[i] = true;
        }
      }
    }
    return memo[n];
  }
}
