package com.welph.leecode.part_1_500.part_221_240;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 2 * 10^9
 */
public class Solution233 {

    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
        System.out.println(countDigitOne(20));
        System.out.println(countDigitOne(10));
        System.out.println(countDigitOne(1410065408));
    }

    public static int countDigitOne(int n) {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(1, 0);
        int i = 1;
        int total = 0;
        int limit = Integer.MAX_VALUE / 2;
        for (; i <= n; i *= 10) {
            counts.put(i * 10, 10 * counts.get(i) + i);
            /**
             * 余数 : 判断首位为1的数值
             * 除数 : 判断当前的能够乘以多少个上一次的大小
             */
            int a = n % i;
            int b = (n / i) % 10;
            if (b > 1) {
                total += i;
            } else if (b == 1) {
                total += a + 1;
            }
            total += b * counts.get(i);
            if (i * 2 > limit) {
                break;
            }
        }
        return total;
    }
}
