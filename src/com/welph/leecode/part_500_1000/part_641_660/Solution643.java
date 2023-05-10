package com.welph.leecode.part_500_1000.part_641_660;

/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 示例 2：
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution643 {

    public static void main(String[] args) {
        System.out.println(findMaxAverage(
                new int[]{1, 12, -5, -6, 50, 3},
                4
        ));
    }

    /**
     * 滑动窗口
     */
    public static double findMaxAverage(int[] nums, int k) {
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += nums[i];
        }
        int maxValue = total;
        int n = nums.length;
        for (int i = k; i < n; i++) {
            total += nums[i] - nums[i - k];
            maxValue = Math.max(maxValue, total);
        }
        return maxValue * 1.0 / k;
    }
}
