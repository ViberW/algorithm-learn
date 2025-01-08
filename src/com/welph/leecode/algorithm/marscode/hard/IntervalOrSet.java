package com.welph.leecode.algorithm.marscode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 小R有一个长度为n的数组a,她定义每个子区间[1,r]的权值a[1]|a[1+1] | ... a[r],即该区间内所有元素的按位或运算结果。
 * <p>
 * 小R非常好奇,在这n*(n+1)/2个子区间中,究竟有多少种不同的权值
 * 她希望你能帮她计算一下,所有子区间中的不同权值总共有多多少种
 */
public class IntervalOrSet {

    /**
     * [l,r]共n个值,
     * 1. 选择1个.共n种可能
     * 2. 选择2个连续数组, 共n-1可能
     * ...
     * 一共  n × (n + 1) / 2种
     */
    public static int solution(int[] a) {
        Set<Integer> set = new HashSet<>();
        int[] dp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            set.add(dp[i] = a[i]);
            for (int j = 0; j < i; j++) {
                set.add(dp[j] = dp[j] | a[i]);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 4}) == 6);
        System.out.println(solution(new int[]{5, 3, 8, 1}) == 8);
        System.out.println(solution(new int[]{1, 1}) == 1);
        System.out.println(solution(new int[]{7, 8, 9, 10, 11}) == 6);
    }
}
