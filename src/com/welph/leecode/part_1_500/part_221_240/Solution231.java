package com.welph.leecode.part_1_500.part_221_240;

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

    /* 官方题解 */
    // 依赖补码 若是2的幂次, 则n和n的补码(反码+1) 等于 n
    public static boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & -n) == n;
    }

    static final int BIG = 1 << 30;

    // 这也是一种方法
    /*
     * 给定的 323232 位有符号整数的范围内，最大的 2 的幂为 2^30=1073741824
     * 我们只需要判断 n 是否是 2^30 的约数即可。
     */
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && BIG % n == 0;
    }

}
