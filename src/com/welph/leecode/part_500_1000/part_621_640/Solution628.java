package com.welph.leecode.part_500_1000.part_621_640;

import java.util.Arrays;

/**
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 * <p>
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 * <p>
 * 提示：
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 */
public class Solution628 {

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{1, 2, 3, 4}));
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3],
                nums[0] * nums[1] * nums[nums.length - 1]);
    }
}
