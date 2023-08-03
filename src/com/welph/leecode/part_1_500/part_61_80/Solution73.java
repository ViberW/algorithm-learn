package com.welph.leecode.part_1_500.part_61_80;

import java.util.Arrays;

/**
 * 给定一个m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p>
 * 示例1:
 * <p>
 * 输入:
 * [
 *  [1,1,1],
 *  [1,0,1],
 *  [1,1,1]
 * ]
 * 输出:
 * [
 *  [1,0,1],
 *  [0,0,0],
 *  [1,0,1]
 * ]
 * 示例2:
 * <p>
 * 输入:
 * [
 *  [0,1,2,0],
 *  [3,4,5,2],
 *  [1,3,1,5]
 * ]
 * 输出:
 * [
 *  [0,0,0,0],
 *  [0,4,5,0],
 *  [0,3,1,0]
 * ]
 * 进阶:
 * 一个直接的解决方案是使用 O(mn)的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m+n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */
public class Solution73 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}};
        setZeroes(matrix);

        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void setZeroes(int[][] matrix) {
        //尽量先把第一个位置的给搞定
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] flag = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    flag[j] = true;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (flag[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
