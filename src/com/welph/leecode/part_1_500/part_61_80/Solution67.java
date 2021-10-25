package com.welph.leecode.part_1_500.part_61_80;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Solution67 {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }

    public static String addBinary(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        if (c2.length > c1.length) {
            char[] temp = c2;
            c2 = c1;
            c1 = temp;
        }
        int spec = 0;
        for (int i = c1.length - 1, j = c2.length - 1; i >= 0; i--, j--) {
            spec = (j >= 0 ? c2[j] - '0' : 0) + (c1[i] - '0') + spec;
            if (spec % 2 > 0) {
                c1[i] = '1';
            } else {
                c1[i] = '0';
            }
            spec = spec / 2;
        }
        String s = String.valueOf(c1);
        if (spec > 0) {
            s = spec + s;
        }
        return s;
    }
}
