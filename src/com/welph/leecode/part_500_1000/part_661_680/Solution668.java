package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第 k 小的数字吗？
 * 乘法表是大小为 m x n 的一个整数矩阵，其中 mat[i][j] == i * j（下标从 1 开始）。
 * 给你三个整数 m、n 和 k，请你在大小为 m x n 的乘法表中，找出并返回第 k 小的数字。
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 3, k = 5
 * 输出：3
 * 解释：第 5 小的数字是 3 。
 * <p>
 * 示例 2：
 * 输入：m = 2, n = 3, k = 6
 * 输出：6
 * 解释：第 6 小的数字是 6 。
 * <p>
 * 提示：
 * 1 <= m, n <= 3 * 10^4
 * 1 <= k <= m * n
 */
public class Solution668 {

    public static void main(String[] args) {
        System.out.println(findKthNumber(3, 3, 5));
        System.out.println(findKthNumber(2, 3, 6));
    }

    /**
     * 以i为例, i的值左上均小于i,  i的右下均大于i
     */
    public static int findKthNumber(int m, int n, int k) {
        //找到范围值
        int minLine = Math.min(m, n);
        double sqrt = Math.sqrt(k);
        if (minLine < sqrt) {
            //说明在另一边

        } else {
            //当前范围内.
        }
        return 0;
    }
}
