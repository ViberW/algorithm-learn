package com.welph.leecode.algorithm.packet;

/**
 * ----------------------------------------------求背包最优方案数
 * 有一个容量有限的背包，容量为w，以及m个待选择的物品, 价值最高的方案有多少种
 * 
 */
public class PecketCount {

    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        System.out.println(maximumValue(w, v, 5));
    }

    /*
     * 这里以01背包为例
     */
    public static int maximumValue(int[] w, int[] v, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        g[0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = n; j >= w[i]; --j) {
                int maxW = Math.max(f[j], f[j - w[i]] + v[i]);
                int tmp = 0;
                if (maxW == f[j]) {
                    tmp += g[j];
                }
                if (maxW == f[j - w[i]] + v[i]) {
                    tmp += g[j - w[i]];
                }
                g[j] = tmp;
                f[j] = maxW;
            }
        }
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (f[i] == f[m]) {// 等同于最佳价值
                count += g[i];
            }
        }
        System.out.println("最佳价值的方案数为:" + count);
        return f[n];
    }
}
