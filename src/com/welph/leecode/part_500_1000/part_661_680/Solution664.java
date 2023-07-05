package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 * <p>
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 * 示例 1：
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * <p>
 * 示例 2：
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 */
public class Solution664 {

    public static void main(String[] args) {
        System.out.println(strangePrinter("aaabbb"));//2
        System.out.println(strangePrinter("aba"));//2
        System.out.println(strangePrinter("tbgtgb"));//4
        System.out.println(strangePrinter("abcabc"));//5
    }

    /**
     * {@link com.welph.leecode.part_500_1000.part_541_560.Solution546}
     * 动态规划
     * dp[i][j]
     * 1. dp[i][j-1] +1
     * 2. dp[i][m] +dp[m+1][j-1]  这里的 s[m] = s[j] 代表从m到j一路刷到底
     * -------------------
     * good 通过546题做出的解法
     */
    public static int strangePrinter(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        char[] chars = s.toCharArray();
        return strangePrinter(chars, dp, 0, length - 1);
    }

    private static int strangePrinter(char[] chars, int[][] dp, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r] > 0) {
            return dp[l][r];
        }
        int or = r; //必须代替一下 否则会重复计算很多次
        while (l < or && chars[or] == chars[or - 1]) {
            or--;
        }
        dp[l][r] = strangePrinter(chars, dp, l, or - 1) + 1;
        for (int i = l; i < or; i++) {
            if (chars[i] == chars[r]) {
                dp[l][r] = Math.min(dp[l][r],
                        strangePrinter(chars, dp, l, i) + strangePrinter(chars, dp, i + 1, or - 1));
            }
        }
        return dp[l][r];
    }

    /**
     * 官方题解
     * dp[i][j] 为i到j的最小打印
     * <p>
     * 1. s[i]=s[j] 则dp[i][j] = dp[i][j-1]  头尾相同, 则一遍刷过去
     * 2. s[i]!=s[j] 则dp[i][j] =min(dp[i][k] + dp[k+1][j])
     */
    public static int strangePrinter2(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][length - 1];
    }
}
