package com.welph.leecode.part_141_160;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 * <p>
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 * <p>
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 * 这道题是 寻找旋转排序数组 {@link Solution153}中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 */
public class Solution154 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        System.out.println(findMin(nums));
        int[] nums1 = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1};
        System.out.println(findMin(nums1));
    }

    //这里就要考虑重复元素了
    public static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        int expect = nums[0];
        while (l <= r) {
            while (l < r && nums[l] == nums[l + 1]) ++l;
            while (l < r && nums[r] == nums[r - 1]) --r;
            mid = (l + r) / 2;
            if (nums[mid] < expect) {
                expect = nums[mid];
                r = mid - 1;
            } else if (nums[mid] >= expect) {
                l = mid + 1;
            }
        }
        return expect;
    }
}
