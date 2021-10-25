package com.welph.leecode.part_1_500.part_401_420;

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
     * *    +--------------- 一维(小则越靠里)
     * *   /
     * *  /
     * * /
     * * 二维(高度)
     */
    public static void main(String[] args) {
        System.out.println(trapRainWater(new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_41_60.Solution42}
     */
    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int ans = 0;
        //尝试使用动态规划看看
        //左右
        int[][] ls = new int[m][n];
        int[][] rs = new int[m][n];
        int[] height;
        int[] t;
        for (int i = 0; i < m; i++) {
            t =  ls[i];
            height = heightMap[i];
            t[0] = height[0];
            for (int j = 1; j < n; j++) {
                t[j] = Math.max(height[j], t[j - 1]);
            }
            //计算右边
            //todo 等待处理
            /*right_max[size - 1] = height[size - 1];
            for (int i = size - 2; i >= 0; i--) {
                right_max[i] = Math.max(height[i], right_max[i + 1]);
            }*/
        }
        //前后
        int[][] fs = new int[n][m];
        int[][] bs = new int[n][m];

        return ans;
    }
}
