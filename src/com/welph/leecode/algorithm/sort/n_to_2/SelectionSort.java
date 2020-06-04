package com.welph.leecode.algorithm.sort.n_to_2;

import java.util.Arrays;

/**
 * 选择排序:
 * 从未排序序列中找到合适大小的数据交换, 时间复杂度始终是O(n^2)
 * <p>
 * 算法步骤:
 * 1.首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * .
 * 2.再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * .
 * 3.重复第二步，直到所有元素均排序完毕。
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            //找到小于当前位置的最小值
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            //若不为当前,则进行交换;
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
