package com.welph.leecode.part_1_500.part_21_40;

import java.util.Arrays;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 空白格用'.'表示。
 * <p>
 * Note:
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的
 */
public class Solution37 {

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(board);
        for (char[] chs : board) {
            System.out.println(Arrays.toString(chs));
        }
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] ydp = new boolean[9][9];
        boolean[][] xdp = new boolean[9][9];
        boolean[][][] xy = new boolean[3][3][9];
        char ch;
        int index;
        int x;
        for (int i = 0; i < 9; i++) {
            x = i / 3;
            for (int j = 0; j < 9; j++) {
                ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                index = ch - '1';
                ydp[i][index] = true;
                xdp[j][index] = true;
                xy[x][j / 3][index] = true;
            }
        }
        //深度优化搜索，因为是唯一解。则对应值可能存放多个位置
        fill(ydp, xdp, xy, board, 0, 0);
    }

    private static boolean fill(boolean[][] ydp, boolean[][] xdp, boolean[][][] xy,
                                char[][] board, int i, int j) {
        if (i > 8) {
            return true;
        }
        if (board[i][j] != '.') {
            return fill(ydp, xdp, xy, board, i + (j == 8 ? 1 : 0), (j == 8 ? 0 : j + 1));
        }
        int x = i / 3;
        int y = j / 3;
        for (int k = 0; k < 9; k++) {
            if (ydp[i][k] || xdp[j][k] || xy[x][y][k]) {
                continue;
            }
            ydp[i][k] = true;
            xdp[j][k] = true;
            xy[x][y][k] = true;
            board[i][j] = (char) (k + '1');
            if (!fill(ydp, xdp, xy, board, i + (j == 8 ? 1 : 0), (j == 8 ? 0 : j + 1))) {
                ydp[i][k] = false;
                xdp[j][k] = false;
                xy[x][y][k] = false;
                board[i][j] = '.';
            } else {
                return true;
            }
        }
        return false;
    }

}
