package com.welph.leecode.part_1_500.part_261_280;

/**
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * 示例1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 提示：
 * 1 <= n <= 104
 */
public class Solution279 {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

    // 动态规划
    // dp = dp[最近的]
    // todo 还有很多方法
    public static int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        // 最坏的为1+1+1+1...+1
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /* 官方题解 */
    // 四平方和定理: 任意一个正整数都可以被表示为至多四个正整数的平方和 {@link https://zhuanlan.zhihu.com/p/382062423}
    // 对于当前题目, 四平方和定理代表了上界
    public int numSquares2(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        // 根据四平方和定理: 4^k*(8m+7) = n 时, n只能被四个正整数表示
        if (checkAnswer4(n)) {
            return 4;
        }
        // 剩下的结果一定是1, 2, 3中的一个
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
}
