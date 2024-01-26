package com.welph.leecode.part_1_500.part_341_360;

/**
 * 给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36。
 * 说明: 你可以假设n不小于 2 且不大于 58。
 */
public class Solution343 {

    public static void main(String[] args) {
        System.out.println(integerBreak(7));
        System.out.println(integerBreak(8));
        System.out.println(integerBreak(9));
        System.out.println(integerBreak(10));
    }

    /**
     * 无语.... 通过观察7~13的变化规律. 因为始终趋近于3的拆分--贪心处理
     * 若是使用动态规划, 则是保存X的最大乘积值
     */
    public static int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        n -= 2;
        return (int) (Math.pow(3, n / 3)) * ((n % 3) + 2);
    }

    /* 官方题解 */
    // 动态规划
    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                // (i-j) 不拆分 和 拆分 之间的最大值
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    /*
     * 动态规划的优化
     * @see https://leetcode.cn/problems/integer-break/solutions/352875/zheng-shu-chai-fen-by-leetcode-solution/
     * 题解精妙
     */
    public int integerBreak3(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }

}
