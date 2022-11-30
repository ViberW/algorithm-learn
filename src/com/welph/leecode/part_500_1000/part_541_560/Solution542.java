package com.welph.leecode.part_500_1000.part_541_560;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定一个由 0 和 1 组成的矩阵 mat ，
 * 请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1：
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * <p>
 * 示例 2：
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0
 */
public class Solution542 {

    public static void main(String[] args) {
        int[][] ints = updateMatrix1(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    /**
     * 能否通过下面的进行总结呢?
     * 下面空间小于5% 说明不需要重新new空间,.原地赋值
     */
    public static int[][] updateMatrix1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int max = m + n;
        //正遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = max;
                    if (i > 0) {
                        mat[i][j] = Math.min(mat[i][j], mat[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][j - 1] + 1);
                    }
                }
            }
        }
        //逆遍历
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {//注意这里不能判断是否为1 因为值已经变化了, 可以判断是否大于0
                if (i < m - 1) {
                    mat[i][j] = Math.min(mat[i][j], mat[i + 1][j] + 1);
                }
                if (j < n - 1) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][j + 1] + 1);
                }
            }
        }
        return mat;
    }

    /**
     * 动态规划 :
     * 找到上下左右的距离最长的位置.
     * 若是为0,则更新为0, 进行广度的遍历, 只要距离比原有的小.
     * ----------------这种方式太慢了 时间5%  空间5%
     */
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    //广度遍历,知道mat=0或者ans<step
                    updateMatrix(mat, ans, m, n, i - 1, j, 1);
                    updateMatrix(mat, ans, m, n, i, j - 1, 1);
                    updateMatrix(mat, ans, m, n, i, j + 1, 1);
                    updateMatrix(mat, ans, m, n, i + 1, j, 1);
                }
            }
        }
        return ans;
    }

    private static void updateMatrix(int[][] mat, int[][] ans, int m, int n,
                                     int i, int j, int step) {
        if (i < 0 || j < 0 || i >= m || j >= n || mat[i][j] == 0) {
            return;
        }
        if (ans[i][j] != 0 && ans[i][j] <= step) {
            return;
        }
        ans[i][j] = step;
        updateMatrix(mat, ans, m, n, i - 1, j, step + 1);
        updateMatrix(mat, ans, m, n, i, j - 1, step + 1);
        updateMatrix(mat, ans, m, n, i, j + 1, step + 1);
        updateMatrix(mat, ans, m, n, i + 1, j, step + 1);
    }
}
