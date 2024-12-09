package com.welph.leecode.algorithm.marscode;

/**
 * 小C定义一个数组为“好数组”，当且仅当该数组满足以下条件：
 *
 * 数组仅由 0, 1, 2 三种元素组成。数组相邻的元素不相等。
 *
 * 例如：数组 [1, 2, 1, 2, 0, 1] 是一个好数组。小C定义一个数组的“陡峭值”为相邻元素的差的绝对值之和。
 * 例如，数组 [2, 1, 2, 0, 1] 的陡峭值为 |2-1| + |1-2| + |2-0| + |0-1| = 5。
 *
 * 现在，小C想知道，长度为 n 的所有好数组的陡峭值之和是多少。由于答案可能非常大，请对10^7+9取模。
 */
public class GoodArraySteep {

    public static int solution(int n) {
        int mod = 1000000007;
        //记录前一个数值的好数组个数k, 那么前一个数组最长的好数组位[i-1-k+1,i-1]=>[i-k,i-1]
        long[][] dp = new long[3][2]; //当前i处结尾位0,1,2的陡峭值总和及n种情况
        long[][] preDp = new long[3][2];
        preDp[0][0] = preDp[1][0] = preDp[2][0] = 1; //dp[i][0]表示情况,  dp[i][1]表示陡值总和
        for (int i = 1; i < n; i++) {
            dp[0][0] = preDp[1][0] + preDp[2][0];
            dp[0][1] = (preDp[1][0] + preDp[1][1] + preDp[2][0] * 2 + preDp[2][1]) % mod;

            dp[1][0] = preDp[0][0] + preDp[2][0];
            dp[1][1] = (preDp[0][0] + preDp[0][1] + preDp[2][0] + preDp[2][1]) % mod;

            dp[2][0] = preDp[1][0] + preDp[0][0];
            dp[2][1] = (preDp[1][0] + preDp[1][1] + preDp[0][0] * 2 + preDp[0][1]) % mod;
            long[][] tmp = preDp;
            preDp = dp;
            dp = tmp;
        }
        long result = 0;
        for (int i = 0; i < 3; i++) {
            result = (result + preDp[i][1]) % mod;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(2) == 8);
        System.out.println(solution(3) == 32);
        System.out.println(solution(4) == 96);
    }
}
