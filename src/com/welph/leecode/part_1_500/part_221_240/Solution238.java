package com.welph.leecode.part_1_500.part_221_240;

import java.util.Arrays;

/**
 * 给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
 * <p>
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class Solution238 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    //不能使用[除法]
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        //左右两次乘积
        int multi = 1;
        for (int i = 0; i < len; i++) {
            res[i] = multi;
            multi *= nums[i];
        }

        multi = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= multi;
            multi *= nums[i];
        }
        return res;
    }
}
