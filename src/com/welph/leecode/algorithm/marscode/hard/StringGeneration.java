package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R有一个初始为空的字符串 s，她的目标是生成一个给定的字符串 t。
 * 她可以使用以下两种操作来生成这个字符串：
 *
 * 将任意一个字母添加到 s 的末尾。
 * 选择 s 中一个长度不小于 2 的后缀，并将其复制添加到 s 的末尾。
 * 小R想知道，有多少种不同的方法可以从空串 s 生成字符串 t。由于结果可能非常大，请对 10^9 + 7 取模。
 */
public class StringGeneration {

    //{@link Solution664}
    public static int solution(String t) {
        int mod = 1000000007;
        //DP[i] 代表到i时生成的方法个数
        int n = t.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        //fn[i][j] 有多长的相同后缀??? 若t[i]!=t[j] 则fn=0 若t[i]==t[j] 则f[i][j] = f[i-1][j-1]+1;
        // i是到当前,  j是之前, i>j && j>=i/2;
        for (int i = 1; i <= n; i++) {
            //变成类似于BM算法
            dp[i] = dp[i - 1];
            //长度不小于 2 的后缀
            int limit = (i + 1) / 2;
            for (int j = i - 2; j >= limit; j--) {
                int r = i - 1;
                int l = j - 1;
                while (r >= j && t.charAt(l) == t.charAt(r)) {
                    l--;
                    r--;
                }
                if (r < j) {
                    dp[i] = (dp[i] + dp[j]) % mod;
                }
            }
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution("ababa") == 3);
        System.out.println(solution("abcabc") == 2);
        System.out.println(solution("aaaa") == 2);
        System.out.println(solution("aabbbabaabbba") == 2);
    }
}
