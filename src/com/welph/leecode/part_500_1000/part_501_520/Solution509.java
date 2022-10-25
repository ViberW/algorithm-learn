package com.welph.leecode.part_500_1000.part_501_520;

/**
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，
 * 后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * 提示：
 * 0 <= n <= 30
 */
public class Solution509 {

    public static void main(String[] args) {
        System.out.println(fib(3));
        System.out.println(fib(2));
        System.out.println(fib(4));
    }

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int l = 0;
        int r = 1;
        int ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = l + r;
            l = r;
            r = ret;
        }
        return ret;
    }
}
