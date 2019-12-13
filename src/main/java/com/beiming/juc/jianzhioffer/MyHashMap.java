package com.beiming.juc.jianzhioffer;

/**
 * 706. 设计哈希映射
 */
public class MyHashMap {

  private TreeNode[] array;

  /**
   * Initialize your data structure here.
   */
  public MyHashMap() {
    array = new TreeNode[1024];

  }

  /**
   * value will always be non-negative.
   */
  public void put(int key, int value) {
    int hash = key % 1024;
    TreeNode treeNode = array[hash];
    if (treeNode == null) {
      array[hash] = new TreeNode(value);
    }
    if (value > treeNode.value) {

    }

  }

  /**
   * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
   * for the key
   */
  public int get(int key) {
    return 1;
  }

  /**
   * Removes the mapping of the specified value key if this map contains a mapping for the key
   */
  public void remove(int key) {

  }

  class TreeNode {

    private TreeNode left;
    private TreeNode right;
    private int value;

    public TreeNode(int value) {
      this.value = value;
    }
  }

}
