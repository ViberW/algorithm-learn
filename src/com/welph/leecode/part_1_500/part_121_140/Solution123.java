package com.welph.leecode.part_1_500.part_121_140;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意:你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * . 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * . 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * . 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Solution123 {

    public static void main(String[] args) {
        // todo 之后重新完成,
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        System.out.println(maxProfit(prices));
    }

    /*
     * 官方题解:
     * 任意一天结束后的状态: 无操作, 买过一次, 买卖一次, 第二次买, 第二次买卖
     * 由于无操作没有收益, 不考虑
     * ----------------------------------------
     * 买过一次, 买卖一次, 第二次买, 第二次买卖
     * buy1, sell1, buy2, sell2
     * ----------------------------------------
     * 相对于第i天逻辑:
     * buy1 = max(buy1(i-1天), -price[i]); 前一天买当天无操作 或 当天买
     * sell1 = max(sell1(i-1天), buy1 + price[i]) 前一天卖当天误操作 或 当天卖
     * buy2 = max(buy2(i-1天), -price[i]); 前一天买当天无操作 或 当天买
     * sell2 = max(sell2(i-1天), buy2 + price[i]) 前一天卖当天误操作 或 当天卖
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        // 这里要关注的是buy2也是-price,这主要是考虑当天买当天卖,再买
        // 因为当天买卖,收益0对数据无影响
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
