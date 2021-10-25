package com.welph.leecode.part_1_500.part_221_240;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个大小为n的整数数组，找出其中所有出现超过⌊ n/3 ⌋次的元素。
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * <p>
 * 示例1：
 * <p>
 * 输入：[3,2,3]
 * 输出：[3]
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 */
public class Solution229 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println(majorityElement(nums));

        int[] nums1 = {3, 2, 3};
        System.out.println(majorityElement(nums1));

        int[] nums2 = {1};
        System.out.println(majorityElement(nums2));
    }

    //moore算法  -- 摩尔算法
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        //根据情况, 最多只能存在两个超过
        int vm = 0, vn = 0;
        int cm = 0, cn = 0;
        for (int num : nums) {
            if (vm == num) {
                cm++;
            } else if (vn == num) {
                cn++;
            } else if (cm == 0) {
                vm = num;
                cm++;
            } else if (cn == 0) {
                vn = num;
                cn++;
            } else {
                cm--;
                cn--;
            }
        }
        //此时找到了最多的两个数据信息
        cm = 0;
        cn = 0;
        for (int num : nums) {
            if (num == vm) {
                cm++;
            } else if (num == vn) {
                cn++;
            }
        }
        int len = nums.length / 3;
        if (cm > len) {
            result.add(vm);
        }
        if (cn > len) {
            result.add(vn);
        }
        return result;
    }
}
