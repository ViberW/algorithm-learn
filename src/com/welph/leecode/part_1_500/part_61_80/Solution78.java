package com.welph.leecode.part_1_500.part_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Solution78 {

    public static void main(String[] args) {
        int[] nums = { 3, 1, 2 };
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        subsets(subsets, nums, 0, nums.length - 1);
        return subsets;
    }

    public static void subsets(List<List<Integer>> subsets, int[] nums, int index, int end) {
        if (index > end) {
            return;
        }
        List<Integer> integers;
        int size = subsets.size();
        for (int j = 0; j < size; j++) {
            integers = new ArrayList<>(subsets.get(j));
            integers.add(nums[index]);
            subsets.add(integers);
        }
        subsets(subsets, nums, index + 1, end);
    }

    /* 通过二进制来表示数值的使用 */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
