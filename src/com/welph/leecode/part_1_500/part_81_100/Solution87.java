package com.welph.leecode.part_1_500.part_81_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * <p>
 * 下图是字符串s1="great"的一种可能的表示形式。
 * <p>
 * great
 * / \
 * gr eat
 * / \ / \
 * g r e at
 *       / \
 *       a t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * <p>
 * 例如，如果我们挑选非叶节点"gr"，交换它的两个子节点，将会产生扰乱字符串"rgeat"。
 * <p>
 * rgeat
 * / \
 * rg eat
 * / \ / \
 * r g e at
 *       / \
 *       a t
 * 我们将"rgeat”称作"great"的一个扰乱字符串。
 * <p>
 * 同样地，如果我们继续交换节点"eat"和"at"的子节点，将会产生另一个新的扰乱字符串"rgtae"。
 * <p>
 * rgtae
 * / \
 * rg tae
 * / \ / \
 * r g ta e
 *     / \
 *     t a
 * 我们将"rgtae”称作"great"的一个扰乱字符串。
 * <p>
 * 给出两个长度相等的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例2:
 * <p>
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 */
public class Solution87 {

    public static void main(String[] args) {
        System.out.println(isScramble2("abb", "bba"));// true
        System.out.println(isScramble2("abab", "aabb"));// true
        System.out.println(isScramble2("ccabcbabcbabbbbcbb", "bbbbabccccbbbabcba"));// false
    }

    /**
     * 递归，检测时间超时,有许多重复计算结果,进而演变成下面动态规划
     */
    public static boolean isScramble(String s1, String s2) {
        return checkScramble(s1, 0, s1.length(), s2, 0, s2.length());
    }

    private static boolean checkScramble(String s1, int l1, int r1,
            String s2, int l2, int r2) {
        int len = r1 - l1;
        if (len != r2 - l2) {
            return false;
        }
        if (len <= 1) {
            return s1.charAt(l1) == s2.charAt(l2);
        }
        for (int i = 1; i < len; i++) {
            if (scramble(s1, l1, r1, l1 + i, s2, l2, r2, l2 + i, true)) {// 正序匹配
                return true;
            }
            if (scramble(s1, l1, r1, l1 + i, s2, l2, r2, l2 + len - i, false)) {// 反序匹配
                return true;
            }
        }
        return false;
    }

    private static boolean scramble(String s1, int l1, int r1, int mid1,
            String s2, int l2, int r2, int mid2, boolean normal) {
        return normal
                ? checkScramble(s1, l1, mid1, s2, l2, mid2) && checkScramble(s1, mid1, r1, s2, mid2, r2)
                : (checkScramble(s1, l1, mid1, s2, mid2, r2) && checkScramble(s1, mid1, r1, s2, l2, mid2));
    }

    /**
     * 动态规划， 保存范围之间是否相等。
     * dp[len][j][j] ||= (dp[k][i][j]&& dp[len - k][i + k][j + k]) || (dp[k][i][j + len - k] && dp[len - k][i + k][j])
     * 其中k>=1 且 k < len
     * --------------
     * dp[len][j][j] 代表从i开始向右的len长度 和 从j开始向右的len长度 是否匹配
     */
    public static boolean isScramble2(String s1, String s2) {
        int length = s1.length();
        if (length != s2.length()) {
            return false;
        }
        boolean[][][] dp = new boolean[length + 1][length][length];
        int l;
        int subLen;
        int i;
        int j;
        for (l = 1; l <= length; l++) {
            subLen = length - l;
            for (i = 0; i <= subLen; i++) {
                for (j = 0; j <= subLen; j++) {
                    if (l == 1) {
                        dp[l][i][j] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        for (int k = 1; k < l; k++) {
                            dp[l][i][j] = dp[k][i][j] && dp[l - k][i + k][j + k]
                                    || dp[k][i][j + l - k] && dp[l - k][i + k][j];
                            if (dp[l][i][j]) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[length][0][0];
    }

    /*官方题解 */
    // 记忆化搜索存储状态的数组
    // -1 表示 false，1 表示 true，0 表示未计算
    int[][][] memo;
    String s1, s2;

    public boolean isScramble3(String s1, String s2) {
        int length = s1.length();
        this.memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    // 第一个字符串从 i1 开始，第二个字符串从 i2 开始，子串的长度为 length，是否和谐
    public boolean dfs(int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }

        // 判断两个子串是否相等
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = 1;
            return true;
        }

        // 判断是否存在字符 c 在两个子串中出现的次数不同
        if (!checkIfSimilar(i1, i2, length)) {//相较于我的方法 这块处理的比较好
            memo[i1][i2][length] = -1;
            return false;
        }
        
        // 枚举分割位置
        for (int i = 1; i < length; ++i) {
            // 不交换的情况
            if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            // 交换的情况
            if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }

        memo[i1][i2][length] = -1;
        return false;
    }

    public boolean checkIfSimilar(int i1, int i2, int length) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = i1; i < i1 + length; ++i) {
            char c = s1.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int i = i2; i < i2 + length; ++i) {
            char c = s2.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            int value = entry.getValue();
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
