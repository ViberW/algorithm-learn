package com.welph.leecode.part_161_180;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class Solution169 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement1(nums));

        int[] nums1 = {3, 3, 4};
        System.out.println(majorityElement1(nums1));
    }

    //摩尔投票法, 因为大于一半, 所以一定能够保留在最后
    public static int majorityElement1(int[] nums) {
        int count = 0;
        int target = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            } else {
                count--;
                if (count == 0) { //应为数量大于一半, 所以在x到i段 为上一次的目标值的一半. 那么接下的结果一定在一半之内;
                    target = nums[i + 1];
                }
            }
        }
        return target;
    }

    //找出大于一半的数据信息, 若是不考虑空间,额外map保存数量. --速度有些慢了.
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int max = 0;
        int target = 0;
        int half = nums.length / 2;
        for (int num : nums) {
            Integer c = count.get(num);
            if (null == c) {
                c = 1;
            } else {
                c += 1;
            }
            count.put(num, c);
            if (c > max || c > half) {
                target = num;
                max = c;
            }
        }
        return target;
    }
}
