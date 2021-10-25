package com.welph.leecode.part_1_500.part_1_20;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * TODO 之后需要抽时间去重新复习
 *
 * @author: Admin
 * @date: 2019/5/9
 * @Description: {相关描述}
 */
public class Solution10 {

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*.*";
        System.out.println(isMatch(s, p));
    }

    /**
     * 动态方程:
     *
     * 1.p[j] == s[i]:   dp[i][j] = dp[i-1][j-1]
     * 2.p[j] == ".":   dp[i][j] = dp[i-1][j-1]
     * 3.p[j] =="*":
     *  1 p[j-1] != s[i]:   dp[i][j] = dp[i][j-2]
     *  2 p[i-1] == s[i] or p[i-1] == ".":
     *     dp[i][j] = dp[i-1][j] // 多个a的情况
     *     or dp[i][j] = dp[i][j-1] // 单个a的情况
     *     or dp[i][j] = dp[i][j-2] // 没有a的情况
     */
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        int sL = s.length(), pL = p.length();
        boolean[][] dp = new boolean[sL + 1][pL + 1];
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        dp[0][0] = true;
        for (int i = 2; i <= pL; ++i) {
            if (pc[i - 1] == '*' && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= sL; ++i) {
            for (int j = 1; j <= pL; ++j) {
                if (pc[j - 1] == '.' || pc[j - 1] == sc[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (pc[j - 1] == '*') {
                    if (pc[j - 2] == sc[i - 1] || pc[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[sL][pL];
    }

    //递归法.反
    public boolean isMatch01(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2))
                    || (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                    && isMatch(s.substring(1), p));
        }
        return !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                && isMatch(s.substring(1), p.substring(1));
    }
}
