package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R拿到了一个长度为n的数组，其中每个元素都是一个正整数。小R发现每次可以删除某个数组中某个数的一位数字，
 * 这样可以逐步将所有数字变为0。他想知道，要将数组中所有数字都变为0，最少需要多少步？
 * <p>
 * 例如：对于数字 103，小R可以选择删除第1位数字，将其变为 3；或者删除第2位数字，变为 13，又或者删除第3位数字，将其变为 10。
 * 最终目标是将所有数字都删除为0。
 */
public class MinStepToZero {
    public static int solution(int n, int[] a) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            int num = a[i];
            while (num > 0) {
                if ((num % 10) > 0) {
                    total++;
                }
                num /= 10;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{10, 13, 22, 100, 30}) == 7);
        System.out.println(solution(3, new int[]{5, 50, 505}) == 4);
        System.out.println(solution(4, new int[]{1000, 1, 10, 100}) == 4);
    }
}
