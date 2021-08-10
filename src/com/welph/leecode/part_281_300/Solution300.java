package com.welph.leecode.part_281_300;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * 进阶：
 * 你可以设计时间复杂度为 O(n^2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到O(n log(n)) 吗?
 */
public class Solution300 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * 获取到其中的子序列, 满足留空的情况下,保证数据的递增性
     * 动态规划 dp[i]
     * dp[i-1] 中最长递增子序列的最大值m
     * //若m<num[i] 则说明dp[i] = dp[i-1]+1
     * //若m>=num[i] 则说明dp[i] = dp[i-1]
     * 但此时需要记录是否在dp[i-1]中有一个次小的递增子序列且满足最大值小于num[i]
     * 若存在,则需要将m = num[i]
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        int max = dp[0] = 1;  //表示以num[i]作为最长上升子序列的结尾的长度
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
