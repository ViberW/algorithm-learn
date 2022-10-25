package com.welph.leecode.part_500_1000.part_501_520;

/**
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 * <p>
 * 示例 2:
 * 输入: num = -7
 * 输出: "-13"
 * <p>
 * 提示：
 * -10^7 <= num <= 10^7
 */
public class Solution504 {

    public static void main(String[] args) {
        System.out.println(convertToBase7(9));
        System.out.println(convertToBase7(100));
        System.out.println(convertToBase7(-10));
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toBinaryString(10));
    }

    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean navigate = num < 0;
        if (navigate) {
            num = -num;
        }
        //每次除7余
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            builder.insert(0, num % 7);
            num /= 7;
        }
        if (navigate) {
            builder.insert(0, "-");
        }
        return builder.toString();
    }
}
