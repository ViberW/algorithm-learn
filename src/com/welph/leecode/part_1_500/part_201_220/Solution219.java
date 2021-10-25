package com.welph.leecode.part_1_500.part_201_220;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，
 * 使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k。
 * <p>
 * 示例1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * <p>
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class Solution219 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
        System.out.println(containsNearbyDuplicate(nums, 2));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> exists = new HashMap<>();
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            if (exists.containsKey(num) && i - exists.get(num) <= k) {
                return true;
            }
            exists.put(num, i);
        }
        return false;
    }
}
