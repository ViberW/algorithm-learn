package com.welph.leecode.part_500_1000.part_641_660;

import java.util.Arrays;

/**
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1：
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * 1 <= n <= 1000
 */
public class Solution650 {

    public static void main(String[] args) {
//        System.out.println(minSteps(3));
//        System.out.println(minSteps(1));
//        System.out.println(minSteps(6));
        System.out.println(minSteps(20));
        System.out.println(minSteps2(20));
    }

    /**
     * 动态规划
     * dp[i][j] 这里 i+j = x  因为最后一步肯定是paste或者没有
     * 可以肯定的是 j <= i/2
     * -------------------------------
     * dp[i][j] = dp[i-j][j] + 1 || dp[i/2] + 2 (j = i/2)
     */
    public static int minSteps(int n) {
        int[] dp = new int[n / 2 + 1];
        int[] min = new int[n + 1];
        min[1] = 0;
        int mid;
        int minVal;
        for (int i = 2; i <= n; i++) {
            minVal = Integer.MAX_VALUE;
            mid = (i - 1) / 2;
            //每次根据+1 +2 +3 => 1*x 2*x 3*x
            for (int j = 1; j <= mid; j++) {
                if (i % j == 0) {
                    dp[j]++;
                    minVal = Math.min(minVal, dp[j]);
                }
            }
            if ((i & 1) == 0) {
                dp[i / 2] = min[i / 2] + 2;
                minVal = Math.min(minVal, dp[i / 2]);
            }
            min[i] = minVal;
        }
        return min[n];
    }

    //尝试优化空间  todo  看看怎么优化时间吧
    public static int minSteps2(int n) {
        int[] min = new int[n + 1];
        min[1] = 0;
        int mid;
        int minVal;
        for (int i = 2; i <= n; i++) {
            minVal = Integer.MAX_VALUE;
            mid = i / 2;
            //每次根据+1 +2 +3 => 1*x 2*x 3*x
            for (int j = 1; j <= mid; j++) {
                if (i % j == 0) {
                    minVal = Math.min(minVal, min[j] + (i / j));
                }
            }
            min[i] = minVal;
        }
        return min[n];
    }
}
