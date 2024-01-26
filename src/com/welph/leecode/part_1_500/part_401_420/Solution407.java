package com.welph.leecode.part_1_500.part_401_420;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个m x n的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * <p>
 * 示例 1:
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * <p>
 * 示例2:
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 * <p>
 * 提示:
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 */
public class Solution407 {
    /**
     * * +--------------- 一维(小则越靠里)
     * * /
     * * /
     * * /
     * * 二维(高度)
     */
    public static void main(String[] args) {
        System.out.println(
                trapRainWater(new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_41_60.Solution42}
     */
    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int ans = 0;
        // 尝试使用动态规划看看
        // 左右
        int[][] ls = new int[m][n];
        int[][] rs = new int[m][n];
        int[] height;
        int[] t;
        for (int i = 0; i < m; i++) {
            t = ls[i];
            height = heightMap[i];
            t[0] = height[0];
            for (int j = 1; j < n; j++) {
                t[j] = Math.max(height[j], t[j - 1]);
            }
            // 计算右边
            // todo 等待处理
            /*
             * right_max[size - 1] = height[size - 1];
             * for (int i = size - 2; i >= 0; i--) {
             * right_max[i] = Math.max(height[i], right_max[i + 1]);
             * }
             */
        }
        // 前后
        int[][] fs = new int[n][m];
        int[][] bs = new int[n][m];

        return ans;
    }

    /* 官方题解 */
    /*
     * 最小堆
     * -----------------------------------------
     * 方块索引(i,j) 此时方块高度为heightMap[i][j] ,若方块接水后的高度为 water[i][j]
     * 则: water[i][j]=max(heightMap[i][j],
     * min(water[i-1][j],water[i][j-1],water[i+1][j],water[i][j+1]))
     * 那么 该方块的接水量= water[i][j]-heightMap[i][j]
     * 最外层的water[i][j]=heightMap[i][j]
     */
    public int trapRainWater2(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        // 通过最小堆, 保证四个方位的判断缩减到最低的一个就行
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);// 优先队列

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[] { i * n + j, heightMap[i][j] });// 第x个方块,方便一个字段保存i和j
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = { -1, 0, 1, 0, -1 };
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];// i
                int ny = curr[0] % n + dirs[k + 1];// j
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    // 因为我们始终是以最小的一块柱子的water放入队列.
                    // 那么这里只需要小于curr[1]. 说明当前方块至少能装水且高度为curr[1] - heightMap[nx][ny]
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    // 木桶原理: 木桶装水量为最小的一块板决定的
                    pq.offer(new int[] { nx * n + ny, Math.max(heightMap[nx][ny], curr[1]) });
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }

    // 广度优先搜索
    public int trapRainWater3(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dirs = { -1, 0, 1, 0, -1 };
        int maxHeight = 0;

        // 标记最大可能的water高度
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxHeight = Math.max(maxHeight, heightMap[i][j]);
            }
        }
        int[][] water = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                water[i][j] = maxHeight;
            }
        }

        Queue<int[]> qu = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (water[i][j] > heightMap[i][j]) {// 这个仅仅是减少了部分计算量(最高柱)
                        water[i][j] = heightMap[i][j];
                        qu.offer(new int[] { i, j });
                    }
                }
            }
        }
        while (!qu.isEmpty()) {
            int[] curr = qu.poll();
            int x = curr[0];
            int y = curr[1];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (water[x][y] < water[nx][ny] && water[nx][ny] > heightMap[nx][ny]) {
                    water[nx][ny] = Math.max(water[x][y], heightMap[nx][ny]);
                    qu.offer(new int[] { nx, ny });
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += water[i][j] - heightMap[i][j];
            }
        }
        return res;
    }

}
