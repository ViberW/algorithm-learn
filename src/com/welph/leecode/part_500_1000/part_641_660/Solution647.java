package com.welph.leecode.part_500_1000.part_641_660;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class Solution647 {

    public static void main(String[] args) {
        System.out.println(countSubstrings2("abc"));
        System.out.println(countSubstrings2("aaa"));
    }

    public static int countSubstrings(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        int ret = 0;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ret++;
            for (int j = 0; j < i; j++) {
                if (j + 1 == i) {
                    dp[j][i] = chars[j] == chars[i];
                } else {
                    dp[j][i] = dp[j + 1][i - 1] && chars[j] == chars[i];
                }
                if (dp[j][i]) {
                    ret++;
                }
            }
        }
        return ret;
    }

    //优化空间 87%时间  85%空间
    public static int countSubstrings2(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        int ret = 0;
        char[] chars = s.toCharArray();
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            dp[i] = true;
            ret++;
            for (int j = 0; j < i; j++) {
                if (j + 1 == i) {
                    dp[j] = chars[j] == chars[i];
                } else {
                    dp[j] = dp[j + 1] && chars[j] == chars[i];
                }
                if (dp[j]) {
                    ret++;
                }
            }
        }
        return ret;
    }
}
