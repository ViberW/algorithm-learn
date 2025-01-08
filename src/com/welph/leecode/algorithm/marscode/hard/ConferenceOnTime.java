package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 小C 计划在 hoursBefore 小时内赶到会议地点，需要经过 n 条不同的路线。这些路线的长度通过一个整数数组 dist 表示，其中 dist[i] 表示第 i 条路线的长度（单位：千米）。小C 在每条路线上的行驶速度为 speed（单位：千米每小时）。每当小C 完成一条路线后，他必须等到下一个整数小时才能开始下一条路线，除非他选择跳过等待时间。然而，完成最后一条路线后，小C 不需要再等待，因为他已经到达目的地。
 *
 * 例如，假设通过第 i 条路线需要 1.4 小时，那么他必须等到 2 小时才能开始下一条路线。如果他通过一条路线的时间恰好是整数小时，他可以立即继续前进。小C 也可以选择跳过某些等待时间，以更快到达目的地。
 *
 * 你需要帮助小C 计算出他准时到达会议地点所需的 最小跳过次数。如果无法按时到达，请返回 -1。
 */
public class ConferenceOnTime {

    //DP
    public static int solution(int[] dist, int speed, int hoursBefore) {
        int max = speed * hoursBefore + 1;
        if (Arrays.stream(dist).sum() > max) {
            return -1;
        }
        int[][] dp = new int[dist.length + 1][dist.length]; //表示跳过j次到达i处的最短耗时, 最多dist.length-1次休息
        Arrays.fill(dp[0], max);
        dp[0][0] = 0;
        for (int i = 1; i <= dist.length; i++) {
            for (int j = dist.length - 1; j >= 0; j--) {
                if (i != dist.length) {
                    //不等待
                    dp[i][j] = j == 0 ? max : dp[i - 1][j - 1] + dist[i - 1];
                    //等待
                    dp[i][j] = Math.min(dp[i][j], (dp[i - 1][j] + dist[i - 1] + speed - 1) / speed * speed);
                } else {
                    dp[i][j] = dp[i - 1][j] + dist[i - 1];
                }
            }
        }
        int[] end = dp[dist.length];
        for (int i = 0; i < dist.length; i++) {
            if (end[i] < max) {
                return i;
            }
        }
        return -1;
    }

    public static int solution2(int[] dist, int speed, int hoursBefore) {
        int max = hoursBefore * speed;
        //tips: 可以预处理,计算每一个等待的情况下.需要处理的时间, 省的数据量大时候遍历过多
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        for (int i = 0; i < dist.length; i++) {
            int value = dist[i];
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] poll = queue.poll();
                int next = poll[0] + value;
                if (next > max) {
                    continue;
                }
                //正好等于整数小时
                if (next % speed == 0 || i == dist.length - 1) {
                    queue.add(new int[]{next, poll[1]});
                } else {
                    //跳过
                    queue.add(new int[]{next, poll[1] + 1});
                    //不跳过
                    next = next + (speed - (next % speed));
                    if (next <= max) {
                        queue.add(new int[]{next, poll[1]});
                    }
                }
            }
        }
        int result = dist.length;
        while (!queue.isEmpty()) {
            result = Math.min(result, queue.poll()[1]);
        }
        result = result == dist.length ? -1 : result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 3}, 5, 3) == 0);
        System.out.println(solution(new int[]{6, 2, 5}, 7, 4) == 0);
        System.out.println(solution(new int[]{8, 4, 6, 2}, 4, 8) == 0);
        System.out.println(solution(new int[]{1, 3, 2}, 4, 2) == 1);
    }

}
