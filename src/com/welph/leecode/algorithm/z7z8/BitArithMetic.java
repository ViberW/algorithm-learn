package com.welph.leecode.algorithm.z7z8;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [位运算] 简单技巧
 * @since 2021/8/23 13:29
 */
public class BitArithMetic {

    /**
     * 两数之和, 不使用+-
     * {@link com.welph.leecode.part_361_380.Solution371}
     */
    public int add(int num1, int num2) {
        int a = num1 ^ num2;
        int b = num1 & num2;
        b = b << 1;
        return b == 0 ? a : add(a, b);
    }

    public static void swap(int a, int b) {
        a = a ^ b;//a=a^b
        b = a ^ b;//b=(a^b)^b=a^0=a
        a = a ^ b;//a=(a^b)^(a^b^b)=0^b=0
    }

    /**
     * 获取数值最接近的2的幂次方值
     */
    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    /**
     * 获取数字的 高位0 的个数
     */
    public static int numberOfLeadingZeros(int i) {
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        n -= i >>> 31;
        return n;
    }

    /**
     * 获取数字的 低位0 的个数
     */
    public static int numberOfTrailingZeros(int i) {
        int y;
        if (i == 0) return 32;
        int n = 31;
        y = i << 16;
        if (y != 0) {
            n = n - 16;
            i = y;
        }
        y = i << 8;
        if (y != 0) {
            n = n - 8;
            i = y;
        }
        y = i << 4;
        if (y != 0) {
            n = n - 4;
            i = y;
        }
        y = i << 2;
        if (y != 0) {
            n = n - 2;
            i = y;
        }
        return n - ((i << 1) >>> 31);
    }

}
