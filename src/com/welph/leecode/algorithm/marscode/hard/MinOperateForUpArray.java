package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 在一个神秘的森林里，两个探险家，小智和小璇，面临着一项艰巨的任务。
 * 他们拥有两个魔法宝箱，分别装有整数列表 list1 和 list2。
 * 他们的目标是通过某种方式将 list1 转变为一个严格递增的序列。
 *
 * 他们可以执行的操作是：在任意时刻，选择 list1 中的一个元素和 list2 中的一个元素进行替换。
 * 具体来说，他们可以选择 list1 的某个位置 i 和 list2 的某个位置 j，并将 list1[i] 设置为 list2[j]。
 *
 * 现在，小智和小璇需要你帮助他们计算出，通过最少的操作次数，是否可以将 list1 变为严格递增的序列。
 * 如果可以做到，请返回所需的最少操作次数；如果不可能，请返回 -1。
 */
public class MinOperateForUpArray {

    //{@link Solution300 }
    public static int solution(int[] list1, int[] list2) {
        // 对 list2 排序，方便后续的二分查找
        Arrays.sort(list2);
        int n = list1.length;
        int m = list2.length;
        // 定义 dp 数组
        int nm = Math.min(n, m);
        int[][] dp = new int[n + 1][nm + 1]; //到i处, 进行了j次替换的最小值.
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = Math.min(list1[0], list2[0]) - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= nm; j++) {
                //不替换
                if (list1[i - 1] > dp[i - 1][j]) {
                    dp[i][j] = list1[i - 1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
                //替换, 找到对应的索引. 往后找到目标值
                if (j > 0) {
                    int index = lowerBound(list2, dp[i - 1][j - 1]);
                    if (index < m) {
                        dp[i][j] = Math.min(dp[i][j], list2[index]);
                    }
                }
            }
        }
        int[] arr = dp[n];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return -1;
    }

    private static int lowerBound(int[] list2, int target) {
        int low = 0, high = list2.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list2[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{3, 8, 4, 10, 11}, new int[]{2, 7, 5, 9}) == 1);
//        System.out.println(solution(new int[]{5, 9, 7, 11}, new int[]{1, 6, 8, 12}) == 1);
//        System.out.println(solution(new int[]{1, 3, 4, 6}, new int[]{2, 5}) == 0);
//        System.out.println(solution(new int[]{1, 5, 3, 6, 7}, new int[]{4, 3, 1, 5}));//2
        System.out.println(solution(new int[]{5, 17, 1, 9, 10, 16}, new int[]{3, 13, 5, 1, 6, 5, 2}));//3
    }
}
