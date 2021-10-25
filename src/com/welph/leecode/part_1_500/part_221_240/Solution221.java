package com.welph.leecode.part_1_500.part_221_240;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：matrix = [['0','1'],['1','0']]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：matrix = [['0']]
 * 输出：0
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class Solution221 {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix));
        char[][] matrix1 = {
                {'1', '0'},
                {'0', '1'},
        };
        System.out.println(maximalSquare(matrix1));
    }

    /**
     * 正方形: dp[i][j] = 到达i和j点的相关的长方形数据点
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1  //正方形的边
     */
    public static int maximalSquare(char[][] matrix) {
        int length = matrix.length;
        int size = matrix[0].length;
        int[][] dp = new int[length + 1][size + 1];
        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j])) + 1;
                    if (dp[i + 1][j + 1] > max) {
                        max = dp[i + 1][j + 1];
                    }
                }
            }
        }
        return max * max;
    }
}
