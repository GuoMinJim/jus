package com.beiming.juc.jianzhioffer;

import java.util.HashMap;

public class SnapshotArray {

  private HashMap<Integer, HashMap<Integer, Integer>> array;

  private Integer snap = 0;

  public SnapshotArray(int length) {
    array = new HashMap<>(length);
  }

  public static void main(String[] args) {

    SnapshotArray snapshotArray = new SnapshotArray(3);
    snapshotArray.set(0, 15);
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.get(0, 2));
    System.out.println(snapshotArray.snap());
    snapshotArray.set(1, 4);
  }

  public void set(int index, int val) {
    HashMap<Integer, Integer> hashMap = array.get(index);
    if (hashMap == null) {
      hashMap = new HashMap<>();
    }
    hashMap.put(snap, val);
    array.put(index, hashMap);
  }

  public int snap() {
    return snap++;
    //调用这个方法的总次数减1
  }

  public int get(int index, int snapId) {
    HashMap<Integer, Integer> hashMap = array.get(index);
    if (hashMap == null) {
      return 0;
    }
    for (int i = snapId; i >= 0; i--) {
      Integer integer = hashMap.get(i);
      if (integer != null) {
        return integer.intValue();
      }
    }
    return 0;
  }

}

/**
 * Your SnapshotArray object will be instantiated and called as such: SnapshotArray obj = new
 * SnapshotArray(length); obj.set(index,val); int param_2 = obj.snap(); int param_3 =
 * obj.get(index,snap_id);
 */
