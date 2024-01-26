package com.welph.leecode.part_1_500.part_441_460;

/**
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，
 * 其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 * <p>
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * <p>
 * 示例 2：
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class Solution441 {

    public static void main(String[] args) {
        System.out.println(arrangeCoins(5));
        System.out.println(arrangeCoins(6));
        System.out.println(arrangeCoins(8));
        System.out.println(arrangeCoins(9));
        System.out.println(arrangeCoins(1804289383));
    }

    /**
     * k ~= sqrt((2n+1/4)) -1/2;
     * 由于之后只需要判断k是否有小数,
     */
    /*
     * n阶的和: n = (x+1) * x / 2
     * => x^2 + x -2n = 0
     * 通过判别式: b^2-4ac 可以得出 8*n + 1 >0 有两个解, 去掉负数的
     * 求根公式: x = (-b ± sqrt(b^2-4ac))/ 2a
     * x = (sqrt(8*n + 1) - 1)/2 => sqrt((2n+1/4)) -1/2
     */
    public static int arrangeCoins(int n) {
        return (int) (Math.sqrt(2 * n + 0.25) - 0.5);
    }

    /**
     * 二分查找法
     */
    public int arrangeCoins1(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left; // 一半的长度, 是否满足1+2+3..+n的和
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
