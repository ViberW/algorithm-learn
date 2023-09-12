package com.welph.leecode.part_1_500.part_121_140;

/**
 * .给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * .
 * .设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * .
 * .注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * .
 * .示例 1:
 * .
 * .输入: [7,1,5,3,6,4]
 * .输出: 7
 * .解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * . 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * .示例 2:
 * .
 * .输入: [1,2,3,4,5]
 * .输出: 4
 * .解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * . 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * . 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * .示例3:
 * .
 * .输入: [7,6,4,3,1]
 * .输出: 0
 * .解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * .
 * .提示：
 * .
 * .1 <= prices.length <= 3 * 10 ^ 4
 * .0 <= prices[i]<= 10 ^ 4
 */
public class Solution122 {

    public static void main(String[] args) {
        int[] prices = { 7, 6, 4, 3, 1 };
        System.out.println(maxProfit(prices));
    }

    // 一旦找到一个比当前的最大值小的就可以放到下一段的交易中了
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int profit = 0;
        int minV = prices[0];
        int maxV = minV;
        int v;
        for (int i = 1; i < len; i++) {
            v = prices[i];
            if (v < maxV) {// 一旦小于最大值, 则重新开始购买,贪心逻辑
                // 重新计算
                profit += maxV - minV;
                maxV = minV = v;
            } else {
                maxV = v;
            }
        }
        profit += maxV - minV;
        return profit;
    }

    /* 官方题解 */
    /*
     * 动态规划
     * dp[i][0] 表示第i天交易后手中没有股票的利润
     * = dp[i-1][0] 前一天未交易 + (dp[i-1][1] + p[i]) 前一天还持有一个股票,今天卖掉
     * 
     * dp[i][1] 表示第i天交易后手里持有一只股票的最大利润
     * =dp[i-1][1] 前一天持有 + (dp[i-1][0] - p[i]) 前一天未持有,今天买入
     * 
     * ---最终dp[n][0] 大于 dp[n][1] 因为最终不持有一定大于持有
     */
    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n][0];
    }

    // 对上面的动态进行空间压缩
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int ndp0 = Math.max(dp0, dp1 + prices[i]);
            int ndp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = ndp0;
            dp1 = ndp1;
        }
        return dp0;
    }

    /*
     * 贪心算法
     * -----------------------
     * 问题等价于多个不相交的区间最大价值和
     * [li,ri] 表示li天买入, ri天卖出
     * 而[li,ri]可以划分到中间多天的总和=>[ri, ri-1] + [ri-1, ri-2]+...[li+1, li]
     * ===> 简化为中间多个长度为1的区间的价值最大化
     * -----> 贪心角度, 每次选择价值大于0的区间,使得答案最大化
     */
    public static int maxProfit3(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
