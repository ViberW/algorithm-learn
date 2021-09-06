package com.welph.leecode.algorithm.z7z8;

/**
 * 从原点出发，每次只能向东，向北，向西走，且不能走已经走过的地方，那么走N步共有多少种不同的方式呢？
 */
public class TaskNStep {

    public static void main(String[] args) {
        System.out.println(taskN(5));
        System.out.println(taskN2(5));
    }


    /**
     * 动态规划问题: 上一步关系下一步
     * 1. 向北走, 下一步可向北/东/西
     * 1. 向东走, 下一步可向北/东
     * 1. 向西走, 下一步可向北/西
     */
    public static int taskN(int n) {
        int[][] steps = new int[n][3];
        steps[0][0] = 1;//东
        steps[0][1] = 1;//西
        steps[0][2] = 1;//北
        for (int i = 1; i < n; i++) {
            steps[i][0] = steps[i - 1][0] + steps[i - 1][2];
            steps[i][1] = steps[i - 1][1] + steps[i - 1][2];
            steps[i][2] = steps[i - 1][0] + steps[i - 1][1] + steps[i - 1][2];
        }
        return steps[n - 1][0] + steps[n - 1][1] + steps[n - 1][2];
    }

    /**
     * 简化代码:  设第i步的总方案数为s[i]
     * s[i]=f[i][0]+f[i][1]+f[i][2]。
     * =2*f[i-1][0]+2*f[i-1][1]+3*f[i-1][2]。
     * =2*s[i-1]+f[i-1][2]
     * <p>
     * 而f[i-1][2]=f[i-2][0]+f[i-2][1]+f[i-2][2]=s[i-2]。
     * 得s[i]=2*s[i-1]+s[i-2]。
     */
    public static int taskN2(int n) {
        int[] s = new int[n + 1];
        s[0] = 1;
        s[1] = 3;
        for (int i = 2; i <= n; i++) {
            s[i] = 2 * s[i - 1] + s[i - 2];
        }
        return s[n];
    }
}
