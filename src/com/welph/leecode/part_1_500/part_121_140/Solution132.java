package com.welph.leecode.part_1_500.part_121_140;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 * 示例:
 * 输入:"aab"
 * 输出: 1
 * 解释: 进行一次分割就可将s 分割成 ["aa","b"] 这样两个回文子串。
 */
public class Solution132 {

    public static void main(String[] args) {
        System.out.println(minCut("aab"));
        System.out.println(minCut("aabac"));
    }

    /**
     * 动态规划:
     * .  1. 包含i字符的分割处理  dp[0][i] = dp[0][x] + 1  即找出所有能够包含i的回文串,
     * .     1.1 找出 dp[x+2][i-1] 是回文串的数据信息
     * .  2. 不包含i字符的分割处理 dp[0][i] = dp[0][i-1]+1
     *
     * -- 时间倒是满足81% 但是内存消耗过大了
     */
    public static int minCut(String s) {
        int length = s.length();
        int[][] dp = new int[length][length + 1];
        int min;
        for (int i = 0; i < length; i++) {
            min = dp[0][i] + 1;
            dp[i][i + 1] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if ((j == i - 1 || dp[j + 1][i] > 0) && s.charAt(j) == s.charAt(i)) {
                    dp[j][i + 1] = 1;
                    min = Math.min(min, dp[0][j] + 1);
                }
            }
            dp[0][i + 1] = min;
        }
        return dp[0][length] - 1;
    }
}
