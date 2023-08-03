package com.welph.leecode.part_1_500.part_81_100;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组[0,0,1,2,2,5,6]可能变为[2,5,6,0,0,1,2])。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回true，否则返回false。
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */

/**
 * {@link com.welph.leecode.part_1_500.part_21_40.Solution33}
 */
public class Solution81 {

    public static void main(String[] args) {
        int[] nums = {3, 1};
        System.out.println(search(nums, 3));
    }

    public static boolean search(int[] nums, int target) {
        //二分法查找
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {
            //需要直接便利去重复
            while (l < r && nums[l] == nums[l + 1]) ++l;
            while (l < r && nums[r] == nums[r - 1]) --r;
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (target > nums[mid]) {
                if (nums[mid] > nums[r] || target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (nums[mid] < nums[l] || target >= nums[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return false;
    }
}
