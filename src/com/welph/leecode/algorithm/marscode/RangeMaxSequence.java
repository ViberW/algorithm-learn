package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小R正在处理一个数组序列，他的任务是找出一个区间，使得这个区间的所有数经过以下计算得到的值是最大的：
 *
 * >>> 区间中的最小数 * 区间所有数的和
 *
 * 小R想知道，经过计算后，哪个区间能产生最大的值。你的任务是帮助小R编写一个程序，输出最大计算值。
 *
 * 例如：给定数组序列 [6, 2, 1]，可以得到以下区间及其计算值：
 *
 * [6] = 6 * 6 = 36
 * [2] = 2 * 2 = 4
 * [1] = 1 * 1 = 1
 * [6, 2] = 2 * 8 = 16
 * [2, 1] = 1 * 3 = 3
 * [6, 2, 1] = 1 * 9 = 9
 * 根据这些计算，小R可以选定区间 [6]，因此输出的最大值为 36。
 */
public class RangeMaxSequence {

    public static int solution(int n, int[] a) {
        //DP
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int value = a[i - 1];
            dp[1][i] = dp[1][i - 1] + value;
            for (int j = i, k = 0; j > 0; j--, k++) {
                dp[0][j] = Math.min(dp[0][j - 1], value);
                result = Math.max(result, dp[0][j] * (dp[1][i] - dp[1][k]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{6, 2, 1}) == 36);
        System.out.println(solution(4, new int[]{5, 1, 4, 3}) == 25);
        System.out.println(solution(5, new int[]{7, 3, 2, 1, 8}) == 64);
    }
}
