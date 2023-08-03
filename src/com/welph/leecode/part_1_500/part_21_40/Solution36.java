package com.welph.leecode.part_1_500.part_21_40;

/**
 * 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * <p>
 * <url src="https://leetcode-cn.com/problems/valid-sudoku/"></>
 * <p>
 * 上图是一个部分填充的有效的数独。
 * <p>
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 */
public class Solution36 {

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
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku2(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        //只能一步步的放
        int[][] ydp = new int[9][9];
        boolean[][] xdp = new boolean[9][9];
        char ch;
        int index;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                index = ch - '1';
                if (ydp[i][index] > 0 || xdp[j][index]) {
                    return false;
                }
                for (int k = 1; k <= i % 3; k++) {
                    int x = ydp[i - k][index];
                    if (x > 0 && (x - 1) / 3 == j / 3) {
                        return false;
                    }
                }
                ydp[i][index] = j + 1;
                xdp[j][index] = true;
            }
        }
        return true;
    }

    public static boolean isValidSudoku2(char[][] board) {
        //只能一步步的放
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
                if (ydp[i][index] || xdp[j][index] || xy[x][j / 3][index]) {
                    return false;
                }
                xy[x][j / 3][index] = true;
                ydp[i][index] = true;
                xdp[j][index] = true;
            }
        }
        return true;
    }
}
