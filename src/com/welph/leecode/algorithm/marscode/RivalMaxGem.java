package com.welph.leecode.algorithm.marscode;

/**
 * 在一个神秘的宝石世界中，小R 和 小S 正在进行一场激烈的宝石争夺战。游戏规则如下：
 *
 * 一列宝石堆 piles[i] 由若干宝石组成，每个宝石堆内的宝石数量都是正整数。
 * 小R 先[开始轮流]从中取走宝石，而小S 则在小R 之后进行操作。一开始，W = 1。
 * 在每一轮中，玩家可以从剩余的宝石堆中拿走K堆的宝石，满足1 <= K <= 2 * W。
 * 在玩家做出选择后，W 的值会更新为 max(W, K)，也就是说，下一轮的可取堆数由当前的最大选择决定。
 *
 * 比赛一直持续到所有宝石被取完。最终胜者为手中宝石最多的玩家。
 * 假设小R 和 小S 都使用最佳策略，请你计算小R 在这场比赛中最多可以拿到多少宝石。
 */
public class RivalMaxGem {

    public static int solution(int[] piles) {
        //选择k堆, 1<=k<=2*w 则下一次 w=max(w,k)  所以k一定是非递减增长的
        //这道题需要按照顺序取
        int[] preSum = new int[piles.length + 1];
        for (int i = 1; i <= piles.length; i++) {
            preSum[i] = preSum[i - 1] + piles[i - 1];
        }
        //小R 和 小S 都使用最佳策略:
        //第 i 堆宝石开始，当前 W 值为 j 时，[0]小S能够让后面小R拿最少 [1]最多可以拿到的宝石数。
        int[][][] dp = new int[piles.length + 1][piles.length + 1][2];
        for (int i = piles.length - 1; i >= 0; i--) {
            for (int j = 1; j <= piles.length; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                int maxK = Math.min(2 * j, piles.length - i); //最多能取的范围.
                for (int k = 1; k <= maxK; k++) {
                    //拿k个宝石, 到下一次就是Math.max(j, k)  由于R和S交替的. 所以这里的遍历方式也是交替的
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i + k][Math.max(j, k)][0] + preSum[i + k] - preSum[i]);
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i + k][Math.max(j, k)][1]); //若是小S,则
                }
            }
        }
        return dp[0][1][1];
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 12, 7, 4, 9}) == 17);
        System.out.println(solution(new int[]{3, 6, 8, 10, 50}) == 21);
        System.out.println(solution(new int[]{7, 3, 5, 2, 8, 6}) == 18);
    }
}
