package com.welph.leecode.part_1_500.part_61_80;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class Solution62 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(19, 13));
        System.out.println(uniquePaths2(19, 13));
    }

    /**
     * 递归中间有数据结果集是相同的，超时
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        //不断递归，到达
        return filp(1, 1, m, n);
    }

    public static int filp(int x, int y, int m, int n) {
        int i = 0;
        if (x == m || y == n) {
            return 1;
        }
        i += filp(x + 1, y, m, n);
        i += filp(x, y + 1, m, n);
        return i;
    }

    /**
     * 用动态规划可能更好
     * p[m][n] = p[m-1][n] +p[m][n-1]
     */
    public static int uniquePaths2(int m, int n) {
        //动态规划
        int[][] arr = new int[m + 1][n + 1];
        arr[1][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m][n];
    }

}
