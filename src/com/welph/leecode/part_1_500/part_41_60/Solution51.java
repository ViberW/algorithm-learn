package com.welph.leecode.part_1_500.part_41_60;

import java.util.ArrayList;
import java.util.List;

/**
 * 著名的问题： n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class Solution51 {


    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    /**
     * 深度优先搜索
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> rs = new ArrayList<>();
        boolean[][] arr = new boolean[n][n];
        settle(arr, 0, n, rs);
        return rs;
    }

    private static String build(boolean[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static boolean settle(boolean[][] arr, int y, int n, List<List<String>> rs) {
        if (y == n) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(build(arr[j]));
            }
            rs.add(list);
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int x = 0; x < n; x++) {
                arr[y][x] = false;
            }
            if (check(arr, i, y, n)) {
                arr[y][i] = true;
                settle(arr, y + 1, n, rs);
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
