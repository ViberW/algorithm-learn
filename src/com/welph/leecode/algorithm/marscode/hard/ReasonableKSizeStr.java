package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小C最近在研究合法的 k-size 字符串。一个字符串被称为 k-size，是指它可以被分成恰好 k 段连续的相同字符组成的子串。
 *
 * 例如：
 * 字符串 aabbbccc 是 3-size，因为它可以分成 ['aa', 'bbb', 'ccc'] 这三段。
 * 字符串 ababaab 是 6-size，因为它可以分成 ['a', 'b', 'a', 'b', 'aa', 'b'] 这六段。
 * 小C认为，只有当每个连续段的长度至少为2时，该字符串才是合法的。例如，aabbbccc 是合法的，而 ababaab 不是合法的。
 *
 * 现在，给定字符串长度 n 和要求的连续段数量 k，
 * 小C想知道，长度为 n 的、仅由小写字母组成的合法的 k-size 字符串有多少个？
 * 答案可能非常大，请将结果对 1e9+7 取模。
 */
public class ReasonableKSizeStr {

    //{@link PowerDemo}
    public static int solution(int n, int k) {
        if (n < 2 * k) {
            return 0;
        }
        int mod = 1000000007;
        //相当于n拆分成k个, 每段至少2的可能性
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i - 2; j >= 0; j--) {
                int lower = j == 0 ? 1 : 2;
                for (int c = Math.min(k, j / 2 + 1); c >= lower; c--) {
                    dp[i][c] = (dp[i][c] + dp[j][c - 1]) % mod;
                }
            }
        }
        long result = dp[n][k];
        //多个结果, 如果担心k很大,快速幂  result = (long) ((result * Math.pow(26, k)) % mod);
        result *= 26;
        k--;
        long base = 25; //第一个26种情况, 第二个25.... 相邻不能相同字符串
        while (k > 0) {
            if ((k & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            k >>= 1;
        }
        return (int) result;
    }

    public static void main(String[] args) {
//        System.out.println(solution(2, 1) == 26);
        System.out.println(solution(4, 2) == 650);
        System.out.println(solution(10, 5) == 10156250);
    }
}
