package com.welph.leecode.part_1_500.part_401_420;

/**
 * 给定一个二维的甲板， 请计算其中有多少艘战舰。 战舰用 'X'表示，空位用 '.'表示。 你需要遵守以下规则：
 * <p>
 * 给你一个有效的甲板，仅由战舰或者空位组成。
 * 战舰只能水平或者垂直放置。换句话说,战舰队只能由 1xN (1 行, N 列)组成，或者 Nx1 (N 行, 1 列)组成，其中N可以是任意大小。
 * 两艘战舰队之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。
 * 示例 :
 * X..X
 * ...X
 * ...X
 * 在上面的甲板中有2队战舰队。
 * <p>
 * 无效样例 :
 * ...X
 * XXXX
 * ...X
 * 你不会收到这样的无效甲板 - 因为战舰之间至少会有一个空位将它们分开。
 * <p>
 * 进阶:
 * 你可以用一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值来解决这个问题吗？
 */
public class Solution418 {

    public static void main(String[] args) {
        System.out.println(countBattleships(new char[][] {
                { 'X', '.', '.', 'X' },
                { '.', '.', '.', 'X' },
                { '.', '.', '.', 'X' }
        }));
    }

    public static int countBattleships(char[][] board) {
        int sum = 0;
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, m, n, visited, 15)) {
                    sum++;
                }
                visited[i][j] = true;
            }
        }
        return sum;
    }

    private static boolean dfs(char[][] board, int i, int j, int m, int n, boolean[][] visited, int k) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || board[i][j] != 'X') {
            return false;
        }
        visited[i][j] = true;
        if ((k & 1) == 1) {
            dfs(board, i + 1, j, m, n, visited, 1);
        }
        if ((k & 2) == 2) {
            dfs(board, i - 1, j, m, n, visited, 2);
        }
        if ((k & 4) == 4) {
            dfs(board, i, j + 1, m, n, visited, 4);
        }
        if ((k & 8) == 8) {
            dfs(board, i, j - 1, m, n, visited, 8);
        }
        return true;
    }

    /* 官方题解 */
    // 遍历扫描
    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; ++k) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; ++k) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    // 当前次比较上一次是否也为x则跳过. 相当于是不是一条队列
    public int countBattleships3(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

}
