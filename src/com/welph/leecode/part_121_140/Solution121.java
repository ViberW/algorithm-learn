package com.welph.leecode.part_121_140;

/**
 * .给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * .如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * .注意：你不能在买入股票前卖出股票。
 * <p>
 * .示例 1:
 * <p>
 * .输入: [7,1,5,3,6,4]
 * .输出: 5
 * .解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * .     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * .示例 2:
 * <p>
 * .输入: [7,6,4,3,1]
 * .输出: 0
 * .解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Solution121 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        int[] prices2 = {7,6,4,3,1};
        System.out.println(maxProfit(prices2));
    }

    /**
     * 寻找相差最大的两个值，至少保证前后区分开来
     */
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
            if (v > maxV) {
                maxV = v;
                profit = Math.max(profit, maxV - minV);
            }
            if (v < minV) {
                maxV = minV = v;
            }
        }
        return profit;
    }

}
