package com.welph.leecode.part_321_340;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回-1。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class Solution322 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange2(coins, 11));
        int[] coins1 = {2};
        System.out.println(coinChange2(coins1, 3));
        int[] coins2 = {1};
        System.out.println(coinChange2(coins2, 0));
        int[] coins3 = {1};
        System.out.println(coinChange2(coins3, 1));
        int[] coins4 = {1, 2, 5};
        System.out.println(coinChange2(coins4, 100));
        int[] coins5 = {186, 419, 83, 408};
        System.out.println(coinChange2(coins5, 6249));
        int[] coins6 = {411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422};
        System.out.println(coinChange2(coins6, 9864));
    }

    /**
     * 动态规划. 取或不取的问题
     * dp[i]表示金额为i需要最少的金额多少,
     * 对于任意金额j,dp[j] = min(dp[j],dp[j-coin]+1),如果j-coin存在的话.
     * <p>
     * dp = [float("inf")] * (amount + 1)
     * dp[0] = 0
     * for i in range(1, amount + 1):
     * dp[i] = min(dp[i - c] if i - c >= 0 else float("inf") for c in coins ) + 1
     * return dp[-1] if dp[-1] != float("inf") else -1
     */
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = max;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    min = Math.min(dp[i - coin], min);
                }
            }
            dp[i] = min + 1;
        }
        return dp[amount] >= max ? -1 : dp[amount];
    }

    /**
     * 广度优先搜索, 尽量选择最大的面值
     * todo 还是超出时间的限制了  --这样的广度搜索不是最好的, 使用queue保存总计的值
     */
    public static int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        if (amount == 0) {
            return 0;
        }
        return coinChange1(coins, coins.length - 1, amount);
    }

    public static int coinChange1(int[] coins, int index, int amount) {
        if (index < 0) {
            return -1;
        }
        int min = -1;
        int v;
        //为什么不在找到一个目标min的时候break.
        //因为777111 和7755 这两个硬币 后面的硬币少.
        for (int j = amount / coins[index]; j >= 0; j--) {
            v = amount - j * coins[index];
            if (v == 0) {
                min = min < 0 ? j : Math.min(min, j);
            } else {
                v = coinChange1(coins, index - 1, v);
                if (v >= 0) {
                    min = min < 0 ? (v + j) : Math.min(min, v + j);
                }
            }
        }
        return min;
    }

    /**
     * 不能用贪心, 否则前面会限制到后面的选择, 甚至可能无解的状态
     * 先广度优先搜索吧  -----------todo 超出时间的限制了; 因为amount值很大 但硬币很小时不断往下找
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int min = -1;
        int coin;
        for (int i = 0; i < coins.length; i++) {
            coin = amount - coins[i];
            if (coin < 0) {
                continue;
            }
            if (coin == 0) {
                return 1;
            }
            coin = coinChange(coins, coin);
            min = min < 0 ? coin : Math.min(coin, min);
        }
        return min < 0 ? min : min + 1;
    }
}
