package com.welph.leecode.part_1_500.part_161_180;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class Solution172 {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));
    }

    //相当于是计算 10的个数, 以及5的个数
    public static int trailingZeroes(int n) {
        int target = 0;
        while (n > 0) {
            target += n / 5;
            n = n / 5;
        }
        return target;
    }
}
