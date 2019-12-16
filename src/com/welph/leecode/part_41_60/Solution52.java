package com.welph.leecode.part_41_60;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 */
public class Solution52 {

    public static void main(String[] args) {
        System.out.println(totalNQueens(1));
    }


    public static int totalNQueens(int n) {
        boolean[][] arr = new boolean[n][n];
        AtomicInteger count = new AtomicInteger();
        settle(arr, 0, n, count);
        return count.get();
    }

    private static boolean settle(boolean[][] arr, int y, int n, AtomicInteger count) {
        if (y == n) {
            count.incrementAndGet();
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int x = 0; x < n; x++) {
                arr[y][x] = false;
            }
            if (check(arr, i, y, n)) {
                arr[y][i] = true;
                settle(arr, y + 1, n, count);
            }
        }
        return false;
    }

    private static boolean check(boolean[][] arr, int x, int y, int n) {
        for (int i = 0; i < y; i++) {
            if (arr[i][x]) {
                return false;
            }
            if (x - 1 - i >= 0 && arr[y - 1 - i][x - 1 - i]) {
                return false;
            }
            if (x + 1 + i < n && arr[y - 1 - i][x + 1 + i]) {
                return false;
            }
        }
        return true;
    }
}
