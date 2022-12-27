package com.welph.leecode.part_500_1000.part_581_600;

import java.util.Arrays;

/**
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution594 {

    public static void main(String[] args) {
        System.out.println(findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(findLHS(new int[]{1, 2, 3, 4}));
        System.out.println(findLHS(new int[]{1, 1, 1, 1}));
        System.out.println(findLHS(new int[]{1, 2, 2, 1}));
        System.out.println(findLHS(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17}));
    }

    public static int findLHS(int[] nums) {
        int max = 0;
        Arrays.sort(nums);
        int last = 0;
        int expectValue = nums[last] + 1;
        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > expectValue) {
                if (pre != last) {
                    max = Math.max(max, i - last);
                }
                if (nums[pre] + 1 < nums[i]) {
                    last = i;
                } else {
                    last = pre;
                }
                expectValue = nums[last] + 1;
            }
            if (nums[i] != nums[i - 1]) {
                pre = i;
            }
        }
        if (pre != last) {
            max = Math.max(max, nums.length - last);
        }
        return max;
    }
}
