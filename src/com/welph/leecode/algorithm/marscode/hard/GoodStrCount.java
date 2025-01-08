package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U定义了一个“好字符串”，它的要求是该字符串中不包含任意长度不小于2的回文子串。
 * 现在小U拿到了一个字符串，她想知道有多少个非空的子序列是“好字符串”。你的任务是帮助她计算出这些子序列的数量。
 *
 * 例如，对于字符串 "aba"，它的子序列中除了 "aa" 和 "aba" 以外，其余五个子序列都是“好字符串”。
 *
 * 注意：由于答案可能非常大，你需要对结果取1000000007 进行输出。
 */
public class GoodStrCount {

    public static int solution(String s) {
        int mod = 1000000007;
        int n = s.length();
        long total = n; //单个字符
        //假设dp[i][j] 标识最后两位分别为i,j的好字符串长度
        long[][] dp = new long[n][n];
        //如果需要考虑 i和j的值相等,那么中间一定要选择2个及以上的且不包含回文的字符串, 起单独前后也不能包含.
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            //只需要管 s[j] != s[i]且s[j] != s[k] k为j子序列中的一个字符
            //对于第二种情况, s[k] 找到符合的. 中间不能回文. 且有且仅有一个字符子串的.可能. ab a
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(j) != s.charAt(i)) {
                    for (int k = j; k >= 0; k--) {
                        if (s.charAt(k) != s.charAt(i)) {
                            dp[j][i] = (dp[j][i] + dp[k][j]) % mod;
                        }
                    }
                    total = (total + dp[j][i]) % mod;
                }
            }
        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution("baabbabbabbabbba"));//76
        System.out.println(solution("aba") == 5);
        System.out.println(solution("aaa") == 3);
        System.out.println(solution("ghij") == 15);
    }

}
