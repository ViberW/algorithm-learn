package com.welph.leecode.part_1_500.part_401_420;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * <p>
 * 提示：
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 * 示例：
 * <p>
 * 给定下面的 5x5 矩阵:
 * | 太平洋 ~ ~ ~ ~ ~
 * | ~ 1 2 2 3 (5) *
 * | ~ 3 2 3 (4) (4) *
 * | ~ 2 4 (5) 3 1 *
 * | ~ (6) (7) 1 4 5 *
 * | ~ (5) 1 1 2 4 *
 * | * * * * * 大西洋
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 */
public class Solution417 {

    public static void main(String[] args) {
        /*
         * System.out.println(pacificAtlantic(new int[][]{
         * {1, 2, 2, 3, 5},
         * {3, 2, 3, 4, 4},
         * {2, 4, 5, 3, 1},
         * {6, 7, 1, 4, 5},
         * {5, 1, 1, 2, 4}
         * }));
         * System.out.println(pacificAtlantic(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6,
         * 5}}));
         */
        System.out.println(
                pacificAtlantic(new int[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 }, { 10, 9, 8, 7 } }));
    }

    /**
     * 思考: 一个点[x,y] 若是能够到达一条边,说明它的上下左右, 的表示至少存在太平洋和大西洋的流向
     * 分别从四个边查找, 一直递增就可以了
     */
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] ret1 = new boolean[m][n];// 太平洋
        boolean[][] ret2 = new boolean[m][n];// 大西洋
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, m, n, ret1, heights[i][0]);
            dfs(heights, i, n - 1, m, n, ret2, heights[i][n - 1]);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, 0, i, m, n, ret1, heights[0][i]);
            dfs(heights, m - 1, i, m, n, ret2, heights[m - 1][i]);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ret1[i][j] && ret2[i][j]) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(i);
                    integers.add(j);
                    result.add(integers);
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] heights, int i, int j, int m, int n, boolean[][] visited, int value) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || heights[i][j] < value) {
            return;
        }
        visited[i][j] = true;
        dfs(heights, i + 1, j, m, n, visited, heights[i][j]);
        dfs(heights, i - 1, j, m, n, visited, heights[i][j]);
        dfs(heights, i, j + 1, m, n, visited, heights[i][j]);
        dfs(heights, i, j - 1, m, n, visited, heights[i][j]);
    }

    /* 官方的另一种题解 广度优先搜索 */
    static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            bfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            bfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            bfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            bfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void bfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[] { row, col });
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int newRow = cell[0] + dir[0], newCol = cell[1] + dir[1];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && heights[newRow][newCol] >= heights[cell[0]][cell[1]] && !ocean[newRow][newCol]) {
                    ocean[newRow][newCol] = true;
                    queue.offer(new int[] { newRow, newCol });
                }
            }
        }
    }

}
