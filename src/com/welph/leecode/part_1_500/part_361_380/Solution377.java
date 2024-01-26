package com.welph.leecode.part_1_500.part_361_380;

import java.util.Arrays;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * <p>
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * <p>
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class Solution377 {

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(combinationSum4(new int[]{9}, 3));
    }

    /**
     * 假设有N位, 每个位置的数据都是从nums [0~n]  元素互不相同
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return combinationSum4X(nums, target, dp);
    }

    static int combinationSum4X(int[] nums, int target, int[] dp) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int sum = 0;
        for (int num : nums) {
            if (target == num) {
                sum++;
            } else if (target > num) {
                sum += combinationSum4X(nums, target - num, dp);
            }
        }
        dp[target] = sum;
        return sum;
    }
    
    /* 官方题解 */
    //动态规划 和上面方法原理一样
    public int combinationSum4_2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
