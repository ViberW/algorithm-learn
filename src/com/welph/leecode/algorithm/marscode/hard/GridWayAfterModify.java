package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R正在处理一个由N行和N列组成的网格问题，N是一个偶数。最初，所有单元格的值均为0。
 *
 * 你可以进行以下操作来修改网格：选择一个宽为1，高为N/2的子网格，其中所有单元格的值都是0，并将它们全部更新为1。
 *
 * 如果从左上角的单元格 A[0][0] 到右下角的单元格 A[N-1][N-1] 存在一条路径，且路径中只经过值为0的单元格，
 * 那么我们称该网格为“伟大”的网格。
 *
 * 你的任务是找到可以通过执行上述操作生成的不同“伟大”网格的总数，答案需要对 10^9 + 7 取模。
 */
public class GridWayAfterModify {

    public static int solution(int N) {
        int mod = 1000000007;
        //动态规划? 跳过当前, 或者当前进行操作. dp[i][j]代表i,j点的可能性? 思考了下不行的. 因为会重复路径
        //所以可以使用标记上一次被封堵的范围{l,r} 那么就是以非这个范围之内的这样方式进入的数量
        int move = N / 2 + 1;     //操作后的格子不能重叠. N,那么有可能 N/2  + 1种操作的可能
        int step = N / 2 - 1;
        long[][][] dp = new long[N + 1][N + 1][N + 1];  //保存i处, 存在封闭范围[l,r]时通过的次数
        dp[0][2][N] = 1; //表明最开始就是除了第一格能通过,其他都不能通过
        for (int i = 1; i <= N; i++) {
            //不操作
            dp[i][0][0] = dp[i - 1][0][0];
            for (int l = 1; l <= move; l++) {
                for (int r = l + step; r <= N; r++) {
                    dp[i][0][0] = (dp[i][0][0] + dp[i - 1][l][r]) % mod;
                }
            }

            //操作[l,l+step]
            for (int j = 1; j <= move; j++) {//添加[j,j+step]网格
                dp[i][j][j + step] = (dp[i][j][j + step] + dp[i - 1][0][0]) % mod;

                for (int l = 1; l <= move; l++) {
                    for (int r = l + step; r <= N; r++) { //可以知道这个范围一定是大于等于N
                        if (dp[i - 1][l][r] == 0) {
                            continue;
                        }
                        int newL = l == 1 ? 1 : j; //l==1 必定封堵了当前次从最上面进入的入口
                        int newR = r == N ? N : j + step;
                        if (newL == 1 && newR == N) {
                            continue;
                        }
                        dp[i][newL][newR] = (dp[i][newL][newR] + dp[i - 1][l][r]) % mod;
                    }
                }
            }
        }
        long result = dp[N][0][0];
        for (int l = 1; l < move; l++) { //这里可以不等于move,因为没意义, 必定封堵终点
            for (int r = l + step; r < N; r++) { //不能等于N,否则到不了A[N-1][N-1]
                result = (result + dp[N][l][r]) % mod;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4) == 74);
        System.out.println(solution(6) == 4099);
        System.out.println(solution(2) == 3);
    }
}
