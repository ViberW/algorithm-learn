package com.welph.leecode.part_121_140;

import java.util.Arrays;

/**
 * 给定一个二维的矩阵，包含'X'和'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * 'X','X','X','X'
 * 'X','O','O','X'
 * 'X','X','O','X'
 * 'X','O','X','X'
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，
 * 换句话说，任何边界上的'O'都不会被填充为'X'。
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class Solution129 {

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };

        char[][] board1 = {
                {'O', 'X', 'O'},
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
        };

        char[][] board2 = {
                {'O', 'O', 'O', 'O', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'O', 'O'}
        };


        char[][] board3 = {
                {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O'},
                {'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'}};

        char[][] board4 = {
                {'O', 'X', 'O', 'O', 'X', 'X'},
                {'O', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O'},
                {'O', 'O', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O', 'O', 'O'}};


        char[][] board5 = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X', 'X'}};
        solve(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    /**
     * 找出所有同边界相连的'O',其他的都可以调整'X'
     * <p>
     */
    public static void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int xl = board.length;
        int yl = board[0].length;
        boolean[][] crossed = new boolean[xl][yl];
        //从四周出发,找到所有和边相连的点并进行标记
        for (int i = 0; i < xl; i++) {
            closed(board, crossed, i, 0, xl, yl);
            closed(board, crossed, i, yl - 1, xl, yl);
        }
        for (int i = 0; i < yl; i++) {
            closed(board, crossed, 0, i, xl, yl);
            closed(board, crossed, xl - 1, i, xl, yl);
        }
        for (int x = 1; x < xl - 1; x++) {
            for (int y = 1; y < yl - 1; y++) {
                if ('O' == board[x][y] && !crossed[x][y]) {
                    board[x][y] = 'X';
                }
            }
        }
    }

    public static void closed(char[][] board, boolean[][] crossed,
                              int x, int y, int xl, int yl) {
        if (x >= 0 && x < xl && y >= 0 && y < yl) {
            if (!crossed[x][y] && board[x][y] == 'O') {
                crossed[x][y] = true;
                closed(board, crossed, x + 1, y, xl, yl);
                closed(board, crossed, x - 1, y, xl, yl);
                closed(board, crossed, x, y + 1, xl, yl);
                closed(board, crossed, x, y - 1, xl, yl);
            }
        }
    }
}
