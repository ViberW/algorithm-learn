package com.welph.leecode.part_1_500.part_281_300;

import java.util.Arrays;

/**
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：
 * 1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * <p>
 * 示例 1：
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * 示例 2：
 * 输入：board = [[1,1],[1,0]]
 * 输出：[[1,1],[1,1]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] 为 0 或 1
 * <p>
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class Solution289 {

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        print(board);
    }

    /**
     * 若1 &count1<2, 转为0
     * //若1 &count1==2|count1==3,不动
     * 若1 &count1>3,转为0
     * 若0 &count1==3,转为1
     * ------------ 影响的节点 --不使用额外的空间, 那用3表示0-1 4表示1-0 存储中间态
     * b[i][j-1]
     * b[i-1][j-1]
     * b[i-1][j]
     * b[i-1][j+1]
     */
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                if (i > 0) {
                    if (j > 0 && (board[i - 1][j - 1] & 1) == 1) {
                        count++;
                    }
                    if ((board[i - 1][j] & 1) == 1) {
                        count++;
                    }
                    if (j < n - 1 && (board[i - 1][j + 1] & 1) == 1) {
                        count++;
                    }
                }
                if (j > 0 && (board[i][j - 1] & 1) == 1) {
                    count++;
                }
                if (j < n - 1 && (board[i][j + 1] & 1) == 1) {
                    count++;
                }
                if (i < m - 1) {
                    if (j > 0 && (board[i + 1][j - 1] & 1) == 1) {
                        count++;
                    }
                    if ((board[i + 1][j] & 1) == 1) {
                        count++;
                    }
                    if (j < n - 1 && (board[i + 1][j + 1] & 1) == 1) {
                        count++;
                    }
                }
                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        //1->0
                        board[i][j] = 3;
                    }
                } else if (count == 3) {
                    //0->1
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                } else if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void print(int[][] board) {
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
