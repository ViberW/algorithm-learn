package com.welph.leecode.algorithm.z7z8;

/**
 * 扔鸡蛋问题
 * -- 有100层高楼, 手中有两个鸡蛋, 最小需要多少次测出最大承受楼层数不破碎
 */
public class ThrowEggs {

    /**
     * {100层, 有两个鸡蛋}
     * 反向思维: 若最少需要 X 次测出
     * 1) 第一次,也在 X 层处抛
     * -- 若在X+1处抛出, 若碎, 则需要 X+1 次, 不等于X
     * -- 若在X-1处抛出, 若碎, 则需要 X-1 次, 合理
     * -- 若在X处抛出, 若碎, 则正好需要 X 次, 合理
     * >> 本着最少次数(考虑之不碎,之后楼层),在X处
     * 2) 若不碎 那么则在 100-X 的剩余楼层, 有2个鸡蛋, 找出最少次数
     * -- 要求次数不超过 X-1, 因为第一次不碎已经使用过一次, 应该在 X+X-1处抛出
     * 3) 一直到转化 X, X-1, X-2, ... 1
     * ==>  X+(X-1)+(X-2)+...+1 = 100 => X(X+1)/2 = 100
     * ==> 最終是 14 次
     */
    public static void main(String[] args) {
        System.out.println(minThrow(100, 2));
    }

    /**
     * 从m楼层, 有n个鸡蛋, 最少次数测出最大承受楼层数
     * <p>
     * -- 动态规划 F(m,n) = min(Max(F(m-x, n)+1, F(x-1,n-1)+1))
     * >>>> 这里的X 有多个值, 找出最终 min对应的X最优解
     */
    public static int minThrow(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = j; //初始化最大次数
            }
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int x = 1; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]));
                }
            }
        }
        return dp[n][m];
    }
}
