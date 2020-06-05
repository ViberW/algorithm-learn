package com.welph.leecode.algorithm.sort.nlog_n;

import java.util.Arrays;

/**
 * 希尔排序:
 * .  也称为递减增量排序算法. 插入排序的改进版本(非稳定)
 * 改进插入排序方案:
 * 1. 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
 * 2. 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
 * <p>
 * 希尔排序思路: 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 * >            待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序
 * <p>
 * 算法步骤:
 * 1.选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 * .
 * 2.按增量序列个数 k，对序列进行 k 趟排序；
 * .
 * 3.每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，
 * > 分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
        int len = arr.length;
        //动态规划增量
        int gap = 1;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        //按增量序列个数 k，对序列进行 k 趟排序；
        while (gap > 0) {
            //每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，
            for (int i = gap; i < arr.length; i++) {
                //插入排序的逻辑
                // 分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
                {
                    int val = arr[i];
                    int j = i - gap;
                    while (j >= 0 && arr[j] > val) {
                        arr[j + gap] = arr[j];
                        j -= gap;
                    }
                    arr[j + gap] = val;
                }
            }
            gap = (int) Math.floor(gap / 3);
        }
    }
}
