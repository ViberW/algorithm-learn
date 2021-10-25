package com.welph.leecode.part_1_500.part_301_320;

/**
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Solution309 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));
    }

    /**
     * dp[][]
     * // dp[i][0]: 手上持有股票的最大收益
     * // dp[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
     * // dp[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0]; //第0天卖出的最大收益
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    /**
     * dp[][]
     * // dp[i][0]: 最后一个操作是卖出
     * // dp[i][1]: 最后一个操作是买入
     * // dp[i][2]: 最后一个操作是冷冻期
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
        }
        return dp[n - 1][0];
    }
}
