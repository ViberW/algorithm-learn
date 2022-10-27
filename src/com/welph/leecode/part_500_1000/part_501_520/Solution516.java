package com.welph.leecode.part_500_1000.part_501_520;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class Solution516 {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_1_20.Solution05}
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution125}
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution131}
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution214}
     * {@link com.welph.leecode.part_1_500.part_401_420.Solution409}
     * ----------------------------
     * 动态规划 dp[i][j]
     * 1. s[i]=s[j] 则dp[i][j] = max(dp[i][j-1],dp[i+1][j])
     * 2. 若是包含j 则说明,
     * dp[i][j] = dp[i+1][j-1] + 2
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    dp[j][i] = Math.max(dp[j][i], dp[j + 1][i - 1] + 2);
                }
                dp[j][i] = Math.max(dp[j][i], Math.max(dp[j + 1][i], dp[j][i - 1]));
            }
        }
        return dp[0][n - 1];
    }
}
