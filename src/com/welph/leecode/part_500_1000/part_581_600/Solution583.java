package com.welph.leecode.part_500_1000.part_581_600;

/**
 * 定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * <p>
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 * <p>
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class Solution583 {

    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
        System.out.println(minDistance("leetcode", "etco"));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_61_80.Solution72}
     * 动态规划
     * dp[i][j] 若是i==j => min(dp[i - 1][j] + 1, dp[i - 1][j - 1], dp[i][j-1] + 1)
     * 若是i!=j => min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 2, dp[i][j-1] + 1)
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (a[i] == b[0]) {
                dp[i][0] = i;
            } else if (i != 0) {
                dp[i][0] = dp[i - 1][0] + 1;//是否存在target
            } else {
                dp[0][0] = 2;
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[0] == b[i]) {
                dp[0][i] = i;
            } else if (i != 0) {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i - 1][j - 1], dp[i][j - 1] + 1));
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i - 1][j - 1] + 2, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
