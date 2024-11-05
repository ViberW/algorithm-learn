package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小R不再追求甜点中最高的喜爱值，今天他想要的是甜点喜爱值之和正好匹配他的预期值 S。
 * 为了达到这个目标，他可以使用魔法棒来改变甜点的喜爱值，使其变为原来喜爱值的阶乘。每个甜点只能使用一次魔法棒，也可以完全不用。
 * <p>
 * 下午茶小哥今天带来了 N 个甜点，每个甜点都有一个固定的喜爱值。小R有 M 个魔法棒，他可以选择任意甜点使用，但每个甜点只能使用一次魔法棒。
 * 他的目标是通过选择一些甜点，可能使用魔法棒，使得这些甜点的喜爱值之和恰好为 S。
 * <p>
 * 请计算小R有多少种不同的方案满足他的要求。如果两种方案中，选择的甜点不同，或者使用魔法棒的甜点不同，则视为不同的方案。
 */
public class SweetSelectToS {
    public static int solution(int n, int m, int s, int[] like) {
        Arrays.sort(like);
        int[][] dp = new int[s + 1][m + 1];
        dp[0][0] = 1;
        int pre = 1;
        int C = 1;
        for (int i = 0; i < n; i++) {
            int val = like[i];
            if (val > s) {
                break;
            }
            if (pre != val) {
                for (int q = pre + 1; q <= val; q++) {
                    C *= q;
                }
                pre = val;
            }
            for (int j = s; j > 0; j--) {
                if (j < val) {
                    break;
                }
                for (int k = Math.min(i + 1, m); k >= 0; k--) {
                    dp[j][k] += dp[j - val][k];
                    if (k > 0 && j >= C) { //使用魔法棒
                        dp[j][k] += dp[j - C][k - 1];
                    }
                }
            }
        }
        int total = 0;
        for (int i : dp[s]) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        int[] like1 = {1, 2, 3};
        int[] like2 = {1, 1, 1};

        System.out.println(solution(3, 2, 6, like1) == 5);
        System.out.println(solution(3, 1, 1, like2) == 6);
    }
}
