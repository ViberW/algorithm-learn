package com.welph.leecode.part_1_500.part_61_80;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class Solution64 {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] arr = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    arr[i][j] = arr[i][j - 1] + grid[i - 1][j - 1];
                } else if (j == 1) {
                    arr[i][j] = arr[i - 1][j] + grid[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.min(arr[i - 1][j], arr[i][j - 1]) + grid[i - 1][j - 1];
                }
            }
        }
        return arr[m][n];
    }
}
