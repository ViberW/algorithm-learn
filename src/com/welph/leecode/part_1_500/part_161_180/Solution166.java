package com.welph.leecode.part_1_500.part_161_180;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * <p>
 * 示例 1：
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * <p>
 * 示例 2：
 * 输入：numerator = 2, denominator = 1
  * 输出："2"
 * <p>
 * 示例 3：
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * <p>
 * 示例 4：
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * <p>
 * 示例 5：
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 * <p>
 * 提示：
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class Solution166 {

    public static void main(String[] args) {
        /*System.out.println(fractionToDecimal(1, 2));
        System.out.println(fractionToDecimal(2, 1));
        System.out.println(fractionToDecimal(2, 3));
        System.out.println(fractionToDecimal(4, 333));
        System.out.println(fractionToDecimal(1, 5));*/
        System.out.println(fractionToDecimal(-1, -2147483648));
    }

    //能单纯的转化为负数, 否则有int溢出
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        boolean negative = numerator != 0 && (numerator < 0 ^ denominator < 0);
        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        long origin = n / d;
        long v = n % d;
        if (v != 0) {
            Map<Long, Integer> index = new HashMap<>();
            int i = 0;
            boolean contain = false;
            while (v != 0 && v < d) {
                if (index.containsKey(v)) {
                    contain = true;
                    break;
                }
                index.put(v, i++);
                v = v * 10;
                sb.append(v / d);
                v = v % d;
            }
            if (contain) {
                sb.insert(index.get(v), "(").append(")");
            }
            sb.insert(0, origin + ".");
        } else {
            sb.append(origin);
        }
        if (negative) {
            sb.insert(0, "-");
        }
        return sb.toString();
    }

    /* 官方题解 和我的解法差不多*/
    public String fractionToDecimal2(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());

        return sb.toString();
    }
}
