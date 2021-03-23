package com.welph.leecode.part_241_260;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 示例 1：
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * <p>
 * 示例 2：
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * <p>
 * 示例 3：
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * <p>
 * 提示：
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 */
public class Solution260 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }

    /**
     * //todo good
     * 异或为0 {@link com.welph.leecode.part_121_140.Solution136}
     * {@link com.welph.leecode.part_121_140.Solution137}
     * <p>
     * 通过分组. 将两个不同的值分别放到两个不同的数组中
     * 因为 所有值异或值的x  = a^b
     * 而x的二进制中, 必定存在一个位为1. 代表在该位上a和b不相同
     * 这样两个不同的组中就能分别拿到多余的值
     */
    public static int[] singleNumber(int[] nums) {
        int val = 0;
        for (int num : nums) {
            val ^= num;
        }
        int move = 1;
        while ((val & move) == 0) {
            move <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((move & num) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

}
