package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 小C拿到一个数组a,她准备构造一个新的数组b,并需要满足以下
 * 条件:
 * 1.数组b中的每一位都与数组 对应位置的元素不同,即bi != ai.
 * 2.数组b的所有元素之和等于数组a的所有元素之和。
 * 3.数组b的所有元素必须是正整数。
 * <p>
 * 请计算有多少种构造数组b的方式,答案需要对10°+7取模。
 */
public class BuildArray {
    //dfs
    public static int solution(int n, int[] a) {
        // write code here
        int sum = Arrays.stream(a).sum();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sum);
        int i = 0;
        while (!queue.isEmpty() && i != n - 1) {
            int val = a[i];
            int delta = n - i - 1;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Integer poll = queue.poll();
                for (int j = 1; j <= poll - delta; j++) {
                    if (j == val) {
                        continue;
                    }
                    queue.add(poll - j);
                }
            }
            i++;
        }
        return (int) queue.stream().filter(v -> v != a[n - 1]).count();
    }

    //DP 动态规划
    public static int solution1(int n, int[] a) {
        int MOD = 1000000007;
        int sum = Arrays.stream(a).sum();
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = sum - (n - i); j > 0; j--) {
                dp[j] = 0;
                for (int k = 1; k <= j; k++) {
                    if (k != a[i - 1]) {
                        dp[j] = (dp[j] + dp[j - k]) % MOD;
                    }
                }
            }
            if (i == 1) {
                dp[0] = 0;
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 1, 3}) == 1);
        System.out.println(solution(3, new int[]{1, 1, 1}) == 0);
        System.out.println(solution(2, new int[]{2, 3}) == 3);
    }
}
