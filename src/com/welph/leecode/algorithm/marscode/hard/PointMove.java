package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小M有n个点,每个点的坐标为(2;y)。她可以从一个点出发,平行于坐标轴移动,直到到达另一个点。具体来说,她可以从
 * (1,y1)直接到达(2,y1)或者(21,y2),但无法直接到达(12,y2)。
 *
 * 为了使得任意两个点之间可以互相到达,小M可以选择增加若干个新的点。
 *
 * 你的任务是计算最少需要增加多少个点,才能保证任意两个点之间可以通过平行于坐标轴的路径互相到达
 */
public class PointMove {
    public static int solution(int n, int[][] points) {
        //并查集
        int[] father = new int[n];
        int[] treeSize = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            treeSize[i] = 1;
        }
        int rootI;
        int rootJ;
        for (int i = 1; i < n; i++) {
            //找到和i出相等的位置
            int x = points[i][0];
            int y = points[i][1];
            for (int j = i - 1; j >= 0; j--) {
                if (x != points[j][0] && y != points[j][1]) {
                    continue;
                }
                if ((rootI = findFather(father, i)) != (rootJ = findFather(father, j))) {
                    int weight = treeSize[rootI] + treeSize[rootJ];
                    // 将结点数少的集合作为结点数多的集合的儿子节点
                    if (treeSize[rootI] < treeSize[rootJ]) {
                        father[rootI] = rootJ;
                        treeSize[rootJ] = weight;
                    } else {
                        father[rootJ] = rootI;
                        treeSize[rootI] = weight;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            findFather(father, i);
        }
        return (int) Arrays.stream(father).distinct().count() - 1; //有count个数的不相交的范围, 就需要count-1个点连接
    }

    public static int findFather(int[] father, int i) {
        int root = i, temp;
        // 查找s的最顶层根
        while (father[root] != root) {
            root = father[root];
        }
        // 路径压缩，提高后续查找效率
        while (i != root) {
            temp = father[i];
            father[i] = root;
            i = temp;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new int[][]{{1, 1}, {2, 2}}) == 1);
        System.out.println(solution(3, new int[][]{{1, 2}, {2, 3}, {4, 1}}) == 2);
        System.out.println(solution(4, new int[][]{{3, 4}, {5, 6}, {3, 6}, {5, 4}}) == 0);
        System.out.println(solution(14, new int[][]{{15, 6}, {15, 4}, {11, 12}, {3, 4}, {6, 14}, {5, 12}, {9, 17}, {2, 6}, {16, 16}, {5, 3}, {16, 11}, {10, 13}, {16, 8}, {14, 1}}));
    }
}
