package com.welph.leecode.part_1_500.part_41_60;

/**
 * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
 * 示例1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 结果基本上和 Solution10 一致
 */
public class Solution44 {

    public static void main(String[] args) {
        String s = "abcdefg";
        String p = "abcd*";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        // 由于*匹配任意字符串
        /**
         * sp[i][j] :
         * 1）如果字符或？： sp[i][j] = sp[i-1][j-1] & ('?' == p[j] || s[i] == p[j])
         * 2) p[j] == * : sp[i][j] = sp[i][j-1] || sp[i-1][j] (不使用* || 使用*)
         */
        if (p.length() == 0)
            return s.length() == 0;
        int sL = s.length(), pL = p.length();
        boolean[][] dp = new boolean[sL + 1][pL + 1];
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        dp[0][0] = true;
        for (int i = 1; i <= pL; ++i) {
            if (pc[i - 1] == '*') {
                dp[0][i] = true;
            } else {
                break;// 仅仅匹配到开始多个的*
            }
        }
        for (int i = 1; i <= sL; ++i) {
            for (int j = 1; j <= pL; ++j) {
                if (pc[j - 1] == '?' || pc[j - 1] == sc[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[sL][pL];
    }
    /**
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     *
     * 当前匹配 = 前一个匹配 & 当前的字符串匹配
     * 1）当前是字母或？ 则正常匹配
     * 2) 如果当前是 * ：
     * a)往后找开始匹配的数据：
     * 继续匹配
     * 不匹配， 继续返回的最近的*
     * b) 若不匹配，则从下一个，继续
     * 递归法
     */

}
