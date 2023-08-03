package com.welph.leecode.part_1_500.part_41_60;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class Solution53 {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] nums) {
        int numsSize = nums.length;
        //dp[i]表示nums中以nums[i]结尾的最大子序和
        int[] dp = new int[numsSize];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < numsSize; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    /**
     * 贪心
     * 从左往右，如果sum小宇0，则重新开始找字串
     */
    public static int maxSubArray1(int[] nums) {
        int result = 0;
        int numsSize = nums.length;
        int sum = 0;
        for (int i = 0; i < numsSize; i++) {
            sum += nums[i];
            result = Math.max(result, sum);
            if (sum < 0) {
                sum = 0;
            }
        }

        return result;
    }


    /**
     * 分治,  最大字串一定在中心的右边或左边或中间
     */
    public static int maxSubArray2(int[] nums) {
        int result = 0;
        int numsSize = nums.length;
        result = maxSubArrayHelper(nums, 0, numsSize - 1);
        return result;
    }

    public static int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        int leftSum = maxSubArrayHelper(nums, left, mid);
        int rightSum = maxSubArrayHelper(nums, mid + 1, right);
        int midSum = findMaxCrossingSubarray(nums, left, mid, right);
        int result = Math.max(leftSum, rightSum);
        result = Math.max(result, midSum);
        return result;
    }

    public static int findMaxCrossingSubarray(int[] nums, int left, int mid, int right) {
        int leftSum = 0;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = 0;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        return (leftSum + rightSum);
    }
}
