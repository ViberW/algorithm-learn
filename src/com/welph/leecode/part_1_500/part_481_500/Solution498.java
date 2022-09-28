package com.welph.leecode.part_1_500.part_481_500;

import java.util.Arrays;

/**
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * <p>
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 */
public class Solution498 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] ret = new int[n * m];
        // 一旦加到最大值, 则就需要
        boolean up = true;
        int l = 0;
        int r = 0;
        for (int i = 0; i < ret.length; i++) {
            ret[i] = mat[l][r];
            if (up) {
                if (r == n - 1) {
                    l++;
                    up = false;
                } else if (l == 0) {
                    r++;
                    up = false;
                } else {
                    l--;
                    r++;
                }
            } else {
                if (l == m - 1) {
                    r++;
                    up = true;
                } else if (r == 0) {
                    l++;
                    up = true;
                } else {
                    l++;
                    r--;
                }
            }
        }
        return ret;
    }
}
