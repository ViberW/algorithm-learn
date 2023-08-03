package com.welph.leecode.part_1_500.part_61_80;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class Solution66 {

    public static void main(String[] args) {
        int[] digits = {4, 3, 2, 1};

        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        int v = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            v = digits[i] + v;
            if (v % 10 == 0) {
                digits[i] = 0;
                v = 1;
            } else {
                digits[i] = v;
                v = 0;
                break;
            }
        }
        if (v > 0) {
            int[] result = new int[digits.length + 1];
            result[0] = v;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }
        return digits;
    }
}
