package com.welph.leecode.part_1_500.part_81_100;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class Solution97 {

    public static void main(String[] args) {
        //System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave2("a", "", "a"));
    }


    /**
     * 需要用到动态规划, 因为相同1-j的位置可能已经是校验处理过的.
     * boolean i3 = dp[ii-1][i2] && s1[i1]==s3[i3]
     * .       || dp[ii][i2-1] && s1[i2]==s3[i3]
     * //动态规划有效  时间95% 空间 14%   其实 每一次仅仅会用到上一次的一组数据. --思考 可以进一步缩减空间消耗
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len2 + len1 != len3) {
            return false;
        }
        if (len1 < len2) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
            len1 = s1.length();
            len2 = s2.length();
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        char c;
        int min;
        for (int i = 1; i <= len3; i++) {
            c = s3.charAt(i - 1);
            min = Math.min(i, len1);
            for (int j = Math.max(0, i - len2 - 1); j < min; j++) {
                //保证 [1,0] 能够进来做判断  //若 i> len1 则需要用dp[len1][i-len1] 此时不需要判断第一个
                if (dp[j][i - j - 1]) {
                    if (j != min && s1.charAt(j) == c) {
                        dp[j + 1][i - j - 1] = true;
                    }
                    if (i - j - 1 < len2 && s2.charAt(i - j - 1) == c) {
                        dp[j][i - j] = true;
                    }
                }
            }
            if (i > len1) {
                if (dp[min][i - min - 1] && s2.charAt(i - min - 1) == c) {
                    dp[min][i - min] = true;
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 啥也不说  上来就暴力递归法给处理看看
     * 贼慢 耗时5% 空间14%
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        //如果暴力法的话 能结果,深度匹配
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return interleave(s1, 0, s2, 0, s3, 0);
    }

    public static boolean interleave(String s1, int i1, String s2, int i2, String s3, int i3) {
        if (i3 == s3.length()) {
            return i1 == s1.length() || i2 == s2.length();
        }
        if (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i3)) {
            if (interleave(s1, i1 + 1, s2, i2, s3, i3 + 1)) {
                return true;
            }
        }
        if (i2 < s2.length() && s2.charAt(i2) == s3.charAt(i3)) {
            if (interleave(s1, i1, s2, i2 + 1, s3, i3 + 1)) {
                return true;
            }
        }
        return false;
    }


}
