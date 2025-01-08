package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小K有一个数组，她定义数组的权值为数组中任选两个数的按位与的值之和。
 * <p>
 * 具体来说，对于数组中的每个连续子数组，我们可以计算所有可能的两个元素的按位与值之和，并将这些值相加。
 * <p>
 * 小K想知道该数组中所有可能的连续子数组的权值和是多少，最后结果对10^9 + 7取模。
 */
public class IntervalAndSum {
    public static int solution(int n, int[] a) {
        int mod = 1000000007;
        // write code here
        int ret = 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int val = 0;
            for (int j = i - 1; j >= 0; j--) {
                val = (val + (a[j] & a[i])) % mod;
                dp[j] = (dp[j] + val) % mod;
                ret = (ret + dp[j]) % mod;
            }
        }
        return ret;
    }

    //对上面的空间简化
    public static int solution1(int n, int[] a) {
        int mod = 1000000007;
        int ret = 0;
        int pre = 0;
        for (int i = 1; i < n; i++) {
            int val = 0;
            for (int j = i - 1; j >= 0; j--) {
                val = (2 * val + (a[j] & a[i])) % mod;
            }
            pre = (pre + val) % mod;
            ret = (ret + pre) % mod;

        }
        return ret;
    }


    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{2, 3, 1, 2}) == 16);
        System.out.println(solution(3, new int[]{5, 6, 7}) == 25);
        System.out.println(solution(2, new int[]{1, 10}) == 0);
        System.out.println(solution(5, new int[]{1, 2, 4, 8, 16}) == 0);
    }
}
