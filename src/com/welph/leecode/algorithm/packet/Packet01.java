package com.welph.leecode.algorithm.packet;

import java.util.Arrays;

import com.welph.leecode.algorithm.z7z8.MaximumPacket;

/**
 * ----------------------------------------------01背包
 * for (int i = 0; i < n; i++) {
 * for (int j = m; j >= V[i]; j--) {
 * f[j] = max(f[j], f[j-V[i]] + W[i]);
 * }
 * }
 * 有一个容量有限的背包，容量为w，以及m个待选择的物品
 * 01背包在于------[每个只有一件]-----
 * 
 * @see MaximumPacket
 */
public class Packet01 {
    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        System.out.println(maximumValue(w, v, 5));
        System.out.println(maximumValue2(w, v, 5));
    }

    /**
     * 设f[i][j]表示前i个物品背包容量为j时，能选择的最大价值。
     * w[i]表示第i个物品的重量，v[i]表示第i个物品的价值。
     * 1. 装不下第i个物品，则f[i][j]=f[i-1][j]
     * 2. 能装下第i个物品，则f[i][j]=max(f[i][j],f[i-1][j-w[i]]+v[i])
     */
    public static int maximumValue(int[] w, int[] v, int n) {
        int m = w.length;
        int[][] f = new int[m + 1][n + 1];
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= w[i - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }
        return f[m][n];
    }

    public static int maximumValue2(int[] w, int[] v, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < m; ++i) {// 根据上面方法的简化, 主要是i行计算用到的是上一行的,且比它j小,所以倒序
            for (int j = n; j >= w[i]; --j) {
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }
        return f[n];
    }

    //////////////////////////// 其他例子/////////////////////////////////////

    /**
     * 将集合分成两个部分，并且两个部分的元素和相等
     * 等价于能否从包含 n 个元素的，并且元素和为 sum 的数组中集合中找到 k(k<n) 个数，该 k 个数之和为 sum/2
     * 
     * @return
     */
    public static boolean canPartition() {
        int[] arr = { 2, 3, 4, 5, 6 };
        // 解题:
        int sum = Arrays.stream(arr).sum();
        if ((sum & 1) != 0) {
            return false;
        }
        int half = sum >> 1;
        boolean[] dp = new boolean[half + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = half; j >= arr[i]; j--) {
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }
        return dp[half];
    }

    /**
     * 设原始数组中元素和为 sum，在我们添加一部分符号之后，可能一定会有一些元素变成负数, 总和变为target
     * 等价于
     * 变成负数的元素的绝对值之和为 sum_neg，剩下的非负数之和为 sum_pos
     * sum_pos + sum_neg = sum
     * sum_pos - sum_neg = target
     * => sum_neg = (sum - target)/2;
     * 即求是否存在总和为sum_neg
     * 
     * @return
     */
    public static int findTargetSumWays() {
        int[] arr = { 2, 3, 4, 5, 6, 8, 10, 1 };
        int target = 5;
        int sum = Arrays.stream(arr).sum();
        if (target > sum || target < -sum || (sum - target) % 2 != 0)
            return 0;
        int sum_neg = (sum - target) / 2;
        int[] dp = new int[sum_neg + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum_neg; j >= arr[i]; j--) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }
        return dp[sum_neg];
    }
}
