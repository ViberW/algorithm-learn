package com.welph.leecode.part_500_1000.part_541_560;

import java.util.Arrays;

/**
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。
 * 答案可能很大，所以返回对 10^9 + 7 取余 的结果。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：8
 * 解释：
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：n = 10101
 * 输出：183236316
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 */
public class Solution552 {

    public static void main(String[] args) {
        System.out.println(checkRecord(2));
        System.out.println(checkRecord(10101));
    }

    /**
     * 动态规划
     * dp[i] = dp[i-1] + 'A' 少于1的可能, 前面就必须没有'A'
     * dp[i-1] + 'L' i-1及i-2不为'L' => dp[i-1]['L']
     * dp[i-1] + 'P' 所有
     * 连续两天的'L'
     * 存在'A' 的量
     */
    public static int checkRecord(int n) {// "PP" , "AP", "LP", "PL", "AL", "LL", "PA", "LA"
        int MOD = (int) (Math.pow(10, 9) + 7);
        // 前面有多少个a 有多少个l
        int[][] dp = new int[2][3];
        int[][] dp1;
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp1 = new int[2][3];
            // 最后p ,a不变 l为0
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp1[j][0] = (dp1[j][0] + dp[j][k]) % MOD;
                }
            }
            // 最后a ,l为0
            for (int k = 0; k < 3; k++) {
                dp1[1][0] = (dp1[1][0] + dp[0][k]) % MOD;
            }
            // 最后l, a不变
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    dp1[j][k] = (dp1[j][k] + dp[j][k - 1]) % MOD;
                }
            }
            dp = dp1;
        }
        int sum = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                sum = (sum + dp[j][k]) % MOD;
            }
        }
        return sum % MOD;
    }

    /*
     * 
     * 官方题解 -链接矩阵 & 快速幂
     * dp[i][j×3+k] 表示前 i 天有 j 个 ‘A’ 且结尾有连续 k 个 ‘L’ 的可奖励的出勤记录的数量
     */
    static final int MOD = 1000000007;

    public int checkRecord2(int n) {
        long[][] mat = {
                { 1, 1, 0, 1, 0, 0 },
                { 1, 0, 1, 1, 0, 0 },
                { 1, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0 } };
        long[][] res = pow(mat, n);
        long sum = Arrays.stream(res[0]).sum();
        return (int) (sum % MOD);
    }

    public long[][] pow(long[][] mat, int n) {
        long[][] ret = { { 1, 0, 0, 0, 0, 0 } };
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, mat);
            }
            n >>= 1;
            mat = multiply(mat, mat);
        }
        return ret;
    }

    public long[][] multiply(long[][] a, long[][] b) {
        int rows = a.length, columns = b[0].length, temp = b.length;
        long[][] c = new long[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < temp; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= MOD;
                }
            }
        }
        return c;
    }

}
