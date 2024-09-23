package com.welph.leecode.part_500_1000.part_561_580;

/**
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。
 * 你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * <p>
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，
 * 找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 * <p>
 * 示例 1：
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 * <p>
 * 提示：
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 */
public class Solution576 {

    public static void main(String[] args) {
        // System.out.println(findPaths(2, 2, 2, 0, 0));
        // System.out.println(findPaths(1, 3, 3, 0, 1));
        System.out.println(findPaths(8, 50, 23, 5, 26));
        // 914783380
        // 2147483647
        // 1000000007
        // 1776894990
    }

    /**
     * 动态规划: dp[r][c][k] 表示在k步内, 移出到边界外的可能
     * dp[r][c][k] = dp[r+1][c][k-1]+dp[r+1][c][k-1]
     * +dp[r][c+1][k-1]+dp[r][c-1][k-1]
     * 处于边界的也需要1步
     * ------------------------
     * todo 这里面仅仅需要用到两个数组, k 和k-1
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int mod = 1000000007;
        int[][][] dp = new int[m][n][maxMove + 1];
        // 一旦有n+1 m+1 或者 0 就是1 可以的
        for (int k = 1; k <= maxMove; k++) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    dp[r][c][k] = (int) ((0L + dpInt(dp, r - 1, c, k - 1, m, n)
                            + dpInt(dp, r + 1, c, k - 1, m, n)
                            + dpInt(dp, r, c + 1, k - 1, m, n)
                            + dpInt(dp, r, c - 1, k - 1, m, n)) % mod);
                }
            }
        }
        int total = 0;
        for (int i = 1; i <= maxMove; i++) {
            total = (int) ((0L + total + dp[startRow][startColumn][i]) % mod);
        }
        return total;
    }

    private static int dpInt(int[][][] dp, int r, int c, int k, int m, int n) {
        if (r < 0 || c < 0 || r == m || c == n) { // 说明来到了边界
            return k == 0 ? 1 : 0;
        } else {
            return dp[r][c][k];
        }
    }

    /*
     * 官方题解
     */
    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int outCounts = 0;
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1; // 代表1步能到达的点
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];
                    if (count > 0) {// 说明经过i步能够到达jk处
                        for (int[] direction : directions) {
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) { // 还在圈内到j1k1的可能条数
                                dpNew[j1][k1] = (dpNew[j1][k1] + count) % MOD;
                            } else {
                                outCounts = (outCounts + count) % MOD; // 说明出去了,结果加上去
                            }
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return outCounts;
    }

}
