package com.welph.leecode.algorithm.thinking;

import java.util.Random;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [ST表] 数据结构 稀疏表
 * ---------主要用来解决RMQ（Range Maximum/Minimum Query，区间最大/最小值查询）
 * 应用倍增的思想，可以实现O(nlogn) 预处理、 O(1) 查询
 * @since 2021/6/11 15:46
 */
public class ST_Table_12 {

    static Random random = new Random();

    /**
     * ST表使用一个二维数组f，对于范围内的所有f[a][b]，
     * 先算出并存储 MAx(Ai)[a,a+2^b] 预处理。
     * 查询时，再利用这些子区间算出待求区间的最大值
     */
    public static void main(String[] args) {
        //预处理
        int n = 10;
        int maxB = (int) (Math.log(n) / Math.log(2));
        int[][] f = new int[10005][maxB + 1]; // 表示从j到j+2^i范围内的最大值(左闭右开)

        for (int i = 0; i < n; i++) {
            f[i][0] = random.nextInt(100);
        }

        for (int i = 1; i <= maxB; i++) {
            for (int j = 1; j + (1 << i) - 1 <= n; ++j)
                /*
                    f[j][i] 表示 j ~ j+2^i
                    f[j][i - 1] 表示  j ~ j+2^(i-1)
                    f[j + (1 << (i - 1))][i - 1] 表示 j+2^(i-1) ~ (j+2^(i-1))+2^(i-1) = j+2^i
                 */
                f[j][i] = Math.max(f[j][i - 1], f[j + (1 << (i - 1))][i - 1]); //动态规划
        }

        /*
            查询: [l,r]区间内的最大值, 可以理解为f[l][s]和f[r-2^s][s] 的最大值
            ----调整s, 保证趋近于l或r边界

            极端情况: l+(2^s)-1 =r  => s[log2(r-l+1)]
         */
        //预处理s 因为s为正整数
        int[] Log2 = new int[n];
        for (int i = 2; i <= n; ++i)
            Log2[i] = Log2[i / 2] + 1;

        //查询
        int l = 1;
        int r = 3;
        int s = Log2[r - l + 1];
        System.out.println(Math.max(f[l][s], f[r - (1 << s) + 1][s]));
    }


}
