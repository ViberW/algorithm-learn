package com.welph.leecode.part_1_500.part_361_380;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个n x n矩阵matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * <p>
 * 示例 2：
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 * <p>
 * 提示：
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n^2
 */
public class Solution378 {

    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_341_360.Solution355} 类似推特消息的多数据排列
     * {@link Solution373}
     * --------------------
     * 因为这里不需要变更, 就不自己实现优先队列了
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[0]));
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{matrix[i][0], i, 0});
        }
        int i;
        int next;
        int ans = matrix[0][0];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (--k <= 0) {
                ans = poll[0];
                break;
            }
            i = poll[1];
            next = poll[2] + 1;
            if (next < n) {
                queue.add(new int[]{matrix[i][next], i, next});
            }
        }
        return ans;
    }


    /* 官方题解 */
    //二分查找法
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

}
