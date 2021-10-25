package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.part_1_500.part_21_40.Solution33;
import com.welph.leecode.part_1_500.part_81_100.Solution81;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组[0,1,2,4,5,6,7] 可能变为[4,5,6,7,0,1,2] 。
 * 请找出其中最小的元素。
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数都是 唯一 的
 * nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 */
public class Solution153 {

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums));
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(nums1));
    }

    /**
     * 和{@link Solution81},{@link Solution33}
     * 二分查找,  这里还是唯一的 都不用判断重复.
     */
    public static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        int expect = nums[0]; //假定第一位为最小值, 二分法找比当前值还小的值
        while (l <= r) {
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
