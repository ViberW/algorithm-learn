package com.welph.leecode.part_1_20;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * @author: Admin
 * @date: 2019/5/5
 * @Description: {相关描述}
 */
public class Solution01 {

    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] nums, int target) {
        //思路:一次遍历hash-> 目标值减去当前值 放入hashMap中 在之后用当前值相等于目标值即可
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.containsKey(nums[i])) {
                return new int[]{cache.get(nums[i]), i};
            }
            cache.put(target - nums[i], i);
        }
        return null;
    }
}
