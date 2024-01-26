package com.welph.leecode.part_1_500.part_161_180;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的
 */
public class Solution162 {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(findPeakElement(nums));
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(findPeakElement(nums1));
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums2));
    }

    //O(logN) 时间复杂度 说明不能遍历
    // 一般是二分查找
    public static int findPeakElement(int[] nums) {
        int max = nums.length - 1;
        int l = 0;
        int r = max;
        boolean lb;
        while (l <= r) {
            int mid = (l + r) / 2;
            if ((lb = mid == 0 || nums[mid] > nums[mid - 1])
                    & (mid == max || nums[mid] > nums[mid + 1])) {
                return mid;
            }
            if (mid == 0) {
                l = mid + 1;
            } else if (mid == max) {
                r = mid - 1;
            } else {
                if (lb) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /*官方题解 第一种解法, 并不意味者比二分法好*/
    public int findPeakElement1(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    public int findPeakElement2(int[] nums) {
        int n = nums.length;
        int idx = (int) (Math.random() * n);

        while (!(compare(nums, idx - 1, idx) < 0 && compare(nums, idx, idx + 1) > 0)) {
            if (compare(nums, idx, idx + 1) < 0) {
                idx += 1;
            } else {
                idx -= 1;
            }
        }
        
        return idx;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }
}
