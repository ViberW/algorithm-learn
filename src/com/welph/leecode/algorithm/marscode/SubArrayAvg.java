package com.welph.leecode.algorithm.marscode;

/**
 * 小U有一个长度为n的整数数组，并且选择了一个有理数 u/v。
 * 他现在想知道这个数组中有多少个连续的子区间，其平均值恰好等于 u/v。数组的子区间是指数组中一段连续的元素。
 *
 * 例如，给定数组 [2, 4, 1, 3, 2, 3] 和有理数 5/2，我们需要找出所有平均值等于 5/2 的子区间。
 */
public class SubArrayAvg {

    public static int solution(int n, int u, int v, int[] arr) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, 5, 2, new int[]{2, 4, 1, 3, 2, 3}) == 6);
        System.out.println(solution(5, 1, 1, new int[]{1, 1, 1, 1, 1}) == 15);
        System.out.println(solution(4, 2, 1, new int[]{2, 2, 2, 2}) == 10);
    }
}
