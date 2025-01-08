package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小C开了一家水果店，某天接到了一个大订单，要求将n个编号为1到n的水果打包成多个果篮，
 * 每个果篮的最大容量为m个水果，并且果篮中水果的编号必须连续。
 * 每个果篮的成本与容纳的水果数量及水果的体积相关，成本公式为：k×⌊(u+v)/2⌋+s
 *
 * 其中，u是果篮中水果的最大体积，v是果篮中水果的最小体积，k是果篮中水果的数量，s是一个常数，
 * ⌊x⌋ 表示对x进行下取整。
 *
 * 你的任务是帮助小C将这些水果打包成若干果篮，使得总成本最小。
 *
 * 例如：当有6个水果，体积为[1, 4, 5, 1, 4, 1]，一个果篮最多装4个水果，常数s为3时，
 * 最优的打包方案是将前三个水果（1, 4, 5）装成一个果篮，后三个水果（1, 4, 1）装成另一个果篮，最小的总成本为21。
 */
public class MinCostFruit {

    public static int solution(int n, int m, int s, int[] a) {
        //DP [i] 代表到i处最小成本
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int min, max;
        for (int i = 1; i <= n; i++) {
            min = max = a[i - 1]; //不超过m个
            for (int j = i - 1, k = Math.max(0, i - m); j >= k; j--) {
                //问题的关键在于 范围内的最大和最小值的快速获取
                min = Math.min(min, a[j]);
                max = Math.max(max, a[j]);
                dp[i] = Math.min(dp[i], dp[j] + (((min + max) / 2) * (i - j)) + s);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(6, 4, 3, new int[]{1, 4, 5, 1, 4, 1}) == 21);
        System.out.println(solution(5, 3, 2, new int[]{2, 3, 1, 4, 6}) == 17);
        System.out.println(solution(7, 4, 5, new int[]{3, 6, 2, 7, 1, 4, 5}) == 35);
    }
}
