package com.welph.leecode.algorithm.sort.n_to_2;

import java.util.Arrays;

/**
 * 插入排序:
 * 通过区分已排序和未排序区间, 对未排序区间的数据选择放置合适的已排序的位置;
 * <p>
 * 算法步骤:
 * 1.将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * .
 * 2.从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 * . （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return;
        }
        for (int i = 1; i < len; i++) {
            int val = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {// 找到合适的位置
                if (arr[j] > val) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = val;
        }
    }
}
