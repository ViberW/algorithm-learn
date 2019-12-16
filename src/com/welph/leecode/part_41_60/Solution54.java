package com.welph.leecode.part_41_60;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Solution54 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int minx = 0;
        int miny = 0;
        int maxx = matrix.length - 1;
        int maxy = matrix[0].length - 1;
        while (minx <= maxx && miny <= maxy) {
            for (int y = minx; y <= maxy; y++) list.add(matrix[minx][y]);
            for (int x = minx + 1; x <= maxx; x++) list.add(matrix[x][maxy]);
            if (minx < maxx && miny < maxy) {
                for (int y = maxy - 1; y > miny; y--) list.add(matrix[maxx][y]);
                for (int x = maxx; x > minx; x--) list.add(matrix[x][miny]);
            }
            minx++;
            miny++;
            maxx--;
            maxy--;
        }
        return list;
    }
}
