package com.welph.leecode.part_1_500.part_381_400;

import java.util.Arrays;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T
 * 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 * <p>
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
public class Solution392 {

    public static void main(String[] args) {
        System.out.println(isSubsequence1("abc", "ahbgdc"));
        System.out.println(isSubsequence("", "ahbgdc"));
    }

    /**
     * 先来个正常的
     */
    public static boolean isSubsequence(String s, String t) {
        int index = 0;
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(index) == t.charAt(i)) {
                index++;
            }
        }
        return index == s.length();
    }

    /**
     * 若有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列
     * s = "abc", t = "ahbgdc"
     * 根据S. 找到第一个a的位置i1
     * 根据 i1往后找到b的位置
     */
    public static boolean isSubsequence1(String s, String t) {
        int length = t.length();
        int[][] dp = new int[length][26]; // [从哪个索引开始搜索][代表的英文字母]
        int c;
        // int[] word = new int[26];
        // Arrays.fill(word, -1);
        // 优化下
        Arrays.fill(dp[length - 1], -1);
        for (int i = length - 1, k = 0; i >= 0; i--, k++) {
            c = t.charAt(i) - 'a';
            // word[c] = i;
            if (k > 0) {
                System.arraycopy(dp[i + 1], 0, dp[i], 0, 26);
            }
            dp[i][c] = i;
        }
        /*
         * 通过遍历后, 就出现了每一个index下标中 到之后任意一个字母的跳转索引下标.
         */
        // 从前往后找
        int index = 0;
        int k;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i) - 'a';
            k = dp[index][c];
            if (k != -1) {
                index = k + 1;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     * 应对大量的s 对t做预处理
     */
    public boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

}
