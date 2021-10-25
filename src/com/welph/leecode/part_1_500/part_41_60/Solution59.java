package com.welph.leecode.part_1_500.part_41_60;

import java.util.Arrays;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class Solution59 {

    public static void main(String[] args) {
        int[][] result = generateMatrix(4);
        for (int[] a : result) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) {
            return matrix;
        }
        int minx = 0;
        int miny = 0;
        int maxx = n - 1;
        int maxy = n - 1;
        int i = 1;
        while (minx <= maxx && miny <= maxy) {
            for (int y = minx; y <= maxy; y++) matrix[minx][y] = i++;
            for (int x = minx + 1; x <= maxx; x++) matrix[x][maxy] = i++;
            for (int y = maxy - 1; y > miny; y--) matrix[maxx][y] = i++;
            for (int x = maxx; x > minx; x--) matrix[x][miny] = i++;
            minx++;
            miny++;
            maxx--;
            maxy--;
        }
        return matrix;
    }
}
