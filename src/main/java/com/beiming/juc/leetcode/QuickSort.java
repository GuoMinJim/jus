package com.beiming.juc.leetcode;

public class QuickSort {

  public static void main(String[] args) {
    int[] arr = {3};
    new QuickSort().quickSortTest(arr, 0, arr.length - 1);
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  /**
   * 快速排序
   */
  public void quickSortTest(int[] arr, int i, int j) {
    if (i == j || i > arr.length - 1 || j > arr.length - 1) {
      return;
    }
    int begin = i;
    int end = j;
    int part = arr[begin];
    boolean falg = true;
    while (begin != end) {
      if (falg) {
        if (arr[end] < part) {
          swap(arr, begin, end);
          falg = false;
        } else {
          end--;
        }
      } else {
        if (arr[begin] > part) {
          swap(arr, begin, end);
          falg = true;
        } else {
          begin++;
        }
      }
    }
    quickSortTest(arr, i, begin);
    quickSortTest(arr, begin + 1, j);
  }


  public String getMaxStr(String str) {
    return "";
  }


  public void swap(int[] arr, int begin, int end) {
    int temp = arr[begin];
    arr[begin] = arr[end];
    arr[end] = temp;
  }
}
