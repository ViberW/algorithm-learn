package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小R有一个长度为n的数组，他想知道这个数组中有多少个子序列是一个排列。
 * 子序列是指可以从原数组中删除若干个元素（也可以不删除）后得到的新数组，而排列的定义是长度为m的数组，其中1到m的每个元素都恰好出现1次。
 * 也就是说，子序列中的元素必须是从1到m的一个完整排列，并且必须按升序排列。
 *
 * 现在，小R想知道有多少个这样的子序列。
 */
public class SubFullSortNumber {

    //长度m的子序列,必须包含1到m且升序G
    public static int solution(int n, int[] a) {
        //这道题有问题, 它不考虑原本数值的位置!!!
        Arrays.sort(a);
        int result = 0;
        int multiplier = 1;
        int currentCount = 0;
        int preValue = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] != preValue) {
                if (a[i] > preValue + 1) {
                    break;
                }
                multiplier *= currentCount;
                result += multiplier;
                currentCount = 1;
                preValue = a[i];
            } else {
                currentCount++;
            }
        }
        result += currentCount * multiplier;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{1, 1, 5, 2, 3, 4}) == 10);
        System.out.println(solution(5, new int[]{1, 2, 3, 4, 5}) == 5);
        System.out.println(solution(7, new int[]{1, 3, 5, 2, 4, 6, 7}) == 7);
        System.out.println(solution(9, new int[]{5, 3, 2, 1, 3, 5, 6, 8, 8}));
        System.out.println(solution(3, new int[]{5, 17, 9}));
        System.out.println(solution(13, new int[]{3, 1, 5, 2, 9, 12, 4, 1, 13, 15, 12, 3, 8}));
    }

}
