package com.welph.leecode.algorithm.packet;

/**
 * ----------------------------------------------分组背包
 * 有一个容量有限的背包，容量为w，以及m个待选择的物品
 * 其中若干个物品为一组, 每组只能选择一种物品 组编号为g
 * 分组背包在于------[多个物品一组]-----
 */
public class PacketPartition {

    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        int[] g = { 1, 1, 2, 2, 2, 3 };
        System.out.println(maximumValue(w, v, g, 5));
    }

    /*
     * 相同j情况下 同一组内多次尝试最大价值
     */
    public static int maximumValue(int[] w, int[] v, int[] g, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        int group;
        boolean check;
        int k = 0;
        for (int i = 0; i < m;) {
            group = g[i];
            check = false;
            for (int j = n; j >= 0; j--) {
                for (k = i; k < m && g[k] == group; k++) {// 找到后面和当前相同的g
                    if (w[k] <= j) {
                        check = true;
                        f[j] = Math.max(f[j], f[j - w[k]] + v[k]);
                    }
                }
                if (!check) {// 说明在小就不能满足了
                    break;
                }
            }
            i = k;
        }
        return f[n];
    }
}
