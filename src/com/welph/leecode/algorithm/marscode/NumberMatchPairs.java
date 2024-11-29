package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小F手中有一串数字，他希望这些数字能按照一定的规则两两配对。配对的规则如下：
 *
 * 数字对中的两个数字的差的绝对值必须大于等于给定的差异值M。
 * 每个数字只能被配对一次，不能出现在其他的数字对中。
 * 小F想知道给定的数字中，最多能成功配对出多少对数字。
 *
 * 例如，对于数字[1, 3, 3, 7]和差异值M = 2，最多可以配对两对数字：(1, 3)和(3, 7)。
 */
public class NumberMatchPairs {


    public static int solution(int N, int M, int[] X) {
        if (N <= 1) {
            return 0;
        }
        Arrays.sort(X);
        //使用双指针, 通过visited记录是否被使用, 不断寻找符合的结果
        boolean[] visited = new boolean[N];
        return dfs(N, M, X, 0, visited);
    }

    private static int dfs(int N, int M, int[] X, int index, boolean[] visited) {
        int maxPairs = 0;
        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                for (int j = i + 1; j < N; j++) {
                    if (!visited[j] && X[j] - X[i] >= M) {
                        visited[j] = true;
                        // 递归处理剩余的数字
                        maxPairs = Math.max(maxPairs, 1 + dfs(N, M, X, i + 1, visited));
                        // 回溯
                        visited[j] = false;
                    }
                }
                visited[i] = false;
            }
        }
        return maxPairs;
    }

    /**
     * 有点类似{@link ConvertStrSame}  但不一样. 这里会有下面的情况不好处理, 所以动态规划不是好的选择
     */
    public static int solution1(int N, int M, int[] X) {
        Arrays.sort(X);

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = N; j >= i; j--) {
                //如2 4 6 8 10 12 在i=2和j=4时,dp[2][3]=[[2,6][4,8]]包含了6. 但是从6到10.会有多次匹配6的问题
                if (j < N && X[j] - X[i] >= M) {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + 1);
                }
                if (i < N) { //跳过开头
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                }
                if (j < N) {//跳过结尾
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j]);
                }
            }
        }
        System.out.println(dp[N][N]);
        return dp[N][N];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 3, 7};
        int[] arr2 = {10, 9, 5, 8, 7};
        int[] arr3 = {2, 4, 6, 8, 10, 12};

        System.out.println(solution(4, 2, arr1) == 2);
        System.out.println(solution(5, 5, arr2) == 1);
        System.out.println(solution(6, 3, arr3));
    }
}
