package com.welph.leecode.algorithm.marscode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 小U拿到了一个长度为n的序列S，他想通过某种方法将这个序列划分为k段，并且使每一段都不为空。
 * 他发现，通过对每一段应用一个unique操作（即将相邻且相同的元素合并成一个）之后，可以得到一个新的序列，
 * 并定义函数f(A)表示经过unique操作后的序列的长度。小U想知道，如何划分才能使所有段的$f`函数值之和最大化？
 *
 * 例如，给定序列 [1,1,1,2,2,3,3,1]，小U可以将其划分为三段，分别为 [1,1,1]、[2,2] 和 [3,3,1]，
 * unique 之后得到的序列分别是 [1]、[2] 和 [3,1]，那么最终的f函数值之和为 6。
 */
public class UniqueSubNumbers {

    public static int solution(int n, int k, List<Integer> a) {
        //需要求和最大 DP
        int[] weight = new int[n + 1];
        int[][] dp = new int[n + 1][k + 1];
        int preValue = a.get(0);
        weight[0] = 1;
        for (int i = 1; i <= n; i++) {
            weight[i] = a.get(i - 1) != preValue ? (weight[i - 1] + 1) : weight[i - 1];
            for (int j = 1; j <= i; j++) {
                int ijWeight = weight[i] - weight[j] + 1;
                for (int h = k; h > 0; h--) {
                    dp[i][h] = Math.max(dp[j - 1][h - 1] + ijWeight, dp[i][h]);
                }
            }
            preValue = a.get(i - 1);
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(solution(8, 3, Arrays.asList(1, 1, 1, 2, 2, 3, 3, 1)) == 6);
        System.out.println(solution(6, 2, Arrays.asList(1, 2, 3, 3, 2, 1)) == 6);
        System.out.println(solution(5, 1, Arrays.asList(1, 1, 1, 1, 1)) == 1);
    }

}
