package com.welph.leecode.part_500_1000.part_541_560;

import com.welph.leecode.algorithm.z7z8.ContactVillage;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <p>
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * <p>
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class Solution547 {

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{
                {1, 1, 0}, {1, 1, 0}, {0, 0, 1}
        }));
        System.out.println(findCircleNum(new int[][]{
                {1, 0, 0}, {0, 1, 0}, {0, 0, 1}
        }));
    }

    /**
     * 并查集
     * {@link ContactVillage}
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] father = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    if (findFather(father, i) != findFather(father, j)) {
                        union(father, size, i, j);
                    }
                }
            }
        }
        //查找不在范围内的数据
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (father[i] == i) { //一组
                ret++;
            }
        }
        return ret;
    }

    private static void union(int[] father, int[] size, int i, int j) {
        int rootI = findFather(father, i);
        int rootJ = findFather(father, j);
        int totalSize = size[rootI] + size[rootJ];
        if (size[rootI] > size[rootJ]) {
            father[rootJ] = rootI;
            size[rootI] = totalSize;
        } else {
            father[rootI] = rootJ;
            size[rootJ] = totalSize;
        }
    }

    private static int findFather(int[] father, int i) {
        int root = i;
        while (father[root] != root) {
            root = father[root];
        }
        if (i != root) {
            father[i] = root;
        }
        return root;
    }
}
