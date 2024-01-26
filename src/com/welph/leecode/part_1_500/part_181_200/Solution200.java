package com.welph.leecode.part_1_500.part_181_200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * 输入：grid = [
 * . ["1","1","1","1","0"],
 * . ["1","1","0","1","0"],
 * . ["1","1","0","0","0"],
 * . ["0","0","0","0","0"]
 * . ]
 * . 输出：1
 * .
 * . 示例 2：
 * . 输入：grid = [
 * . ["1","1","0","0","0"],
 * . ["1","1","0","0","0"],
 * . ["0","0","1","0","0"],
 * . ["0","0","0","1","1"]
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
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        };
        System.out.println(numIslands(grid));

        char[][] grid1 = { { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' } };
        System.out.println(numIslands(grid1));
    }

    // 并查集
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
                                int[] res = new int[] { i, j };
                                merge(fn, res, j);
                                fn[j] = res;
                            }
                        } else {
                            fn[j] = new int[] { i, j };
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

    /* 官方题解 */
    // 也是并查集
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n]; // 比我的好, 减少空间 而且计算方便
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j; // 这里的形式
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count; // 两段合并
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    // 广度优先
    public int numIslands4(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    // 通过广度, 把当前节点关联的所有点置为0 , 深度优先也是同样的策略
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

}
