package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小C拿到了一个由数字字符和 ? 组成的字符串，她的目标是将所有的 ? 替换成数字字符，使得替换后的字符串表示的十进制整数成为正整数
 * p
 * p 的倍数。由于方案数可能非常大，需要对最终的结果取模 1000000007。
 */
public class ReplaceUnknownNum {

    public static int solution(String s, int p) {
        //dp[i][r] 前i个字符组成的字符串, 对p的余数
        int mod = 1000000007;
        int n = s.length();
        int[][] dp = new int[n + 1][p];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < p; j++) {
                if (s.charAt(i - 1) == '?') {
                    for (int k = 0, current = (10 * j) % p; k <= 9; k++) {
                        dp[i][current] = (dp[i][current] + dp[i - 1][j]) % mod;
                        current = (current + 1) % p;
                    }
                } else {
                    int current = (10 * j + (s.charAt(i - 1) - '0')) % p;
                    dp[i][current] = (dp[i][current] + dp[i - 1][j]) % mod;
                }
            }
        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        System.out.println(solution("??", 1) == 100);
        System.out.println(solution("????1", 12) == 0);
        System.out.println(solution("1??2", 3) == 34);
    }

}
