package com.welph.leecode.part_41_60;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Solution43 {

    public static void main(String[] args) {
        String num1 = "5";
        String num2 = "2";
        System.out.println(multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        //从小的开始,认为num2小
        int len1 = num1.length();
        int len2 = num2.length();
        int[] arr = new int[len1 + len2 + 1];
        int c1;
        int j;
        int i;
        int l;
        int r;
        for (i = len1 - 1, l = 0; i >= 0; i--, l++) {
            c1 = num1.charAt(i) - '0';
            for (j = len2 - 1, r = 0; j >= 0; j--, r++) {
                arr[l + r] = arr[l + r] + (c1 * (num2.charAt(j) - '0'));
            }
        }
        int size = arr.length;
        int k;
        int current;
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < size; i++) {
            current = arr[i];
            sb.insert(0, current % 10);
            k = 1;
            while ((current = current / 10) > 0) {
                arr[i + k] = arr[i + k] + (current % 10);
                k++;
            }
        }
        while (size-- > 0) {
            if (arr[size] != 0) {
                break;
            }
        }
        return size == -1 ? "0" : sb.substring(arr.length - size - 1);
    }
}
