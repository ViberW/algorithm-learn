package com.welph.leecode.algorithm.marscode;

/**
 * 问题描述
 * 小S有一个由字符 'U' 和 'C' 组成的字符串S，并希望在编辑距离不超过给定值 m 的条件下，尽可能多地在字符串中找到 "UCC" 子串。
 * <p>
 * 编辑距离定义为将字符串S 转化为其他字符串时所需的最少编辑操作次数。允许的每次编辑操作是插入、删除或替换单个字符。
 * 你需要计算在给定的编辑距离限制 m 下，能够包含最多 "UCC" 子串的字符串可能包含多少个这样的子串。
 * <p>
 * 例如，对于字符串"UCUUCCCCC"和编辑距离限制m = 3，可以通过编辑字符串生成最多包含3个"UCC"子串的序列。
 */
public class MaxUccSubStr {

    public static int solution(int m, String s) {
        int n = s.length();
        String target = "UCC";
        // dp[i][j] 表示用 j 次编辑在前 i 个字符中能得到的最多 UCC 子串
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 3; i <= m; i++) {
            dp[0][i] = i / target.length();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (i > 1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j]);
                }
                if (i > 2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 3][j]);
                }
                //若3号位u  max(dp[j]= d[i-1][j-2]+1)     d[i-1][j]
                //若3号位c  max(dp[j]= d[i-1][j-2]+1)
                //---------------------------走到这说明上一次是U
                //若2号位u  max(dp[j]= d[i-2][j-1]+1)  d[i-2][j]
                //若2号位c  max(dp[j]= d[i-2][j-1]+1)
                //---------------------------走到这说明上一次是U
                //若3号位u  max(dp[j]= d[i-2][j-1]+1)
                //若3号位c  max(dp[j]= d[i-3][j]+1)
                for (int k = 1; k <= target.length(); k++) {
                    if (j + k >= target.length()) {
                        if (i >= k) {
                            if (target.charAt(target.length() - k) != s.charAt(i - k)) { //这里的i代表i-1的地方
                                if (k != target.length()) { //不能去除 因为考虑最后一位的不相等
                                    dp[i][j] = Math.max(dp[i][j], dp[i - k][j + k - target.length()] + 1);
                                }
                                break;
                            } else {
                                dp[i][j] = Math.max(dp[i][j], dp[i - k][j + k - target.length()] + 1);
                            }
                        } else {
                            dp[i][j] = Math.max(dp[i][j], dp[0][j + k - target.length()] + 1);
                            break;
                        }
                    }
                }
            }
        }

        // 找到最大 UCC 子串数量
        int maxUCC = 0;
        for (int j = 0; j <= m; j++) {
            maxUCC = Math.max(maxUCC, dp[n][j]);
        }
        return maxUCC;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, "UCUUCCCCC") == 3);
        System.out.println(solution(6, "U") == 2);
        System.out.println(solution(2, "UCCUUU") == 2);
    }
}
