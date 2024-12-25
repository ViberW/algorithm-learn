package com.welph.leecode.algorithm.marscode;

/**
 * 小F 正在操作一个正方形的二进制方阵 grid，他希望通过交换相邻的两行，调整方阵，使得主对角线以上的所有单元格都为 0。
 * 主对角线是从左上角到右下角 (1,1) 到 (n,n) 的一系列单元格。
 *
 * 你需要帮助小F 计算出，为了满足这个条件，最少需要进行多少次交换。如果无法达成要求，则返回 -1。
 */
public class GridByChangeLine {

    public static int solution(int[][] grid) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{new int[]{0, 0, 1}, new int[]{1, 1, 0}, new int[]{1, 0, 0}}) == 3);
        System.out.println(solution(new int[][]{new int[]{1, 0, 0}, new int[]{0, 1, 0}, new int[]{0, 0, 1}}) == 0);
        System.out.println(solution(new int[][]{new int[]{0, 1, 0}, new int[]{0, 0, 1}, new int[]{1, 0, 0}}) == 2);
    }
}
