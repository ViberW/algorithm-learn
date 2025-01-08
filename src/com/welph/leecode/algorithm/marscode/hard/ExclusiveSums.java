package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小C拿到了一个数组，她定义一个数组的权值为数组中任意选取两个数的异或之和。
 *
 * 具体来说，对于一个数组，每个连续子数组的任意两元素的异或值之和定义为其权值。
 * 她想知道该数组中所有可能的连续子数组的权值之和是多少，答案需要对10^9 + 7取模。
 */
public class ExclusiveSums {
    //{@link IntervalOrSum} {@link IntervalOrSum}
    public static int solution(int n, int[] a) {
        int mod = 1000000007;
        int ret = 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int val = 0;
            for (int j = i - 1; j >= 0; j--) {
                val = (val + (a[j] ^ a[i])) % mod;
                dp[j] = (dp[j] + val) % mod;
                ret = (ret + dp[j]) % mod;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{2, 3, 1, 2}) == 28);
        System.out.println(solution(3, new int[]{5, 6, 7}) == 10);
        System.out.println(solution(2, new int[]{1, 10}) == 11);
        System.out.println(solution(5, new int[]{1, 2, 4, 8, 16}) == 402);
    }
}
