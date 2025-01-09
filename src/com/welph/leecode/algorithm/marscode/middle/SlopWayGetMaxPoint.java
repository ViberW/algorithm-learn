package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小U决定在一个  m×n 的地图上行走。地图中的每个位置都有一个高度，表示地形的高低。
 *
 * 小U只能在满足以下条件的情况下移动：
 * 只能上坡或者下坡，不能走到高度相同的点。
 * 移动时必须交替进行：上坡后必须下坡，下坡后必须上坡，不能连续上坡或下坡。
 * 每个位置只能经过一次，不能重复行走。
 *
 * 任务是帮助小U找到他在地图上可以移动的最大次数，即在符合所有条件的前提下，
 * 小U能走过的最大连续位置数量。
 *
 * 例如在以下2x2的地图中：
 * 1 2
 * 4 3
 * 中庸行者可以选择移动顺序 3 -> 4 -> 1 -> 2，最大移动次数为3。
 */
public class SlopWayGetMaxPoint {

    public static int solution(int m, int n, int[][] a) {
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        int[] directions = {0, 1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(dfs(m, n, a, directions, i, j, visited, 0) - 1, result);
            }
        }
        return result;
    }

    private static int dfs(int m, int n, int[][] a, int[] directions,
                           int i, int j, boolean[][] visited, int opt) {
        if (visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int result = 0;
        for (int k = 0; k < 4; k++) {
            int ni = i + directions[k];
            int nj = j + directions[k + 1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (opt != 1 && a[ni][nj] > a[i][j]) { //1代表小于
                    result = Math.max(result, dfs(m, n, a, directions, ni, nj, visited, 1));
                }
                if (opt != 2 && a[ni][nj] < a[i][j]) {//2代表大于, 这里小于,则下一次要大于
                    result = Math.max(result, dfs(m, n, a, directions, ni, nj, visited, 2));
                }
            }
        }
        visited[i][j] = false;
        return result + 1;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 2, new int[][]{{1, 2}, {4, 3}}) == 3);
        System.out.println(solution(3, 3, new int[][]{{10, 1, 6}, {5, 9, 3}, {7, 2, 4}}) == 8);
        System.out.println(solution(4, 4, new int[][]{{8, 3, 2, 1}, {4, 7, 6, 5}, {12, 11, 10, 9}, {16, 15, 14, 13}}) == 11);
    }
}
