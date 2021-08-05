package com.welph.leecode.part_321_340;

/**
 * 给定一个m x n 整数矩阵matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * <p>
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为[1, 2, 6, 9]。
 * <p>
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是[3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 * <p>
 * 提示：
 * m == matrix.lengthz
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 */
public class Solution329 {

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    //递增即递减.  寻找到最长的递进序列,值不相等
    //dp [i][0] 到达i的最长递增序列, [i][1] 到达i的递减序列
    //
    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[1].length;
        int ans = 0;


        return ans;
    }
}
