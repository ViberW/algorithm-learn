package com.welph.leecode.part_361_380;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * <p>
 * 示例:
 * n = 10, 我选择了8.
 * <p>
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * <p>
 * 游戏结束。8 就是我选的数字。
 * <p>
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class Solution375 {

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    /**
     * 若是r-l<3 说明当前次一定是能够找到答案的. 1,2,3 只需要猜2就行
     * 注意: 这里没有guess的方法 目标值可能在任意位置上
     * ---------------
     * 思考: 1~n的位置上, 我猜 m 则 下一次可能是1~m或m~n范围的猜数字  找到每种情况下最大值的最小值
     * dp[i][j] 表示从i~j范围内的至少要求金额
     */
    public static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                if (j - i == 1) {
                    dp[i][j] = i;
                } else if (j - i == 2) {
                    dp[i][j] = i + (j - i) / 2;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        //找到i~j范围内的 所有路程的最大值的 最小值
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][k - 1], dp[k + 1][j]) + k);
                    }
                }
            }
        }
        return dp[1][n];
    }

}
