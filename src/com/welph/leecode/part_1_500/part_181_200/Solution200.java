package com.welph.leecode.part_1_500.part_181_200;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * 输入：grid = [
 * .   ["1","1","1","1","0"],
 * .   ["1","1","0","1","0"],
 * .   ["1","1","0","0","0"],
 * .   ["0","0","0","0","0"]
 * . ]
 * . 输出：1
 * .
 * . 示例 2：
 * . 输入：grid = [
 * .   ["1","1","0","0","0"],
 * .   ["1","1","0","0","0"],
 * .   ["0","0","1","0","0"],
 * .   ["0","0","0","1","1"]
 * . ]
 * . 输出：3
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class Solution200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));

        char[][] grid1 = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(grid1));
    }

    //并查集
    public static int numIslands(char[][] grid) {
        int len = grid.length;
        if (len == 0) {
            return 0;
        }
        int size = grid[0].length;
        int[][] fn = new int[size + 1][];
        int count = 0;
        char[] g;
        for (int i = 0; i <= len; i++) {
            g = null;
            if (i != len) {
                g = grid[i];
                for (int j = 1; j <= size; j++) {
                    if (g[j - 1] == '1') {
                        if (fn[j - 1] != null && fn[j - 1][0] == i) {
                            if (fn[j] != null) {
                                merge(fn, fn[j - 1], j);
                            }
                            fn[j] = fn[j - 1];
                        } else if (fn[j] != null) {
                            int[] ints = find(fn, fn[j][0], fn[j][1]);
                            if (ints[0] == i) {
                                fn[j] = ints;
                            } else {
                                int[] res = new int[]{i, j};
                                merge(fn, res, j);
                                fn[j] = res;
                            }
                        } else {
                            fn[j] = new int[]{i, j};
                        }
                    }
                }
            }
            for (int j = 1; j <= size; j++) {
                if (i == len || g[j - 1] != '1') {
                    if (fn[j] != null) {
                        int[] ints = find(fn, fn[j][0], fn[j][1]);
                        if (null != ints && i != ints[0]) {
                            count++;
                        }
                    }
                    fn[j] = null;
                }
            }
        }
        return count;
    }

    private static int[] find(int[][] fn, int i, int j) {
        int[] ints = fn[j];
        if (null == ints) {
            return null;
        }
        return ints[0] == i && ints[1] == j ? ints : (fn[j] = find(fn, ints[0], ints[1]));
    }

    private static void merge(int[][] fn, int[] res, int j) {
        int[] ints = find(fn, fn[j][0], fn[j][1]);
        fn[ints[1]] = res;
    }
}
