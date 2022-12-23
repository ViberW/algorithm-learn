package com.welph.leecode.part_500_1000.part_561_580;

/**
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 * 示例 1:
 * 输入: n = "123"
 * 输出: "121"
 * <p>
 * 示例 2:
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 * <p>
 * 提示:
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 10^18 - 1] 范围内的整数
 */
public class Solution564 {

    public static void main(String[] args) {
        System.out.println(nearestPalindromic("9")); //8
        System.out.println(nearestPalindromic("11")); //9
        System.out.println(nearestPalindromic("100000")); //这里的回文结果是99999 而不是100001
        System.out.println(nearestPalindromic("99999")); //这里的回文结果是100001 最近的就是它了
        System.out.println(nearestPalindromic("121"));
        System.out.println(nearestPalindromic("321"));
        System.out.println(nearestPalindromic("11111"));//11011
    }

    /**
     * 返回最近且最小的回文串
     * 需要最小的  说明需要从后往前寻找
     */
    public static String nearestPalindromic(String n) {
        char[] chars = n.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {

        }
        return null;
    }
}
