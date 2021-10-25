package com.welph.leecode.part_401_420;

/**
 * 给你一个 只包含正整数 的 非空 数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Solution416 {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    /**
     * 问题就变成了如何选择, 能够得到half的容量的子集数组 (01背包问题)
     * dp[i] 表示对于容量为i时,能够有多少种方式装载
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >> 1;
        int[] dp = new int[half + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = half; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[half] != 0;
    }
}
