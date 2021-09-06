package com.welph.leecode.algorithm.z7z8;

/**
 * 有一个容量有限的背包，容量为w，以及m个待选择的物品，每个只有一件。
 * 每个物品有一定的重量和价值，那么选择哪些物品放入背包，可使选择的物品总价值最大呢？
 */
public class MaximumPacket {

    public static void main(String[] args) {
        int[] w = {1, 2, 1, 4, 2, 3};
        int[] v = {10, 20, 30, 10, 15, 5};
        //背包重5kg, 求最大装载价值
        System.out.println(maximumValue(w, v, 5));
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
}
