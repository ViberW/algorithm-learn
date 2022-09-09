package com.welph.leecode.part_1_500.part_481_500;

/**
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * <p>
 * 示例 2:
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i]不是0就是1.
 */
public class Solution485 {

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        for (int num : nums) {
            if (num == 0) {
                max = Math.max(max, cur);
                cur = 0;
            } else {
                cur++;
            }
        }
        max = Math.max(max, cur);
        return max;
    }
}
