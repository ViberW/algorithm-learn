package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 给定一个数组，将其划分成d个非空子数组，使其最大值之和最小。
 *
 * 如果无法划分，返回-1，否则返回最大和的最小值。
 */
public class SubArrayMaxValueSum {

    public static int solution(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            int max = jobDifficulty[i - 1];
            for (int j = i; j > 0; j--) {
                max = Math.max(max, jobDifficulty[j - 1]);
                int low = j == 1 ? 0 : 1;
                for (int k = Math.min(d, j); k > low; k--) {
                    dp[i][k] = Math.min(dp[i][k], dp[j - 1][k - 1] + max);
                }
            }
        }
        return dp[n][d];
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{6, 5, 4, 3, 2, 1}, 2) == 7);
        System.out.println(solution(new int[]{6, 9, 9}, 4) == -1);
        System.out.println(solution(new int[]{1, 4, 1}, 3) == 6);
    }
}
