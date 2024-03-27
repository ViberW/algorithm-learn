package com.welph.leecode.part_1_500.part_341_360;

/**
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n。
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class Solution357 {

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }

    // f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [第一个因数是 9，因为数字不能以 0 开头]。

    /**
     * 这个题就是高中时学的排列组合
     * f(0)=1
     * f(1)=10
     * f(2)=9*9+f(1)
     * f(3)=9*9*8+f(2)
     * f(4)=9*9*8*7+f(3)
     * 左边开始数
     * 首位数不取 0 其他位数可以取 0，下一位比前一位取法少一种，因为不能重复
     * 首位数取 0 时就是 f(n-1)的数量 --todo 看了题解
     */
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            // dp[i - 1] - dp[i - 2] 其实就是 9*9*8... 后面乘以(10 - (i - 1))
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }

    // 对上面空间的简化
    public static int countNumbersWithUniqueDigits2(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 10;
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }
}
