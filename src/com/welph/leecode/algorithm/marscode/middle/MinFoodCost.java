package com.welph.leecode.algorithm.marscode.middle;

import java.util.Arrays;

/**
 * 小明想从A徒步到B，总路程需要N天，路程中为了确保安全，小明每天需要消耗1份食物。
 * 从起点开始，小明每天都能遇到一个补给站，可以补充食物，不同补给站的食物价格可能不同。
 * 小明最多能同时携带K份食物，请问小明若要安全完成徒步，最少要花多少钱？
 * -----------
 * 第一行为两个正整数N、K，代表总路程N天，最大负重K。
 * 第二行为N个正整数，分别代表从第0天到第N-1天，每一个补给站的食物价格。
 */
public class MinFoodCost {

    public static int solution(int n, int k, int[] data) {
        int[] dp = new int[k + 1]; //在i处携带了j个食物,最小花费
        for (int i = 0; i <= k; i++) {
            dp[i] = i * data[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j < k) {
                    //不买
                    dp[j] = dp[j + 1];
                }
                if (j > 0) {
                    //买
                    dp[j] = j == k ? (dp[j - 1] + data[i]) : Math.min(dp[j - 1] + data[i], dp[j]);
                }
            }
        }
        return dp[1]; //因为n不是最后一个, 还需要走一天
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(5, 2, new int[]{1, 2, 3, 3, 2}) == 9);
        System.out.println(solution(6, 3, new int[]{4, 1, 5, 2, 1, 3}) == 9);
    }
}
