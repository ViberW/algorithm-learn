package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;

/**
 * 把M个苹果放在N个盘子里，允许有的盘子空着不放，那么总共有多少种不同的分法呢？
 */
public class PutApple {

    public static void main(String[] args) {
        System.out.println(putApple(3, 2));
    }

    /**
     * 分治+动态规划
     * ----------------
     * 设f[m,n]表示M个苹果刚好分成N堆的方法数，那最终要求的就是f[m,1]+f[m,2]+...+f[m,n]。
     * 这是一个递推关系，那肯定得有边界，不然从何推起呢？
     * 前面已经分析了m，n的大小关系不定，也就是有3种情况。
     * 1.   m<n，把m个苹果刚好分成n堆，明显是不可能的，所以f[m,n]=0
     * 2.   m=n，把m个苹果刚好分成m堆，明显只有一种情况，就是每一堆1个，所以f[m,n]=1
     * 3.   m>n，第3小节已经说明，这种情况就划分成子问题，先在每一堆放1个，
     * .        剩下的再依次分成1堆，2堆...n堆，所以f[m,n]=f[m-n,1]+f[m-n,2]+...+f[m-n,n]
     */
    public static int putApple(int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(f[i], -1);
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += solve(m, i, f);
        }
        return ans;
    }

    private static int solve(int m, int n, int[][] f) {
        if (f[m][n] != -1) return f[m][n];
        //苹果少于堆数, 则为0
        if (m < n) {
            f[m][n] = 0;
            return f[m][n];
        }
        //苹果等于堆数或只有1堆
        if (m == n || n == 1) {
            f[m][n] = 1;
            return f[m][n];
        }
        //苹果>堆数
        f[m][n] = 0;
        for (int i = 1; i <= n; ++i) {
            f[m][n] += solve(m - n, i, f);
        }
        return f[m][n];
    }

    /**
     * 上面是针对苹果分几堆,这里是将苹果分配情况划分为[有空盘]和[无空盘]的总和
     * ----------
     * 有空盘子如何分解：
     * .   其实就是先拿一个盘子出来空着，这样就变成了M个苹果，N-1个盘子的问题
     * 没有空盘子如何分解：
     * .   其实就是先在每一个盘子放一个，这样就变成了M-N个苹果，N个盘的问题
     */
    private static int solve1(int m, int n) {
        if (m < 0) return 0;
        if (m == 0 || n == 1) return 1;
        return solve1(m - n, n) + solve1(m, n - 1);
    }

    /////////////题目转化: 整数划分////////////////////////////

    /**
     * 问: 把一个整数n写成多个大于等于1小于等于它本身的整数的和，
     * 即n=m1+m2+...，则[m1,m2...]构成的一个集合称为n的一个划分，那么总共有多少种不同的划分呢？
     * -------------
     * 其实就是将n个1装入n个堆的划分总和
     */
    private static int intPartition(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += solve1(n, i);
        }
        return ans;
    }
}
