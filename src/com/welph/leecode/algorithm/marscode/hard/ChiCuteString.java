package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小R定义了一种特别的字符串,称之为可爱串。当且仅当该手字符串包含子序列
 * "chi",并且不包含子串"chi"时,称为可爱串。子序列是指字符串中不连续的一段,而子串则必须是连续的字符。
 *
 * 例如,字符串"cihci"包含子序列"chi",且不包含子串"chi",因此该字符串被称为可爱串。
 *
 * 现在,小R想知道,在长度为n的、仅由'c','h','i'三种字符组成的字符串中,有多少是可爱串?答案请对10^9+7取模。
 */
public class ChiCuteString {

    public static int solution(int n) {
        if (n < 4) {
            return 0;
        }
        int MOD = 1000000007;
        //动态规划:
        long[][][] dp = new long[2][3][4];
        int curr = 1;
        int pre = 0;
        dp[pre][0][0] = 1;
        for (int i = 0; i < n; i++) {
            //[k][h] 代表到了当前处,结尾子串长度为k,且子序列长度h
            for (int k = 0; k < 3; k++) {
                for (int h = k; h < 4; h++) {
                    for (int j = 0; j < 3; j++) {
                        if (k == j) {// 正好是缺少的值
                            if (k != 2) { //最后的i不能添加
                                dp[curr][k + 1][h == j ? (h + 1) : h] += dp[pre][k][h];
                            }
                        } else {
                            dp[curr][j == 0 ? 1 : 0][h == j ? (h + 1) : h] += dp[pre][k][h];
                        }
                    }
                    /*//添加c
                    if (k == 0) {
                        dp[k + 1][h == 0 ? h + 1 : h] = dp[k][h];
                    } else {
                        //h == 0 ? h + 1 : h  因为h不可能为0
                        dp[1][h] = dp[k][h];
                    }
                    //添加h
                    if (k == 1) {
                        dp[k + 1][h == 1 ? h + 1 : h] += dp[k][h];
                    } else {
                        dp[0][h == 1 ? h + 1 : h] += dp[k][h];
                    }
                    //添加i
                    if (k == 2) {
                        //不能加i
                    } else {
                        dp[0][h == 2 ? h + 1 : h] += dp[k][h];
                    }*/
                }
            }
            //交换
            curr = curr ^ 1;
            pre = pre ^ 1;
            for (long[] cur : dp[curr]) {
                Arrays.fill(cur, 0);
            }
        }
        long result = (dp[pre][0][3] + dp[pre][1][3] + dp[pre][2][3]) % MOD;
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4) == 3);
        System.out.println(solution(5) == 24);
        System.out.println(solution(6) == 126);
    }
}
