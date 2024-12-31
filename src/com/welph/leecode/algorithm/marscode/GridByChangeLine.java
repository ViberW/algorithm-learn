package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小F 正在操作一个正方形的二进制方阵 grid，他希望通过交换相邻的两行，调整方阵，使得主对角线以上的所有单元格都为 0。
 * 主对角线是从左上角到右下角 (1,1) 到 (n,n) 的一系列单元格。
 *
 * 你需要帮助小F 计算出，为了满足这个条件，最少需要进行多少次交换。如果无法达成要求，则返回 -1。
 */
public class GridByChangeLine {

    public static int solution(int[][] grid) {
        //即右上半部分都为0
        int n = grid.length;
        int[] counts = new int[n]; //第i行需存在counts[i]个右符合的0
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0 && grid[i][j] != 1; j--) {
                counts[i]++;
            }
        }
        int result = 0;
        for (int i = 0, count = n - 1; i < n; i++) {
            if (counts[i] < 0) {//已经被移动过了
                continue;
            }
            if (counts[i] < count) {
                int j = i + 1;
                for (; j < n; j++) {
                    if (counts[j] < 0) {
                        continue;
                    }
                    result++;
                    if (counts[j] >= count) {
                        counts[j] = -1;//移动
                        break;
                    }
                }
                if (j == n) {
                    return -1;
                }
                i--;
            }
            count--;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[][]{new int[]{0, 0, 1}, new int[]{1, 1, 0}, new int[]{1, 0, 0}}) == 3);
//        System.out.println(solution(new int[][]{new int[]{1, 0, 0}, new int[]{0, 1, 0}, new int[]{0, 0, 1}}) == 0);
//        System.out.println(solution(new int[][]{new int[]{0, 1, 0}, new int[]{0, 0, 1}, new int[]{1, 0, 0}}) == 2);
        System.out.println(solution(new int[][]{
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}}) == 5);
    }
}
