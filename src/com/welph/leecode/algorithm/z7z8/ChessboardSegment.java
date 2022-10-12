package com.welph.leecode.algorithm.z7z8;

/**
 * 有一个m*m的棋盘，沿着格子的边进行分割，每分割一块{拿走}，将剩下的矩形棋盘继续分割
 * 经过n-1次分割后，可以得到n块矩形棋盘
 * >> 分割好A和B 若拿走了A 则只能对B进行后续的分割
 * <p>
 * 每个小格子中存在一个分值, 分割后每块都有一个总分xi(所有格子总和)
 * 如何分割使得各个矩形的总分的 [均方差最小]
 */
public class ChessboardSegment {


    public static void main(String[] args) {
        System.out.println(segment(new int[][]{
                {1, 2, 1, 1, 4, 0, 2, 1},
                {3, 2, 0, 0, 2, 0, 3, 3},
                {0, 1, 1, 1, 7, 3, 2, 0},
                {4, 0, 0, 0, 2, 0, 3, 1},
                {3, 2, 0, 0, 2, 0, 3, 3},
                {0, 1, 1, 1, 7, 3, 2, 0},
                {1, 2, 1, 1, 4, 0, 2, 1},
                {4, 1, 1, 7, 2, 7, 0, 0}
        }, 3));
    }

    /**
     * σ = sqrt(((x1-avg)^2 +(x2-avg)^2+...+(xi-avg)^2) / n)
     * 其中 avg =(x1+x2+...+xi)/n  固定了
     * -------
     * 上述式子简化:
     * σ = sqrt(((x1^2- 2*x1*avg+avg^2) + (x2^2- 2*x2*avg+avg^2) +...+ (xi^2- 2*xi*avg+avg^2) ) / n)
     * = sqrt((x1^2+...+xi^2 ) / n   - 2*avg^2 +avg^2 )  //2*avg^2 里面每个相加得到avg*avg
     * = sqrt((x1^2+...+xi^2 ) / n  - avg^2)
     * >> 均方差最小 即求的 (x1^2+...+xi^2 ) / n 的最小值即可
     * ----------------------------------------------------------
     * 通过使用 动态规划  用五维数组表示矩形的 左上(i,j) 和 右下(k,l)两个点的四个坐标值 加上上一次的切分规则
     */
    public static double segment(int[][] board, int n) {
        int m = board.length;
        int M = m + 1;
        Integer[][][][][] dp = new Integer[2][M][M][M][M];
        /*
         * 1. 若垂直切分,这之后只能从左边或右边切 设置X中间线为 b
         * dp[t][i][j][k][l] = min(
         *  cal(i,j,k,b)+dp[t-1][i][b+1][k][l], cal(i,b+1,k,l)+dp[t-1][i][j][k][b])
         *
         * 2. 若水平切分,这之后只能从上边或上边切 设置Y中间线为 a
         * dp[t][i][j][k][l] = min(
         *  cal(i,j,a,l)+dp[t-1][a+1][j][k][l], cal(a+1,j,k,l)+dp[t-1][i][j][a][l])
         *
         *  == cal()方法为计算分值平方数 因为它们不被切分
         *  == 这里的t-1 和上一次的有关 使用0和1表示
         */
        //预先计算好到每个节点的总和
        int[][] sum = new int[M][M];
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + board[i - 1][j - 1];
            }
        }
        //初始化好, 考虑到最开始的下面使用0 本质
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                for (int k = i; k < M; k++) {
                    for (int l = j; l < M; l++) {
                        dp[0][i][j][k][l] = getSum(sum, i, j, k, l);
                    }
                }
            }
        }
        //开始计算
        int s = 0; //代表当前切的方式,因为从 t 计算使用的t-1, 那么仅仅需要使用到0和1代替, 交替覆盖
        for (int t = 1; t < n; ++t) {
            s = 1 - s;
            // 枚举起点
            for (int i = 1; i < M; ++i) {
                for (int j = 1; j < M; ++j) {
                    // 枚举终点
                    for (int k = i; k < M; ++k) {
                        for (int l = j; l < M; ++l) {
                            // 横切
                            for (int a = i; a < k; ++a) {
                                dp[s][i][j][k][l] = min(dp[s][i][j][k][l], nullOr(dp[1 - s][i][j][a][l]) + getSum(sum, a + 1, j, k, l));
                                dp[s][i][j][k][l] = min(dp[s][i][j][k][l], nullOr(dp[1 - s][a + 1][j][k][l]) + getSum(sum, i, j, a, l));
                            }
                            // 纵切
                            for (int b = j; b < l; ++b) {
                                dp[s][i][j][k][l] = min(dp[s][i][j][k][l], nullOr(dp[1 - s][i][j][k][b]) + getSum(sum, i, b + 1, k, l));
                                dp[s][i][j][k][l] = min(dp[s][i][j][k][l], nullOr(dp[1 - s][i][b + 1][k][l]) + getSum(sum, i, j, k, b));
                            }
                        }
                    }
                }
            }
        }
        double average = sum[m][m] * 1.0 / n;
        double ans = dp[s][1][1][m][m] * 1.0 / n - average * average;
        return Math.sqrt(ans);
    }

    static int getSum(int[][] sum, int i, int j, int k, int l) {
        int ans = sum[k][l] - sum[k][j - 1] - sum[i - 1][l] + sum[i - 1][j - 1];
        return ans * ans;
    }

    static int nullOr(Integer x) {
        return null == x ? 0 : x;
    }


    static int min(Integer x, Integer y) {
        return x != null && x < y ? x : y;
    }

}
