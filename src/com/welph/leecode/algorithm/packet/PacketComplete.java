package com.welph.leecode.algorithm.packet;

/**
 * -----------------------------完全背包
 * for (int i = 0; i < n; i++) {
 * for (int j = V[i]; j <= m; j++) {
 * f[j] = max(f[j], f[j-V[i]] + W[i]);
 * }
 * }
 * 有一个容量有限的背包，容量为w，以及m个待选择(无数个)的物品
 * 完全背包在于------[每个只有无数件]-----
 */
public class PacketComplete {

    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        System.out.println(maximumValue(w, v, 5));
    }

    public static int maximumValue(int[] w, int[] v, int n) {
        int m = w.length;
        int[][] f = new int[m + 1][n + 1];
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (j >= w[i - 1]) {
                    // 这里不同于01背包, 这里放和不放选择最大值
                    // tips: f[i][j - w[i - 1]] 不同于01背包的 f[i-1][j - w[i - 1]]
                    // 因为需要f[i][j - w[i - 1]] 已经考虑f[i-1][j - w[i - 1]], 这里为的是多次投放i
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - w[i - 1]] + v[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        return f[m][n];
    }

    public static int maximumValue2(int[] w, int[] v, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < m; ++i) {// 根据上面方法的简化, 为了可能得多次投放i 要顺序计算
            for (int j = w[i]; j <= n; ++j) {
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }
        return f[n];
    }

    //////////////////////////// 其他例子/////////////////////////////////////

    /**
     * 存在多个无限面值的硬币,要求组合成target价值的最少硬币数
     * 
     * @return
     */
    public static int coinChange() {
        int[] arr = { 1, 2, 5, 10, 20 };
        int target = 77;
        int[] dp = new int[target + 1];
        for (int i = 0; i < arr.length; ++i) {
            for (int j = arr[i]; j <= target; ++j) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }
        return dp[target];
    }

    /**
     * 给一个整数表示不同的面值,另给一个target的总金额,返回拼凑的可能数
     * 
     * @return
     */
    public static int coinChange2() {
        int[] arr = { 1, 2, 5, 10, 20 };
        int target = 77;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = arr[i]; j <= target; ++j) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }
        return dp[target];
    }
}
