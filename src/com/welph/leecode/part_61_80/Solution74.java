package com.welph.leecode.part_61_80;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class Solution74 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int[][] a = {{1}};
        System.out.println(searchMatrix(a, 0));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int l = 0;
        int r = matrix.length - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (target > matrix[mid][0]) {
                l = mid + 1;
            } else if (target < matrix[mid][0]) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        int[] matrix1 = matrix[r < 0 ? l : Math.min(l, r)];
        l = 0;
        r = matrix1.length - 1;
        while (l <= r) {
            mid = (l + r) / 2;
            if (target > matrix1[mid]) {
                l = mid + 1;
            } else if (target < matrix1[mid]) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
