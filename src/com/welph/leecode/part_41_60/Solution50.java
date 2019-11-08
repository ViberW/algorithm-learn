package com.welph.leecode.part_41_60;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2 ^ -2 = 1/2^2 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class Solution50 {

    public static void main(String[] args) {
        System.out.println(myPow(2.00000d, -2));
    }

    /**
     * 这个对于很小的x，n较大值，时超出计算时间。单纯暴力法不行
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        int s = -1;
        if (n < 0) {
            s = 1;
        }
        double ans = 1;
        for (int i = n; i != 0; i += s) {
            if (i > 0) {
                ans = ans * x;
            } else {
                ans = ans / x;
            }
        }
        return ans;
    }

    /**
     * 计算n 只需要计算n/2 * n-n/2即可
     */
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    /**
     * 快速
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
}
