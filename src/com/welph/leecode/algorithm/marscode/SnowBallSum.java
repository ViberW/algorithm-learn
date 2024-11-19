package com.welph.leecode.algorithm.marscode;

/**
 * 在一座高度为 H 的山上，每个高度 i 处生成了 a_i 个雪球。当雪球从海拔高度 i 滚到地面时，
 * 它的体积会膨胀 x^i 倍。也就是说，雪球的初始体积为 1，滚动距离 i 会使体积变成 1 * x^i。
 * 我们需要计算所有滚落到地面的雪球的总体积，并对结果取模 10^9 + 7。
 *
 * 你的任务是帮助计算所有雪球滚落到地面的总体积。
 */
public class SnowBallSum {

    public static int solution(int H, int x, int[] a) {
        int mod = 1000000007;
        long total = 0;
        //这一题主要考虑的是指数超过long大小的情况, 所以需要考虑结果不超限的情况下. 对计算结果的取模
        long power = 1;
        for (int i = 0; i < H; i++) {
            power = (power * x) % mod;
            total = (total + a[i] * power) % mod;
        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new int[]{1, 3, 2, 4}) == 2830);
        System.out.println(solution(2, 5, new int[]{1, 2}) == 55);
        System.out.println(solution(3, 3, new int[]{2, 1, 1}) == 42);
    }
}
