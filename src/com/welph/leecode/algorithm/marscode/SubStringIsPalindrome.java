package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小R 拿到一个由小写字母组成的字符串 s，以及一个整数 k。
 * 她需要将字符串拆分成 k 个互不重叠的子串，每个子串都必须是回文。
 *
 * 为了达到这个要求，她可以对字符串中的部分字符进行修改，将它们改成其他小写字母。
 * 小R 想知道，要保证每个子串都是回文，最少需要修改多少个字符。
 *
 * 例如：对于字符串 s = "cab"，如果需要分割成 k = 2 个回文子串，
 * 她可以将其修改为 "caa"，然后将字符串分割为 ["c", "aa"]。
 */
public class SubStringIsPalindrome {

    public static int solution(String s, int k) {
        //动态规划, 到达i处,分割处j个回文串的最小修改字符
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        int[] palindrome = new int[n + 1]; //当前到i处修改为回文,需要多少次
        Arrays.fill(dp[0], n);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], n);
            for (int j = 1; j <= i; j++) {
                if (j == i) {
                    palindrome[j] = 0;
                } else if (j + 1 == i) {
                    palindrome[j] = s.charAt(j - 1) == s.charAt(i - 1) ? 0 : 1; //修改1次
                } else {
                    palindrome[j] = palindrome[j + 1] + (s.charAt(j - 1) == s.charAt(i - 1) ? 0 : 1);
                }
                for (int h = k; h > 0; h--) {//范围h个回文串
                    dp[i][h] = Math.min(dp[i][h], dp[j - 1][h - 1] + palindrome[j]);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(solution("cab", 2) == 1);
        System.out.println(solution("aabcc", 3) == 0);
        System.out.println(solution("xyzqwertk", 4) == 3);
    }
}
