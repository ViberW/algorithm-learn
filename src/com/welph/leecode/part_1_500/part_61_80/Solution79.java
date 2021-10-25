package com.welph.leecode.part_1_500.part_61_80;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class Solution79 {

    public static void main(String[] args) {
        /**
         * [["A","B","C","E"],["S","F","E","S"],["A","D","E","E"]]
         * "ABCESEEEFS"
         */
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        char[][] board1 = {
                {'A', 'B'},
                {'C', 'D'},
        };
        System.out.println(exist(board, "ABCESEEEFS"));
    }

    public static boolean exist(char[][] board, String word) {
        //回溯法
        int len = board.length;
        int length = board[0].length;
        boolean[][] exists = new boolean[len][length];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < length; j++) {
                if (exists(board, word, i, j, 0, len, length, exists)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exists(char[][] board, String word, int i, int j, int k,
                                  int len, int length, boolean[][] exists) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= len || j >= length) {
            return false;
        }
        if (exists[i][j]) {
            return false;
        }
        if (board[i][j] == word.charAt(k)) {
            exists[i][j] = true;
            k = k + 1;
            if (exists(board, word, i + 1, j, k, len, length, exists)) {
                return true;
            }
            if (exists(board, word, i - 1, j, k, len, length, exists)) {
                return true;
            }
            if (exists(board, word, i, j + 1, k, len, length, exists)) {
                return true;
            }
            if (exists(board, word, i, j - 1, k, len, length, exists)) {
                return true;
            }
            exists[i][j] = false;
        }
        return false;
    }
}
