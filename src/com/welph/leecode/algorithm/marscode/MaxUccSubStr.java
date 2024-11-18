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

    /**
     * {@link  com.welph.leecode.part_1_500.part_61_80.Solution72}
     */
    public static int solution(int m, String s) {
        int n = s.length();
        String target = "UCC";
        // dp[i][j] 表示用 j 次编辑在前 i 个字符中能得到的最多 UCC 子串
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 3; i <= m; i++) {
            dp[0][i] = i / target.length();
        }
        //删除操作最不好了, 因为可以使用新增或者更新使得数据变得更好.
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j]; //跳过i
                /*
                 * UCC
                 * CUU
                 * 从后往前看,
                 * 若最后一个不同,则相当于是只能先考虑向后添加CC. 不用考虑向左走, 因为结果等于i-1的最大值
                 * 若中间的不同, 则相当于是UC 添加C操作就行了, dp[i-2][j-1]+1
                 * 若第一个不同,则相当于是CCC 相当于是要添加个U就行. dp[i-2][j-1]+1 ,
                 * 若是第一个相同,则相当于是UCC dp[i-3][j]+1
                 */
                if (s.charAt(i - 1) == 'U') {
                    if (j >= 2) {
                        dp[i][j] = Math.max(dp[i - 1][j - 2] + 1, dp[i][j]);
                    }
                    continue;
                }
                if (j >= 2) {
                    dp[i][j] = Math.max(dp[i - 1][j - 2] + 1, dp[i][j]); //直接前面添加UC
                }
                if (i > 1 && j > 0) {
                    dp[i][j] = Math.max(dp[i - 2][j - 1] + 1, dp[i][j]); //新增或删除
                }
                if (i > 2 && s.charAt(i - 2) == 'C' && s.charAt(i - 3) == 'U') {
                    dp[i][j] = Math.max(dp[i - 3][j] + 1, dp[i][j]);
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
        System.out.println(solution(3, "UCUUCCCCC"));
        System.out.println(solution(6, "U"));
        System.out.println(solution(2, "UCCUUU"));
        System.out.println(solution(3, "CUUUCUCUUUUCU"));
        System.out.println(solution(9, "CUUUUC"));
    }
}
