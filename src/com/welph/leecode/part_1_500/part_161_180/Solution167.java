package com.welph.leecode.part_1_500.part_161_180;

import com.welph.leecode.part_1_500.part_1_20.Solution01;

import java.util.Arrays;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class Solution167 {

    public static void main(String[] args) {
        /*int[] numbers = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(numbers, 9)));

        int[] numbers1 = {-1, 0};
        System.out.println(Arrays.toString(twoSum(numbers1, -1)));*/

        int[] numbers2 = {-3, 3, 4, 90};
        System.out.println(Arrays.toString(twoSum(numbers2, 0)));
    }

    /**
     * 双指针  已经排序好的数据. {@link Solution01}
     * 由于这里排好序, 可以使用二分查找.
     */
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        int val;
        while (l < r) {
            val = numbers[l] + numbers[r];
            if (val == target) {
                return new int[]{l + 1, r + 1};
            } else if (val > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }
}
