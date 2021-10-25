package com.welph.leecode.part_1_500.part_401_420;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */
public class Solution413 {

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 动态规划:
     * dp[i] 表示到i处的个数 --- int sum 表示上一次的结果
     * delta 上一次的差值
     * expend 上一次的连接到最后节点i的最长等差数列长度
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int sum = 0;
        int delta = nums[1] - nums[0];
        int expend = 2;
        for (int i = 2; i < n; i++) {
            int d = nums[i] - nums[i - 1];
            if (d == delta) {
                sum += expend - 1;
                expend++;
            } else {
                delta = d;
                expend = 2;
            }
        }
        return sum;
    }
}
