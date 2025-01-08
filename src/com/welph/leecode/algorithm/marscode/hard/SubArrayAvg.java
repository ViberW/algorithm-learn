package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U有一个长度为n的整数数组，并且选择了一个有理数 u/v。
 * 他现在想知道这个数组中有多少个连续的子区间，其平均值恰好等于 u/v。数组的子区间是指数组中一段连续的元素。
 *
 * 例如，给定数组 [2, 4, 1, 3, 2, 3] 和有理数 5/2，我们需要找出所有平均值等于 5/2 的子区间。
 */
public class SubArrayAvg {

    public static int solution(int n, int u, int v, int[] arr) {
        //简化, 最大公约数
        int gcd = gcd(u, v);
        u = u / gcd;
        v = v / gcd;

        //所以区间长度位v的整数倍
        int result = 0;
        //前缀和
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
            for (int j = i - v, delta = 1; j >= 0; j -= v, delta++) {
                if (sum[i] - sum[j] == delta * u) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(solution(6, 5, 2, new int[]{2, 4, 1, 3, 2, 3}) == 6);
        System.out.println(solution(5, 1, 1, new int[]{1, 1, 1, 1, 1}) == 15);
        System.out.println(solution(4, 2, 1, new int[]{2, 2, 2, 2}) == 10);
    }
}
