package com.welph.leecode.algorithm.marscode.hard;

/**
 * 给定两个长度为 n 的数组 a 和 b，以及一个空数组 c。你可以执行以下操作：
 *
 * 每次可以选择 a 数组或者 b 数组的第一个元素，添加到 c 数组的末尾，然后从对应的数组中删除这个元素。
 *
 * 当 a 数组和 b 数组都为空时，操作结束。
 *
 * 小C希望在所有可能生成的 c 数组中，找出最长的递增子数组，且该子数组需要满足相邻两个元素的差的绝对值为 1。
 *
 * 请你计算并输出这个最长递增子数组的长度。
 */
public class MaxIncreaseArray {

    public static int solution(int[] a, int[] b) {
        int n = a.length;
        int[][] dp = new int[n + 1][2]; //0代表以a为结尾, 2代表以b为结尾
        // 状态转移
        int ret = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 1 && a[i - 1] - a[i - 2] == 1) {
                    dp[j][0] = dp[j][0] + 1; //[i-1][j][0]
                } else {
                    dp[j][0] = 1;
                }
                if (i > 0 && j > 0 && a[i - 1] - b[j - 1] == 1) {
                    dp[j][0] = Math.max(dp[j][0], dp[j][1] + 1); //[i-1][j][1]  --此时还是i-1
                }
                if (j > 1 && b[j - 1] - b[j - 2] == 1) {
                    dp[j][1] = dp[j - 1][1] + 1; //[i][j-1][1]   --变为i
                } else {
                    dp[j][1] = 1;
                }
                if (i > 0 && j > 0 && b[j - 1] - a[i - 1] == 1) {
                    dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] + 1); //[i][j-1][0]
                }
                ret = Math.max(ret, Math.max(dp[j][0], dp[j][1]));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 2, 4}, new int[]{7, 1, 3}) == 4);
        System.out.println(solution(new int[]{3, 6, 8, 1}, new int[]{2, 7, 9, 5}) == 4);
        System.out.println(solution(new int[]{10, 15, 12, 13, 11}, new int[]{9, 14, 16, 18, 17}) == 3);
    }

}
