package com.welph.leecode.part_1_500.part_381_400;

/**
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 位数字。
 * 注意：n 是正数且在 32 位整数范围内（n < 2^31）。
 * <p>
 * 示例 1：
 * 输入：3
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 */
public class Solution400 {

    public static void main(String[] args) {
        // System.out.println(findNthDigit(3));
        // System.out.println(findNthDigit(11));
        System.out.println(findNthDigit(149));
        System.out.println(findNthDigit(1000000000));
    }

    /**
     * 题目说明:
     * n=11, 代表字符串是1234567891011.....字符串第11个 --> 0
     * n=9, 代表字符串是123456789.....字符串第9个-->9
     * -----------------------------
     * 第几个数字，只需要超出跨度n-left，除以cnt得到在100到999的
     * 跨度，+base得到第几个数字，数字的第几位数bit只需要求出跨度n-left，%位数cnt
     * 就可以得到第几位
     * ------------
     * n=11，在10到189，第几个数字？（11-10）/2+10=10第10个数也就是10，第几位？（11-10）%2==1第1位也就是0
     * n=149，在10到189，（149-10）/2+10=79第79个数字也就是79，（149-10）%2==1第1位也就是9
     * int num=(n-left)/cnt+base;
     * int bit=(n-left)%cnt;
     * -----------> num[bit] --看了下题解 来了点思路
     */
    public static int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        long left = 1;
        long right = 9;
        long cnt = 1;
        long base = 1;
        // 找到对应的n所处的最大和最小值的范围
        // 1 9
        // 10 189  --> 10 一直到99 期间一共有90个数字 180位数
        while (!(left <= n && n <= right)) {
            left = right + 1;
            cnt++; //代表当前范围每个数字一共由多少个位数组成
            base *= 10;
            right = right + cnt * 9 * base;
        }
        //对应的数字是多少
        long num = (n - left) / cnt + base;
        //对应的数字的第几位
        int bit = (int) ((n - left) % cnt);
        return String.valueOf(num).charAt(bit) - '0';
    }
}
