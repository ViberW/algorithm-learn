package com.welph.leecode.part_221_240;

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * 示例1:
 * 输入: 1
 * 输出: true
 * 解释: 2^0= 1
 * <p>
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 2^4= 16
 * <p>
 * 示例 3:
 * 输入: 218
 * 输出: false
 */
public class Solution231 {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(-2147483648));
    }

    public static boolean isPowerOfTwo(int n) {
        return n != 0 && n > Integer.MIN_VALUE && (n & (n - 1)) == 0;
    }
}
