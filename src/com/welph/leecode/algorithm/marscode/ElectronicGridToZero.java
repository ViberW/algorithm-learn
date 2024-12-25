package com.welph.leecode.algorithm.marscode;

import java.util.*;

/**
 * 在一个古老的实验室里，两个研究员，小星和小月，获得了一个 m x n 的电路图，表示为二进制矩阵 grid。
 * 在这个矩阵中，他们可以对任意一个电路单元进行翻转操作。
 *
 * 翻转操作会将所选单元的状态从 0 改为 1，或从 1 改为 0，同时影响与其相邻的上下左右单元。
 *
 * 小星和小月希望通过最少的翻转次数，将整个电路图变成全 0 的状态。如果这个目标无法实现，则返回 -1。
 */
public class ElectronicGridToZero {

    //{@link PressLightBulb} {@link Solution319}  和这些还不太一样
    public static int solution(int[][] grid) {
        //递归+剪枝
        int m = grid.length;
        int n = grid[0].length;
        long startState = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startState |= (1L << (i * n + j));
                }
            }
        }
        if (startState == 0) {
            return 0;
        }
        int[] directions = {0, 1, 0, -1, 0, 0};
        int result = 0;
        Queue<Long> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        queue.add(startState);
        visited.add(startState);
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            for (int x = 0; x < size; x++) {
                Long poll = queue.poll();
                //针对所有点进行反转
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        long state = poll;
                        for (int k = 0; k < 5; k++) {
                            int ni = i + directions[k];
                            int nj = j + directions[k + 1];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                                state ^= (1L << (ni * n + nj));
                            }
                        }
                        if (state == 0) {
                            return result;
                        }
                        if (!visited.contains(state)) {
                            visited.add(state);
                            queue.add(state);
                        }
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[][]{new int[]{0, 1}, new int[]{1, 0}}) == 2);
        System.out.println(solution(new int[][]{new int[]{1, 0}, new int[]{1, 1}}) == 1);
        System.out.println(solution(new int[][]{new int[]{0}}) == 0);
    }
}
