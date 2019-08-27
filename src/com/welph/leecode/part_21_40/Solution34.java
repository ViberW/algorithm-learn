package com.welph.leecode.part_21_40;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class Solution34 {


    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 6;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = search(nums, 0, nums.length - 1, target, false);
        result[1] = search(nums, 0, nums.length - 1, target, true);
        return result;
    }

    public static int search(int[] nums, int l, int r, int target, boolean positive) {
        int mid;
        boolean exist = false;
        while (l <= r) {
            mid = (l + r) / 2;
            if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            } else {
                exist = true;
                if (positive) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return exist ? (l + r) / 2 + (positive || r < 0 ? 0 : 1) : -1;
    }
}
