package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小E想知道一个给定集合中，有多少个子集满足以下条件：
 * <p>
 * 子集内的所有元素数量大于 1。
 * 子集内的所有元素两两之间互为倍数关系。
 * 由于结果可能非常大，输出的结果需要对 1000000007 取模。
 */
public class EachMultipleSubList {

    public static int solution(int n, int[] a) {
        Arrays.sort(a);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ret = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] % a[j] == 0) {
                    dp[i] += dp[j];
                }
            }
            ret += dp[i];
        }
        ret -= n; //剔除单个字符的情况
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 2, 3, 4, 5}) == 6);
        System.out.println(solution(6, new int[]{2, 4, 8, 16, 32, 64}) == 57);
        System.out.println(solution(4, new int[]{3, 6, 9, 12}) == 5);
    }
}
