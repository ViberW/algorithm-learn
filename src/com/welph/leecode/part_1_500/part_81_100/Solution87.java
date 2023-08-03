package com.welph.leecode.part_1_500.part_81_100;

/**
 * 给定一个字符串s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * <p>
 * 下图是字符串s1="great"的一种可能的表示形式。
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * <p>
 * 例如，如果我们挑选非叶节点"gr"，交换它的两个子节点，将会产生扰乱字符串"rgeat"。
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * 我们将"rgeat”称作"great"的一个扰乱字符串。
 * <p>
 * 同样地，如果我们继续交换节点"eat"和"at"的子节点，将会产生另一个新的扰乱字符串"rgtae"。
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
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
        System.out.println(isScramble2("abb", "bba"));
        System.out.println(isScramble2("abab", "aabb"));
        System.out.println(isScramble2("ccabcbabcbabbbbcbb", "bbbbabccccbbbabcba"));
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
            if (scramble(s1, l1, r1, l1 + i, s2, l2, r2, l2 + i, true)) {
                return true;
            }
            if (scramble(s1, l1, r1, l1 + i, s2, l2, r2, l2 + len - i, false)) {
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
     * dp[len][j][j] ||= (dp[k][i][j]&& dp[len - k][i + k][j + k])
     * || (dp[k][i][j + len - k] && dp[len - k][i + k][j])
     * 其中k>=1 且 k < len
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
}
