package com.welph.leecode.algorithm.sort.nlog_n;

import java.util.Arrays;

/**
 * 快速排序: 分治思想 -- 冒泡排序基础上的递归分治
 * 选择排序数组l到r之前的任意位点pivot(分区点).将所有小于privot的放置于右边,其他的放置于左边;
 * 递归分治,直至区间缩小为1,本身有序;
 * <p>
 * 算法逻辑:
 * 1.从数列中挑出一个元素，称为 “基准”（pivot）;
 * .
 * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，
 * >所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * >在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * .
 * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int pivot = partition(arr, l, r);
            sort(arr, l, pivot - 1);
            sort(arr, pivot + 1, r);
        }
    }

    static int partition(int[] arr, int l, int r) {
        //左右交换; 尽量对端判断
        int pivot = arr[r];
        int target = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, target);
                target++;
            }
        }
        swap(arr, target, r);
        return target;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
