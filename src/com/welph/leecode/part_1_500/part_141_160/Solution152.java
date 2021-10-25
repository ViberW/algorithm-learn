package com.welph.leecode.part_1_500.part_141_160;

/**
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Solution152 {

    public static void main(String[] args) {
        int[] nums = {-2, 0, -1};
        int[] nums1 = {2, 3, -2, 4};
        System.out.println(maxProduct1(nums));
        System.out.println(maxProduct1(nums1));

    }

    /**
     * 根据下面, 换个思路, 只需要知道 dp[i] = dp[i-1] * num[i]
     * dp[i-1] 应该有到 i-1的最大值, 到i-1的最小值
     * 这个方法就很好的解决的. 本质上也是动态规划
     */
    public static int maxProduct1(int[] nums) {
        int len = nums.length;

        int maxNum = nums[0];
        int minNum = maxNum;

        int max = maxNum;
        int num;
        for (int i = 1; i < len; i++) {
            num = nums[i];
            maxNum = maxNum * num;
            minNum = minNum * num;
            int tmp = maxNum;
            maxNum = Math.max(Math.max(tmp, minNum), num);
            minNum = Math.min(Math.min(tmp, minNum), num);
            max = Math.max(max, maxNum);
        }
        return max;
    }

    /**
     * dp[j][i] = dp[j][i-1] * nums[i]
     */
    //该子数组中至少包含一个数字  --- 若nums长度很大 那么dp就很大  -- 超出内存了
    public static int maxProduct(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        int max = nums[0];
        dp[0][0] = nums[0];
        int num;
        int val;
        for (int i = 1; i < len; i++) {
            num = nums[i];
            dp[i][i] = num;
            max = Math.max(max, num);
            for (int j = 0; j < i; j++) {
                val = dp[j][i - 1] * num;
                dp[j][i] = val;
                max = Math.max(max, val);
            }
        }
        return max;
    }
}
