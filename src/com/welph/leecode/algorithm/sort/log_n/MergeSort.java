package com.welph.leecode.algorithm.sort.log_n;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 归并排序 : 分治思想
 * 需要额外的内存空间
 * 利用分治,使得比较两段数组的均是已经有序的. 只需要按照next节点比较大小合并即可;
 * <p>
 * 算法逻辑:
 * 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 * .
 * 2.设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 * .
 * 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 * .
 * 4.重复步骤 3 直到某一指针达到序列尾；
 * .
 * 5.将另一序列剩下的所有元素直接复制到合并序列尾。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    static void sort(int[] arr, int l, int r) {
        if (r - l < 2) {
            return;
        }
        int middle = (l + r) / 2;
        sort(arr, l, middle);
        sort(arr, middle, r);
        merge(arr, l, middle, r);
    }

    static void merge(int[] arr, int l, int middle, int r) {
        if (r - l < 2) {
            return;
        }
        int originLeft = l;
        //额外的空间内存
        int[] result = new int[r - l];
        int i = 0;
        int mr = middle;
        while (l < middle && mr < r) {
            if (arr[l] <= arr[mr]) {
                result[i] = arr[l++];
            } else {
                result[i] = arr[mr++];
            }
            i++;
        }
        for (; l < middle; l++, i++) {
            result[i] = arr[l];
        }
        for (; mr < r; mr++, i++) {
            result[i] = arr[mr];
        }
        //覆盖数据到arr
        System.arraycopy(result, 0, arr, originLeft, result.length);
    }
}
