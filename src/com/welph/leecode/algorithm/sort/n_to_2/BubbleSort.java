package com.welph.leecode.algorithm.sort.n_to_2;

import java.util.Arrays;

/**
 * 冒泡排序:
 * 重复走访数列,每次比较相邻的两个数据,若不满足大小要求,则互换
 * <p>
 * 算法步骤:
 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * .
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * .
 * 3.针对所有的元素重复以上的步骤，除了最后一个。
 * .
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        boolean flag;
        //当次循环需要遍历的长度
        for (int i = 0; i < len - 1; i++) {
            flag = false;
            //当次全数列扫描
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                //说明之前数据已经排序完毕
                break;
            }
        }
    }
}
