package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小F正在使用一个骑士跳跃方式的拨号器。这个拨号器是一个类似电话键盘的 3x4 矩阵，
 * 每个数字对应一个单元格，骑士只能站在蓝色数字单元格上进行跳跃（数字 1 到 9 和 0）。
 * 骑士的移动方式和国际象棋中的马相同：
 * 1. 它可以垂直移动两个单元格并水平移动一个单元格，
 * 2. 或水平移动两个单元格并垂直移动一个单元格，形成 "L" 形。
 *
 * 123
 * 456
 * 789
 * *0#
 * 给定一个整数 n，你需要帮助小F计算骑士可以拨出的所有长度为 n 的不同电话号码的数量。
 * 骑士可以从任何数字开始，并在 n-1 次有效跳跃后得到一个有效号码。答案可能非常大，
 * 因此你需要返回对 10^9 + 7 取模的结果。
 */
public class KnightPhoneJump {

    public static int solution(int n) {
        int[][] dest = new int[10][];
        dest[0] = new int[]{4, 6};
        dest[1] = new int[]{8, 6};
        dest[2] = new int[]{7, 9};
        dest[3] = new int[]{4, 8};
        dest[4] = new int[]{3, 9, 0};
        dest[5] = new int[0];
        dest[6] = new int[]{1, 7, 0};
        dest[7] = new int[]{2, 6};
        dest[8] = new int[]{3, 1};
        dest[9] = new int[]{2, 4};
        int mod = 1000000007;
        ///表明每一种长度下的数值能够生成电话号码的可能性
        long[][] dp = new long[2][10];
        int cur = 0;
        int next = 1;
        Arrays.fill(dp[cur], 1); //每一种都可能
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                dp[next][j] = 0;
                for (int d : dest[j]) {
                    dp[next][j] = (dp[next][j] + dp[cur][d]) % mod;
                }
            }
            cur = cur ^ 1;
            next = next ^ 1;
        }
        long result = 0;
        for (long count : dp[cur]) {
            result = (result + count) % mod;
        }
        System.out.println(result);
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(1) == 10);
        System.out.println(solution(2) == 20);
        System.out.println(solution(3) == 46);
        System.out.println(solution(4) == 104);
    }
}
