package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小C面临一个选择问题：她可以从编号为1到n的糖果中任意选择一些糖果作为奖励，
 * 但需要遵守一个限制规则：如果选择了编号为i的糖果，那么编号为i-1、i-2、i+1、i+2的糖果将不能被选择。
 * 每个糖果都有一个对应的美味值，小C希望所选糖果的美味值之和最大。
 *
 * 例如：当有7个糖果，编号为1到7，美味值分别为 3, 1, 2, 7, 10, 2, 4，
 * 小C可以选择编号为1、4、7的糖果，这样可以得到最大美味值之和为 3 + 7 + 4 = 14。
 */
public class MaxSweetCandy {

    public static int solution(int n, int[] a) {
        //类似与分组背包
        int[] dp = new int[3];
        int index = 2;
        for (int i = 0; i < n; i++) {
            if (++index == 3) {
                index = 0;
            }
            //选择a[i] 和不选a[i]
            dp[index] += a[i];
            for (int j = 1; j < 4; j++) {
                dp[index] = Math.max(dp[index], dp[(index + j) % 3]);
            }
        }
        return dp[index];
    }

    public static void main(String[] args) {
        System.out.println(solution(7, new int[]{3, 1, 2, 7, 10, 2, 4}) == 14);
        System.out.println(solution(5, new int[]{1, 10, 2, 5, 8}) == 18);
        System.out.println(solution(6, new int[]{4, 5, 6, 1, 2, 3}) == 9);
    }

}
