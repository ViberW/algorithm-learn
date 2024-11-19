package com.welph.leecode.algorithm.marscode;

/**
 * 小R定义了一个长度为 m 的数组 b 的权值为：1×b_1 + 2×b_2 + 3×b_3 + ... + m×b_m。
 *
 * 现在小R有一个长度为 n 的数组 a，她想知道所有子数组的权值和是多少，并且答案需要对 10^9 + 7 取模。
 */
public class IntervalWeightSum {

    public static int solution(int n, int[] a) {
        if (n == 0) {
            return 0;
        }
        int mod = 1000000007;
        long[] preSum = new long[n + 1];
        preSum[1] = a[0];
        for (int i = 1; i < n; i++) {
            preSum[i + 1] = (preSum[i] + a[i]) % mod;
        }
        long total = 0;
        for (int i = 1; i <= n; i++) {
            long weight = 0;
            for (int j = i - 1; j >= 0; j--) {
                weight = (weight + preSum[i] - preSum[j]) % mod;
                total = (total + weight) % mod;
            }
        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 3}) == 33);
        System.out.println(solution(4, new int[]{4, 5, 6, 7}) == 203);
        System.out.println(solution(2, new int[]{10, 20}) == 80);
    }
}
