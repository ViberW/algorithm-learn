package com.welph.leecode.algorithm.sort.nlog_n;

import java.util.Arrays;

/**
 * 堆排序: 利用堆结构
 * 近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点
 * <p>
 * 算法步骤:
 * 1.将待排序序列构建成一个堆 H[0……n-1]，根据（升序降序需求）选择大顶堆或小顶堆；
 * .
 * 2.把堆首（最大值）和堆尾互换；
 * .
 * 3.把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
 * .
 * 4.重复步骤 2，直到堆的尺寸为 1。
 * <p>
 * 注意: 堆并不一意味着排好序了.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 2, 1, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        //找到最大的值 放于index=0的位置
        buildMaxHeap(arr, len);
        //但本身还没有排好序,所以此案有这一步操作;
        //将第一元素放到最后,之后只需要处理len-1范围内的数据,重复如此
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            //每个元素从上往下堆化;
            heapify(arr, 0, len);
        }
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    //构建堆
    private static void heapify(int[] arr, int i, int r) {
        int left = 2 * i + 1;   //因为是完全二叉树,所有,左右子节点,分别为2*i+1, 2*i+2.若是使用1作为起始索引,则2*i和2*i+1ß
        int right = 2 * i + 2;
        int largest = i;

        //大顶堆模式
        if (left < r && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < r && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, r);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
