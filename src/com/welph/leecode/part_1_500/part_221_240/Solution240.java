package com.welph.leecode.part_1_500.part_221_240;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 示例 1：
 * 输入：matrix =
 * [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 * target = 5
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix =
 * [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 * target = 20
 * 输出：false
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class Solution240 {

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 4, 7, 11, 15 },
                { 2, 5, 8, 12, 19 },
                { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 } };
        System.out.println(searchMatrix(matrix, 25));
        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix(matrix, 9));
        System.out.println(searchMatrix(matrix, 17));
        System.out.println(searchMatrix(matrix, 30));
        System.out.println(searchMatrix(matrix, 18));
        System.out.println(searchMatrix(matrix, 20));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int length = matrix.length;
        int len = matrix[0].length;
        int i = 0;
        int l = 0;
        int r = len - 1;
        int mid;
        while (i < length) {
            mid = (l + r) / 2;
            if (matrix[i][mid] == target) {
                return true;
            } else if (matrix[i][mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            // 还可以改进-- 再通过row进行一次二分查找
            if (l > r) {
                r = mid;
                l = 0;
                i++;
            }
        }
        return false;
    }

    /* 官方题解 */
    // Z形查找(相当于Z形 不断缩小以二次缩小)
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1; // 一开始从右上角 进而缩小范围.牛的
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

    // 二分法(没有Z形查找快)
    public boolean searchMatrix3(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
