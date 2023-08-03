package com.welph.leecode.part_1_500.part_41_60;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Solution55 {

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump2(nums));
    }

    public static boolean canJump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int len = nums.length - 1;
        for (int i = 0; i <= len; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i > end) {
                return false;
            }
            if (i == end) {
                end = maxPosition;
                if (end >= len) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean jump2(int[] nums) {
        int position = nums.length - 1;
        while (position != 0) reset:{
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i;
                    break reset;
                }
            }
            return false;
        }
        return true;
    }
}
