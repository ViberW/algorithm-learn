package com.welph.leecode.algorithm.z7z8;

/**
 * 在一个二维地图中，数值代表此处山的高度，在某个点只能滑向上下左右4个相邻的点，最远的滑行路线，也就等价于找出一条最长的数值下降路线。
 * 比如下图中的红色路线就是此时最长的一条路线，长度为10。那要如何找出这样的一条路线呢？
 */
public class BestSkiing {

    public static void main(String[] args) {
        int[][] area = new int[][]{
                {1, 2, 3, 4, 3},
                {12, 13, 14, 5, 5},
                {11, 10, 15, 6, 6},
                {10, 9, 8, 8, 17},
                {9, 8, 7, 6, 7},
        };
        System.out.println(skiing(area));
        System.out.println(skiingBaoli(area));
    }

    /**
     * 记忆化搜索
     */
    private static int skiing(int[][] area) {
        return 0;
    }

    /**
     * 暴力写法
     */
    private static int skiingBaoli(int[][] area) {
        int n = area.length;
        int m = area[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(findSki(area, i, j), max);
            }
        }
        return max;
    }

    private static int findSki(int[][] area, int i, int j) {
        int ret = 0;
        if (i > 0 && area[i][j] > area[i - 1][j]) {
            ret = Math.max(ret, findSki(area, i - 1, j));
        }
        if (i < area.length - 1 && area[i][j] > area[i + 1][j]) {
            ret = Math.max(ret, findSki(area, i + 1, j));
        }
        if (j > 0 && area[i][j] > area[i][j - 1]) {
            ret = Math.max(ret, findSki(area, i, j - 1));
        }
        if (j < area.length - 1 && area[i][j] > area[i][j + 1]) {
            ret = Math.max(ret, findSki(area, i, j + 1));
        }
        return ret + 1;
    }
}
