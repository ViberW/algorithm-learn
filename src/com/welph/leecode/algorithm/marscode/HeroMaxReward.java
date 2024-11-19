package com.welph.leecode.algorithm.marscode;

import java.util.*;

/**
 * 小 W 有 n 个英雄，能力值为 a_i (1 <= i <= n)，初始能力值都为 1，小 W 可以最多完成 k 次升级，
 * 每一次升级从 n 个英雄中挑选出某一个（例如 i ），然后任意选择一个正整数 x，
 * 对它进行升级，升级后能力值 a_i = a_i + \text{floor}(a_i / x) （floor 代表下取整）。
 * 其中每一个英雄能力值刚好升级成 b_i 时小 W 会获得 c_i 的奖励（每个英雄只能获取一次奖励），
 *
 * 最后小 W 想知道最多能获得多少的奖励？
 */
public class HeroMaxReward {

    /**
     * 判断一个数的因子({@link NumMultipleLonger})
     */
    public static int solution(int n, int k, int[] b, int[] c) {
        int max = Arrays.stream(b).max().getAsInt();
        int[] dp = new int[max + 1];
        for (int i = 0; i < max; i++) {
            dp[i + 1] = i; //最多的步数
        }
        int next;
        for (int i = 2; i < max; i++) {
            for (int j = i; j > 0; j--) {
                if ((next = i + i / j) > max) {
                    break;
                }
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }
        //01背包问题: 总容量为k, 需要选择n个物品 价值最高
        int[] wardsDp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            int step = dp[b[i]];
            for (int j = k; j >= step; j--) {
                wardsDp[j] = Math.max(wardsDp[j], wardsDp[j - step] + c[i]);
            }
        }
        return wardsDp[k];
    }

    public static void main(String[] args) {
        // Add your test cases here
        System.out.println(solution(4, 4, new int[]{1, 7, 5, 2}, new int[]{2, 6, 5, 2}) == 9);
        System.out.println(solution(3, 0, new int[]{3, 5, 2}, new int[]{5, 4, 7}) == 0);
        System.out.println(solution(23, 14,
                new int[]{17, 8, 19, 14, 17, 13, 22, 17, 6, 10, 22, 12, 2, 6, 21, 24, 22, 25, 3, 3, 18, 23, 20},
                new int[]{16, 22, 9, 12, 7, 3, 18, 23, 1, 17, 7, 5, 10, 25, 24, 8, 20, 9, 9, 13, 13, 11, 2}));//94
    }

}
