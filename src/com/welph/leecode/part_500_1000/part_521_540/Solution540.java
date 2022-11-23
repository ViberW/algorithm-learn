package com.welph.leecode.part_500_1000.part_521_540;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class Solution540 {

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate1(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate1(new int[]{3, 3, 7, 7, 10, 11, 11}));
        System.out.println(singleNonDuplicate1(new int[]{3, 3, 7, 7, 10, 10, 11, 11, 12}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_241_260.Solution260 }
     */
    public static int singleNonDuplicate1(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }

    public static int singleNonDuplicate(int[] nums) {
        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i - 1] != nums[i]) {
                return nums[i - 1];
            }
        }
        return nums[nums.length - 1];
    }
}
