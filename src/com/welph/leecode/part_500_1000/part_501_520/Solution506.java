package com.welph.leecode.part_500_1000.part_501_520;

import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，
 * 依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * 示例 1：
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * <p>
 * 示例 2：
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 * <p>
 * 提示：
 * n == score.length
 * 1 <= n <= 10^4
 * 0 <= score[i] <= 10^6
 */
public class Solution506 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[] {
                5, 4, 3, 2, 1
        })));
        System.out.println(Arrays.toString(findRelativeRanks(new int[] {
                10, 3, 8, 9, 4
        })));
    }

    public static String[] findRelativeRanks(int[] score) {
        String[] medals = { "Gold Medal", "Silver Medal", "Bronze Medal" };
        String[] result = new String[score.length];
        // 快排的同时进行处理
        int[] index = new int[score.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        quickSort(score, index, 0, score.length - 1);
        for (int i = 0; i < score.length; i++) {
            result[index[i]] = i < 3 ? medals[i] : String.valueOf(i + 1);
        }
        return result;
    }

    private static void quickSort(int[] score, int[] index, int l, int r) {
        if (l < r) {
            int pivot = partition(score, index, l, r);
            quickSort(score, index, l, pivot - 1);
            quickSort(score, index, pivot + 1, r);
        }
    }

    private static int partition(int[] score, int[] index, int l, int r) {
        int pivot = score[r];
        int target = l;
        for (; l < r; l++) {
            if (score[l] > pivot) {
                swap(score, index, l, target);
                target++;
            }
        }
        swap(score, index, target, r);
        return target;
    }

    private static void swap(int[] score, int[] index, int i, int j) {
        int temp = score[i];
        score[i] = score[j];
        score[j] = temp;
        temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    // 与其自己写快排 不如使用二维数组
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        String[] desc = { "Gold Medal", "Silver Medal", "Bronze Medal" };
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            if (i >= 3) {
                ans[arr[i][1]] = Integer.toString(i + 1);
            } else {
                ans[arr[i][1]] = desc[i];
            }
        }
        return ans;
    }

}
