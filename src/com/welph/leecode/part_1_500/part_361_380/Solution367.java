package com.welph.leecode.part_1_500.part_361_380;

/**
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * <p>
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 * <p>
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class Solution367 {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_61_80.Solution69}
     */
    public static boolean isPerfectSquare(int num) {
            long a = num;
            while (a * a > num) {
                a = (a + num / a) / 2;
            }
            return a * a == num;
    }
}
