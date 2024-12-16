package com.welph.leecode.algorithm.marscode;

/**
 * 小R拿到了一个 n 行 m 列的小写字母矩阵，
 *
 * 她想知道有多少个子矩阵满足以下条件：每个字母在这个子矩阵中最多出现一次。
 */
public class SubSingleMatrix {

    public static int solution(int n, int m, String[] s) {
        //判断某个范围内的字母需要最多一次
        //空间换时间
        int[][][][] dp = new int[n][m][n][m];
        int duplicate = 1 << 26;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = 1 << (s[i].charAt(j) - 'a');
                dp[i][j][i][j] = curr;
                result++;
                for (int k = i; k >= 0; k--) {
                    for (int h = i == k ? j - 1 : j; h >= 0; h--) {
                        int val = curr;
                        if (i > k) {
                            if ((val & dp[i - 1][j][k][j]) > 0) {
                                val |= duplicate;
                            }
                            val = val | dp[i - 1][j][k][j];
                        }
                        if (j > h) {
                            if ((val & dp[i][j - 1][i][h]) > 0) {
                                val |= duplicate;
                            }
                            val = val | dp[i][j - 1][i][h];
                        }
                        if (i > k && j > h) {
                            if ((val & dp[i - 1][j - 1][k][h]) > 0) {
                                val |= duplicate;
                            }
                            val = val | dp[i - 1][j - 1][k][h];
                        }
                        if (val < duplicate) {
                            result++;
                        }
                        dp[i][j][k][h] = val;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(solution(2, 3, new String[]{"aad", "abc"}) == 13);
        System.out.println(solution(3, 3, new String[]{"abc", "def", "ghi"}) == 36);
        System.out.println(solution(4, 4, new String[]{"abcd", "efgh", "ijkl", "mnop"}) == 100);
    }
}
