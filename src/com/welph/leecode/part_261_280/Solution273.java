package com.welph.leecode.part_261_280;

import java.util.HashMap;
import java.util.Map;

/**
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 * 示例 1：
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * <p>
 * 示例 2：
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * <p>
 * 示例 3：
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * <p>
 * 示例 4：
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * <p>
 * 提示：
 * 0 <= num <= 2^31 - 1
 */
public class Solution273 {

    public static void main(String[] args) {
        /*System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1234567891));
        System.out.println(numberToWords(12345));*/
        System.out.println(numberToWords(20));
    }

    static String[] pos = {"Billion", "Million", "Thousand", "Hundred"};
    static int[] pov = {1000000000, 1000000, 1000, 100};
    static String[] ty = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String[] ten = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] sin = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder builder = new StringBuilder();
        numberToWords(num, 0, builder);
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    static void numberToWords(int num, int i, StringBuilder builder) {
        while (i < pov.length) {
            if (num >= pov[i]) {
                numberToWords(num / pov[i], i + 1, builder);
                num = num % pov[i];
                builder.append(pos[i]).append(" ");
            }
            i++;
        }
        if (num > 0) {
            if (num >= 20) {
                builder.append(ty[num / 10 - 2]).append(" ");
                num = num % 10;
                if (num > 0) {
                    builder.append(sin[num - 1]).append(" ");
                }
            } else if (num >= 10) {
                builder.append(ten[num - 10]).append(" ");
            } else {
                builder.append(sin[num - 1]).append(" ");
            }
        }
    }

}
