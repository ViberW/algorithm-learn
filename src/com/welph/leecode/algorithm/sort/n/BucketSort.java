package com.welph.leecode.algorithm.sort.n;

import com.welph.leecode.algorithm.sort.nlog_n.QuickSort;

import java.util.Arrays;

/**
 * 桶排序: 通过定义好的多个顺序桶,分别将数列中的数据按照桶的数值范围`均匀`放置,
 * 随后每个桶都进行`快排`操作
 * 时间复杂度: m个桶, m * ((n/m)*log(n/m)) = m*log(n/m) = O(m)
 * .                     快排的时间复杂度
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 4);
    }

    public static void sort(int[] arr, int bucketSize) {
        int min, max;
        min = max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;

        int[][] buckets = new int[bucketCount][0];

        for (int i = 0; i < arr.length; i++) {
            int k = (arr[i] - min) / bucketSize;
            buckets[k] = arrAppend(buckets[k], arr[i]);
        }

        int arrIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].length == 0) {
                continue;
            }
            int[] bucket = buckets[i];
            QuickSort.sort(bucket);
            //将数据回到arr
            for (int j = 0; j < bucket.length; j++) {
                arr[arrIndex++] = bucket[j];
            }
        }
    }

    private static int[] arrAppend(int[] arr, int value) {
        //可以设置为达到某个阈值,进行倍数扩容, 或者使用List
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
