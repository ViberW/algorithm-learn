package com.welph.leecode.algorithm.marscode;

/**
 * 给定一个长度为 n 的整数序列 a，你需要找到所有满足以下条件的整数对 (l, r)：
 *
 * 区间 [l, r] 的长度不小于 k，即 r - l + 1 ≥ k。
 * 区间 [a_l, a_{l+1}, ..., a_r] 中的第 k 小的数等于 x。
 * 你的任务是计算所有满足条件的 (l, r) 对的数量。
 */
public class MinKBySubArrays {
    public static int solution(int n, int x, int k, int[] a) {
        // 第k小的数是x  感觉是个单调栈的问题. 碰到等于x的时候去判断处理


        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 3, 2, new int[]{1, 2, 3, 4, 5}) == 3);
        System.out.println(solution(6, 2, 4, new int[]{4, 1, 5, 2, 4, 6}) == 0);
        System.out.println(solution(7, 3, 5, new int[]{3, 5, 2, 7, 5, 6, 5}) == 0);
    }
}
