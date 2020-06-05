package com.welph.leecode.algorithm.sort.n;

import java.util.Arrays;

/**
 * 计数排序:
 * .  通过数列保存数值的个数;
 * .  对数列中的计数进行顺序求和; ==> {1,2,3} ==> {1,3,6}
 * .  在从计数数列中回填数据时,并非是扫描计数数列,而是扫描原数组数列,
 * > 每次从数列中找到值,同时对应的位置减1;
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int max = arr[0];
        int len = arr.length;
        //找出范围
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //计数统计
        int[] ints = new int[max + 1];
        for (int i = 0; i < len; i++) {
            ints[arr[i]]++;
        }
        //顺序求和
        for (int i = 1; i < ints.length; i++) {
            ints[i] += ints[i - 1];
        }
        //关键的: 减数填充
        int[] temp = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            temp[ints[arr[i]] - 1] = arr[i];
            ints[arr[i]]--;
        }
        System.arraycopy(temp, 0, arr, 0, len);
    }
}
