package com.welph.leecode.part_1_500.part_121_140;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class Solution136 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        System.out.println(singleNumber(nums));
        int[] nums1 = {7, 4, 1, 2, 1, 2, 7};
        System.out.println(singleNumber1(nums1));
    }

    /**
     * 线性时间复杂度O(n)
     * <p>
     * 若是不使用额外的空间...  使用位运算  任何数同0异或都是其本身, 最后两者的异或为0
     */
    public static int singleNumber1(int[] nums) {
        int val = 0;
        for (int num : nums) {
            val ^= num;
        }
        return val;
    }

    /**
     * 若是考虑额外空间, 使用哈希表存储就可了
     */
    public static int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.iterator().next();
    }
}
