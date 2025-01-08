package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R有一个长度为 n 的排列，排列中的数字是 1 到 n 的整数。她每次操作可以选择两个数 a_i 和 a_j 进行交换，
 * 前提是这两个数的下标 i 和 j 的奇偶性相同（即同为奇数或同为偶数）。小R希望通过最少的操作使数组变成升序排列。
 *
 * 请你帮小R计算，最少需要多少次操作才能使得数组有序。如果不能通过这样的操作使数组有序，则输出 -1。
 */
public class SortEvenOdd {
    public static int solution(int n, int[] a) {
        //奇数位排序 偶数位排序, 查看是否符合升序
        int cost = 0;
        for (int i = 0; i < n; i++) {
            //说明不是自己的
            while (a[i] != i + 1) {
                if ((a[i] & 1) != ((i + 1) & 1)) {
                    return -1;
                }
                int change = a[i];
                a[i] = a[change - 1];
                a[change - 1] = change;
                cost++;
            }
        }
        //判断是否能够连续存在升序
        return cost;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 4, 5, 2, 3}) == 2);
        System.out.println(solution(4, new int[]{4, 3, 2, 1}) == -1);
        System.out.println(solution(6, new int[]{2, 4, 6, 1, 3, 5}) == -1);
    }
}
