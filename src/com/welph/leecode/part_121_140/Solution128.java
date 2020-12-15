package com.welph.leecode.part_121_140;

import java.util.*;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为O(n) 的解决方案吗？
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 提示：
 * <p>
 * 0 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution128 {

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums2));
        int[] nums3 = {-7, -1, 3, -9, -4, 7, -3, 2, 4, 9, 4, -9, 8, -7, 5, -1, -7};
        System.out.println(longestConsecutive(nums3));
        int[] nums4 = {-2, -3, -3, 7, -3, 0, 5, 0, -8, -4, -1, 2};
        System.out.println(longestConsecutive(nums4));
    }

    /**
     * 并查集
     * --每次想办法进行合并
     */
    public static int longestConsecutive(int[] nums) {
        //每次查询num-1和num+1是否存在,存在则保证信息
        int max = 0;
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        Map.Entry<Integer, Integer> entry;
        int cur;
        for (int num : nums) {
            entry = tree.ceilingEntry(num + 1);
            cur = num;
            if (entry != null && entry.getKey() == num + 1) {
                tree.remove(entry.getKey());
                cur = entry.getValue();
            }
            entry = tree.floorEntry(num);
            if (entry == null || entry.getValue() < num - 1) {
                tree.put(num, cur);
                max = Math.max(max, cur - num + 1);
            } else {
                cur = Math.max(entry.getValue(), cur);
                tree.put(entry.getKey(), cur);
                max = Math.max(max, cur - entry.getKey() + 1);
            }
        }
        return max;
    }

}

