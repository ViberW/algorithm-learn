package com.welph.leecode.part_1_500.part_41_60;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class Solution48 {

    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        for (int[] r : matrix) {
            System.out.println(Arrays.toString(r));
        }
    }

    public static void rotate(int[][] matrix) {
        //存放的位置是， r, c
        // 之后的位置是  c,n-1-r,  最终是个循环
        //第一次遍历，0，0   1，1   直到n/2为止
        int n = matrix.length;
        int temp;
        int cur;
        int r;
        int c;
        int tempR;
        for (int i = 0; i <= n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                r = i;
                c = j;
                cur = matrix[r][c];
                int count = 4;
                while (count-- > 0) {
                    tempR = r;
                    r = c;
                    c = n - 1 - tempR;
                    temp = matrix[r][c];
                    matrix[r][c] = cur;
                    cur = temp;
                }
            }
        }
    }
}
