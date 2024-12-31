package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的数组，数组上从 0 到 n 标记了一些切割点。
 * 切割点的位置用数组 cuts 表示，其中 cuts[i] 代表需要在 cuts[i] 位置进行切割。
 * 每次切割的代价等于当前部分的长度，因此可以自由选择切割的顺序，以最小化总切割代价。
 *
 * 任务是找到一个最优的切割顺序，使得切割数组的总代价最小。
 */
public class SliceArrayToMinCost {

    //{@link MaxWealthSelect} 类似
    public static int solution(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        // 将切割点排序并构建数组
        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        newCuts[m + 1] = n;
        System.arraycopy(cuts, 0, newCuts, 1, m);
        // 初始化 DP 数组
        int[][] dp = new int[m + 2][m + 2];
        for (int i = 1; i <= m; i++) {
            dp[i][i] = newCuts[i + 1] - newCuts[i - 1];
            for (int j = i - 1; j >= 1; j--) {
                int ans = Integer.MAX_VALUE;
                for (int k = j; k <= i; k++) {
                    ans = Math.min(ans, dp[k - 1][j] + dp[i][k + 1]);
                }
                dp[i][j] = ans + newCuts[i + 1] - newCuts[j - 1];
            }
        }
        return dp[m][1];
    }

    // 计算给定切割点下的最小成本
    static int solution2(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        // 将切割点排序并构建数组
        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        newCuts[m + 1] = n;
        System.arraycopy(cuts, 0, newCuts, 1, m);
        // 初始化 DP 数组
        int[][] dp = new int[m + 2][m + 2];
        int result = process(newCuts, 1, m, dp);
        return result;
    }

    // 递归函数，计算给定区间的最小成本
    static int process(int[] cuts, int l, int r, int[][] dp) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return cuts[r + 1] - cuts[l - 1];
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            ans = Math.min(ans, process(cuts, l, k - 1, dp) + process(cuts, k + 1, r, dp));
        }
        ans += cuts[r + 1] - cuts[l - 1];
        dp[l][r] = ans;
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(solution(8, new int[]{2, 4, 5, 6}) == 18);
        System.out.println(solution(11, new int[]{1, 3, 6, 8}) == 25);
        System.out.println(solution(12, new int[]{3, 5, 9}) == 24);
        System.out.println(solution(9, new int[]{5, 6, 1, 4, 2}));
    }
}
