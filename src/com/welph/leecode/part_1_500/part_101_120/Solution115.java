package com.welph.leecode.part_1_500.part_101_120;

/**
 * . 给定一个字符串S和一个字符串T，计算在 S 的子序列中 T 出现的个数。
 * .
 * . 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * .
 * . 题目数据保证答案符合 32 位带符号整数范围。
 * .
 * . 示例1：
 * . 输入：S = "rabbbit", T = "rabbit"
 * . 输出：3
 * . 解释：
 * . 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * . (上箭头符号 ^ 表示选取的字母)
 * .
 * . rabbbit
 * . ^^^^ ^^
 * . rabbbit
 * . ^^ ^^^^
 * . rabbbit
 * . ^^^ ^^^
 * <p>
 * . 示例2：
 * . 输入：S = "babgbag", T = "bag"
 * . 输出：5
 * . 解释：
 * .
 * . 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * . (上箭头符号 ^ 表示选取的字母)
 * .
 * . babgbag
 * . ^^ ^
 * . babgbag
 * . ^^    ^
 * . babgbag
 * . ^    ^^
 * . babgbag
 * .   ^  ^^
 * . babgbag
 * .     ^^^
 */
public class Solution115 {

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
    }


    /**
     * 使用二维数组bp[i][j] i为t的长度 j为s的长度, 信息
     * bp[i][j]  :
     * //每一次的匹配,都等于上一次可能的匹配次数, 加上(若当前s[i-1]=t[j-1],则额外的dp[i-1][j-1])
     * 当s[i-1] = t[j-1]  时 bp[i][j] = bp[i-1][j-1] + dp[i][j-1]
     * 当s[i] !=t[j]  时 bp[i][j] = dp[i][j-1]
     * <p>
     * explain: {@author 在处理的时候, 这类需要动态规划, 首先想到多条多维, 再逐次看看能够缩减}
     */
    public static int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[n + 1][m + 1]; //t放在一维.为了充分利用缓存行

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;
        }

        char cur;
        for (int i = 1; i <= n; i++) {
            cur = t.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                if (cur == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}
