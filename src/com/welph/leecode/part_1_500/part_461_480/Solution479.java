package com.welph.leecode.part_1_500.part_461_480;

/**
 * 给定一个整数 n ，返回 可表示为两个 n位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 * <p>
 * 示例 1:
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * <p>
 * 示例 2:
 * 输入： n = 1
 * 输出： 9
 * <p>
 * 提示:
 * 1 <= n <= 8
 */
public class Solution479 {

    public static void main(String[] args) {
        System.out.println(largestPalindrome(5));

    }

    /**
     *
     */
    public static int largestPalindrome(int n) {
        if (n == 1) {// 一个最大值就是9
            return 9;
        }
        int max = (int) (Math.pow(10, n) - 1); //左半部分的
        int ret = 0;
        Label:
        for (int i = max; i > 0; i--) {
            //枚举
            long p = i;
            //构建右半部分
            for (long j = p; j > 0; j /= 10) {
                p = p * 10 + j % 10;
            }
            //构建出了一个回文串
            //不断的找一个值,
            for (long k = max; k * k >= p; k--) { //注意: 这里面的k需要用long来表示, 以为k*k可能会超过integer的最大值
                if (p % k == 0) {
                    ret = (int) (p % 1337);
                    break Label;
                }
            }
        }
        return ret;
    }
}
