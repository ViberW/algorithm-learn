package com.welph.leecode.algorithm.marscode.hard;

/**
 * 在一个神秘的竞技场中，勇敢的探险者小青拥有两个宝箱：
 * 一个宝箱里装满了 n 个金银珠宝的数值，另一个则是一个包含 m 个神秘符文的序列。
 *
 * 小青面临着一个挑战：在接下来的 m 轮中，他必须在这两者之间做出明智的选择，以获得最高的财富。
 *
 * 在每一轮（第 i 轮）中，小青可以选择从宝箱的最上面或最下面取出一个珠宝 x。
 * 然后，他会将这个珠宝的价值乘以对应的符文 c[i]，并把结果累加到他的总财富中。
 * 被取出的珠宝将从宝箱中消失，接着小青会继续下一轮操作，直到完成 m 轮。
 *
 * 你的任务是帮助小青计算，在完成 m 轮挑战后，他可以获得的最高财富是多少。
 *
 * 输入：
 * stones：宝箱中n个金银珠宝的数值
 * c：m个神秘符文
 *
 * 约束条件：
 * 时间限制：3s
 * stones 和 c序列大小的数据范围为 1 ≤ m ≤ n≤ 2000，并且 -1000 ≤ stones[i], c[i]≤ 1000
 */
public class MaxWealthSelect {

    //比赛的时候, 用了DFS+剪枝, 但效率肯定是不如DP
    public static int solution(int[] stones, int[] c) {
        //使用动态规划
        int n = c.length;
        int[] dp = new int[n + 1];
        int delta = stones.length - c.length;
        for (int i = n - 1; i >= 0; i--) {
            //范围为i到i+n
            for (int j = 0; j < n; j++) {
                //delta 为0的情况下, 则仅仅考虑dp[i]即可
                dp[j] = dp[j] + stones[j + delta] * c[i];
                if (delta > 0) {
                    dp[j] = Math.max(dp[j], dp[j + 1] + stones[j] * c[i]);
                }
            }
            delta++;
            n--; //随着扩大, 选择范围开始缩小
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-3, 4, 5}, new int[]{2, -1, 3}) == 25);
        System.out.println(solution(new int[]{6, -7, 1}, new int[]{4, -3}) == 45);
        System.out.println(solution(new int[]{3, 5, -2, 9}, new int[]{1, 3, -5}) == 40);
        System.out.println(solution(new int[]{-6, 13, 12, 8, -13, -7, -8, 5, 3, -18, 21, 16, 11, 0, 20, -6, 7, 2, -20, 7, -5, -11, 20, 11, -7},
                new int[]{20, -18, -6, 22, -8, 23, -25, 17, 7, -9, -20, -12, -1, 10, -6, -8, -5, 3, 18}));
    }
}
