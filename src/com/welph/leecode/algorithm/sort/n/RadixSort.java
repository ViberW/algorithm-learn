package com.welph.leecode.algorithm.sort.n;

import java.util.Arrays;

/**
 * 基数排序: 非比较型整数排序算法
 * >  对需要比较的数据,按照位数切割, 并比较每一位的数据.按照
 * <p>
 * 不同于桶排序和计数排序: 基数排序可以为每个位数(基数)的分别排序(桶/计数)
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1, 11, 34, 52, 32};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        int max = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int digit = digit(max);
        sort(arr, digit);
    }

    public static void sort(int[] arr, int digit) {
        int mod = 10;
        int dev = 1;
        int[][] counter;
        for (int i = 0; i < digit; i++, dev *= 10, mod *= 10) {
            counter = new int[mod][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = (arr[j] % mod) / dev;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }
            //由于每次都是稳定排序,直接将对应的数值放入arr中即可;
            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }
    }

    static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    static int digit(int value) {
        if (value == 0) {
            return 1;
        }
        int len = 0;
        for (; value != 0; value /= 10) {
            len++;
        }
        return len;
    }
}
