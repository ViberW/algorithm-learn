package com.welph.leecode.part_500_1000.part_581_600;

/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
public class Solution581 {

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(findUnsortedSubarray(new int[]{1, 2, 3, 4, 5}));
        System.out.println(findUnsortedSubarray(new int[]{1}));
        System.out.println(findUnsortedSubarray(new int[]{2, 3, 3, 2, 4}));
        System.out.println(findUnsortedSubarray(new int[]{1, 2, 4, 5, 3}));
    }

    /**
     * 出现过的过程中, 出现了递减的变化 就把它放入进去,
     * 维持一个单调增栈, 用于查询对应值的位置. 这个范围内的需要加入进来
     */
    public static int findUnsortedSubarray(int[] nums) {
        int l = 0;
        int r = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max) {
                //向前找到最小值
                for (; l >= 0; l--) {
                    if (nums[i] >= nums[l]) {
                        break;
                    }
                }
                r = i;
            } else if (l == r) {
                l++;
                r++;
            }
            max = Math.max(max, nums[i]);
        }
        return r - l;
    }
}
