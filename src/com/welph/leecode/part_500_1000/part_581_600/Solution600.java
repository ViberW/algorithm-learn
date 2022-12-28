package com.welph.leecode.part_500_1000.part_581_600;

/**
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * <p>
 * 示例 1:
 * 输入: n = 5
 * 输出: 5
 * 解释:
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * <p>
 * 示例 2:
 * 输入: n = 1
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: n = 2
 * 输出: 3
 * <p>
 * 提示:
 * 1 <= n <= 10^9
 */
public class Solution600 {

    public static void main(String[] args) {
        System.out.println(findIntegers(1));//2
        System.out.println(findIntegers(2));//3
        System.out.println(findIntegers(5));//5
        System.out.println(findIntegers(22));//13
        System.out.println(findIntegers1(22));//13
        System.out.println(findIntegers(8));//6
        System.out.println(findIntegers(6));//5
        System.out.println(Integer.toBinaryString(1000));//1111101000
        System.out.println(findIntegers(1000));//142
        System.out.println(findIntegers1(1000));//144
    }

    /**
     * 动态规划
     * dp[i] 若是为最后一位为0, 则考虑  dp[i>>1]   00  01
     * 若是为最后一位为1, 则考虑  dp[i>>2]  101 考虑的是这些.
     * ----------------------------------------------
     * 11111 => 1111 + 10111
     * => dp[i] = dp[i-1] + dp[i-2]
     */
    public static int findIntegers(int n) {
        //找到n的最大值
        int maxBit = 31 - Integer.numberOfLeadingZeros(n);
        int[] dp = new int[maxBit + 2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {//111
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        //现在逐步考虑.
        int result = 0;
        boolean preOne = false;
        for (int bit = maxBit; bit >= 0; bit--) {
            if (((n >> bit) & 1) == 1) {
                result += dp[bit];
                if (preOne) { //这里考虑到两个连续的 必须要清除掉
                    break;
                }
                preOne = true;
            } else {
                preOne = false;
            }
            if (bit == 0) {
                ++result;
            }
        }
        return result;
    }

    /**
     * 官方
     */
    public static int findIntegers1(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }
}
