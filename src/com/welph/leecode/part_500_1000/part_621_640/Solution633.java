package com.welph.leecode.part_500_1000.part_621_640;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * <p>
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * <p>
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * <p>
 * 提示：
 * 0 <= c <= 2^31 - 1
 */
public class Solution633 {

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(5));
        System.out.println(judgeSquareSum(0));
        System.out.println(judgeSquareSum(2147483646));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_1_20.Solution01}
     * 二分查找 类似两数之和,并且在升序过程中
     */
    public static boolean judgeSquareSum(int c) {
        int sqrt = (int) Math.sqrt(c);
        int mid = c / 2;
        int target;
        int sqrt1;
        for (int i = sqrt; i >= 0; i--) {
            //二分查找目标值为target的 c- sqrt^2
            target = c - i * i;
            if (target > mid) {
                return false;
            }
            sqrt1 = (int) Math.sqrt(target);
            if (sqrt1 * sqrt1 == target) {
                return true;
            }
        }
        return false;
    }
}
