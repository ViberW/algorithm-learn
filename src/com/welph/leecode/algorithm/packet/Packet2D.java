package com.welph.leecode.algorithm.packet;

/**
 * ----------------------------------------------二维背包
 * 有一个容量有限V, 且承受重量L的背包，容量为w , 重量为h，以及1个待选择的物品
 * 二维背包在于-----背包不仅考虑容量N 还考虑重量L
 * 
 */
public class Packet2D {

    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };// 体积
        int[] v = { 10, 20, 30, 10, 15, 5 };// 价值
        int[] h = { 2, 3, 3, 4, 1, 2 };// 重量
        System.out.println(maximumValue(w, v, h, 5, 10));
    }

    /*
     * 先处理每个物品仅有一个的情况
     * 状态转移方程:
     * -------------------------不放入 放入（所以要将第 i 个物品的价值计入）
     * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
     */
    public static int maximumValue(int[] w, int[] v, int[] h, int n, int l) {
        int m = w.length;
        int[][] dp = new int[n + 1][l + 1];// 使用二维数组 容量+重量
        for (int i = 0; i < m; ++i) {
            for (int j = n; j >= w[i]; --j) {// 容量循环
                for (int k = l; k >= h[i]; --k) {// 重量循环
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - h[i]] + v[i]);
                }
            }
        }
        return dp[n][l];
    }

}
