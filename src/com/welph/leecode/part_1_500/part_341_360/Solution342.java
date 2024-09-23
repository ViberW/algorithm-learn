package com.welph.leecode.part_1_500.part_341_360;

/**
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 * <p>
 * 示例 1：
 * 输入：n = 16
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：true
 * <p>
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * <p>
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class Solution342 {

    public static void main(String[] args) {
        System.out.println(Integer.numberOfTrailingZeros(60)); //1000
        System.out.println(isPowerOfFour(32));
        System.out.println(isPowerOfFour(16));
        System.out.println(isPowerOfFour(5));
        System.out.println(isPowerOfFour2(64));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_221_240.Solution231}
     * {@link com.welph.leecode.part_1_500.part_321_340.Solution326}
     * 若为4的幂次方, 则一定是2的幂次方, 且幂次为2的倍数
     */
    public static boolean isPowerOfFour(int n) {
        if (n != 0 && n > Integer.MIN_VALUE && (n & (n - 1)) == 0) { //属于2的幂次方
            //计算幂次是多少.
            //查看i是否为2的倍数
            return Integer.numberOfTrailingZeros(n) % 2 == 0;
        }
        return false;
    }

    /*官方题解 */
    public static boolean isPowerOfFour2(int n) {
        //这里0xaaaaaaab 二进制为 10101010101010101010101010101011 , 相当于时若n的二进制奇数位为1,则代表4的幂次
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaab) == 0;
    }

    /*
     * 若 n 是 4 的幂，那么它可以表示成 4^x 的形式，我们可以发现它除以 3 的余数一定为 1
     * 若 n 不是 4 的幂而是2的幂，那么它可以表示成 那么它可以表示成 4^x * 2 的形式，此时它除以 3 的余数一定为 2。
     * ------------------------
     * 通过 n 除以 3 的余数是否为 1 来判断 n 是否是 4 的幂
     */
    public boolean isPowerOfFour3(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

}
