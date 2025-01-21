package com.welph.leecode.algorithm.marscode.middle;

import java.util.Arrays;

/**
 * 小C来到了一家饭馆，这里共有 n 道菜，第 i 道菜的价格为 a_i。
 * 其中一些菜中含有蘑菇，s_i 代表第 i 道菜是否含有蘑菇。
 * 如果 s_i = '1'，那么第 i 道菜含有蘑菇，否则没有。
 *
 * 小C希望点 k 道菜，且希望总价格尽可能低。
 * 由于她不喜欢蘑菇，她希望所点的菜中最多只有 m 道菜含有蘑菇。
 * 小C想知道在满足条件的情况下能选出的最小总价格是多少。
 *
 * 如果无法按照要求选择菜品，则输出-1。
 */
public class SelectMushroomDish {

    public static long solution(String s, int[] a, int m, int k) {
        int n = a.length;
        int[][] dp = new int[k + 1][m + 1];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        int mCount = 0;
        for (int i = 1; i <= n; i++) { //背包
            int value = a[i - 1];
            boolean isMushroom = s.charAt(i - 1) == '1';
            for (int j = Math.min(k, i); j > 0; j--) {
                if (isMushroom) { //包含蘑菇
                    mCount++;
                    for (int c = Math.min(mCount, m); c > 0; c--) {
                        if (dp[j - 1][c - 1] == Integer.MAX_VALUE) {
                            continue;
                        }
                        dp[j][c] = Math.min(dp[j][c], dp[j - 1][c - 1] + value);
                    }
                } else {
                    for (int c = Math.min(mCount, m); c >= 0; c--) {
                        if (dp[j - 1][c] == Integer.MAX_VALUE) {
                            continue;
                        }
                        dp[j][c] = Math.min(dp[j][c], dp[j - 1][c] + value);
                    }
                }
            }
        }
        return Arrays.stream(dp[k]).filter(v -> v != Integer.MAX_VALUE).min().orElse(-1);
    }

    public static void main(String[] args) {
        System.out.println(solution("001", new int[]{10, 20, 30}, 1, 2) == 30);
        System.out.println(solution("111", new int[]{10, 20, 30}, 1, 2) == -1);
        System.out.println(solution("0101", new int[]{5, 15, 10, 20}, 2, 3) == 30);
    }
}
