package com.welph.leecode.part_21_40;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 */
public class Solution29 {

    public static void main(String[] args) {
        System.out.println(divide(1026117192, -874002063));
        System.out.println();
        System.out.println(divide(1, -1));
    }

    private static int limit = 1 << 30;

    /**
     * 思路： 尽量减少integer.min_value的影响，所以根据dividend和divisor是否相同正负，进行加减操作
     *    保证之后的dividend值的绝对值一定小于integer.max_value ，之后可以进行正数转换，
     *    在item的方法中通过二进制位移不断对比操作
     */
    public static int divide(int dividend, int divisor) {
        int positive = 0;
        if (dividend == 0) {
            return 0;
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == divisor ? 1 : 0;
        }
        if (dividend < 0) {
            positive++;
        }
        if (divisor < 0) {
            positive++;
        }
        int suf = 0;
        if (positive == 1) {
            dividend = dividend + divisor;
            suf = (dividend ^ divisor) < 0 ? -1 : 0;
        } else {
            dividend = dividend - divisor;
            suf = (dividend ^ divisor) < 0 ? 0 : 1;
        }
        if (dividend == 0) {
            return positive == 1 ? -1 : 1;
        }
        if (dividend < 0) {
            dividend = -dividend;
        }
        if (divisor < 0) {
            divisor = -divisor;
        }
        int result = divideItem(dividend, divisor);
        return positive == 1 ? -result + suf : result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + suf;
    }

    public static int divideItem(int dividend, int divisor) {
        //计算divisot和2的移位31相差多少位
        if (divisor > dividend) {
            return 0;
        } else {
            int k = divisor;
            int j = 0;
            while (dividend >= k) {
                j++;
                if (k >= limit) {
                    break;
                }
                k = k << 1;
            }
            int dis = dividend - (divisor << (j - 1));
            if (dis < divisor) {
                return 1 << (j - 1);
            } else {
                return (1 << (j - 1)) + divideItem(dis, divisor);
            }
        }
    }

}
