package com.welph.leecode.algorithm.z7z8;

import com.welph.leecode.algorithm.graph.shortestpath.structure.Edge;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 穷乡僻壤，人烟稀少，如何布局网线，成了当下村委会首个急需攻克的难题
 * --如果农户A到农户B，农户B到农户C的网线已经建好了，那农户A和农户C也间接的连通了，不用再建设
 * <p>
 * ------------------
 * 本质上就是寻找无向图中N个顶点对应的N-1个边的最小权值和;
 * 问题就转化为经典的最小生成树
 * Prim算法和Kruskal算法
 */
public class ContactVillage {

    static Random random = new Random();

    public static void main(String[] args) {
        int len = 10;
        int[][] ans = new int[len][len];
        init(ans, len);
        System.out.println("===============");
        for (int[] an : ans) {
            System.out.println(Arrays.toString(an));
        }
        System.out.println("===============");
        System.out.println(prim(ans));
        System.out.println(kruskal(ans));
    }

    private static void init(int[][] ans, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int distance = random.nextInt(100);
                ans[i][j] = distance;
                ans[j][i] = distance;
            }
        }
    }

    /**
     * -----------------Prim算法-----------------------------
     * 对于加权连通图G=(V,E)，V为顶点集，E为边集。
     * <p>
     * 以V中任意一点x为起点，将x加入一个新的顶点集S={x}，初始新的边集T={}为空
     * 重复如下步骤直到S=V：
     * 1）选择E中最小的边<u,v>，其中u属于S，而v不属于S但属于V
     * 2）将v加入S，将边<u,v>加入T
     * 最终S,T即为所求最小生成树
     * 算法解释：把S和非S想象成两个子图，每一步其实就是在找出这两个子图之间的最小边。
     * ---------------适用于点少边多的场景-----通过邻接矩阵保存顶点和边;
     */
    public static int prim(int[][] ans) {
        int res = 0;
        int l = ans.length;
        boolean[] flag = new boolean[l];
        int[] length = new int[l];
        // 将0作为起点加入集合S
        for (int i = 0; i < l; ++i) {
            length[i] = ans[0][i];
        }
        // 选择N-1条边
        for (int i = 0; i < l - 1; ++i) {
            int min = 0x7fffffff;
            int k = 0;
            // 枚举非S所有点，选择最小的边
            for (int j = 1; j < l; ++j) {
                if (!flag[j] && length[j] < min) {
                    min = length[j];
                    k = j;
                }
            }
            res += min;
            // 将新的点k加入集合S,并通过k更新非S中点的距离
            flag[k] = true;
            for (int j = 1; j < l; ++j) {
                if (!flag[j] && ans[k][j] >= 0 && ans[k][j] < length[j]) {
                    length[j] = ans[k][j];
                }
            }
        }
        return res;
    }

    /**
     * ------------------Kruskal算法-----------------
     * 对于加权连通图G=(V,E)，V为顶点集，E为边集。
     * 初始一个非连通图T=(V,{})，即含所有点，边集为空
     * 重复以下步骤，直到成功选择N-1条边
     * 1）在E中取出最小边<u,v>，如果u，v没有连通，就将该边加入T，同时将u,v连通；否则舍弃判断下一条最小边。
     * 最终T即为所求最小生成树
     * >>>>>>>>>> [并查集]
     * -------------适用于边少点多的场景-----通过邻接表保存顶点和边;
     */
    public static int kruskal(int[][] ans) {
        Edge[] edges = convertEdge(ans);
        //预先排序
        Arrays.sort(edges, Comparator.comparingInt(value -> value.w));
        int l = edges.length;
        int edgeNum = 0;
        int res = 0;
        // 并查集根结点，初始为-1，合并之后为-num,num表示集合中的个数
        int[] father = new int[l];
        int[] treeSize = new int[l];
        for (int i = 0; i < l; i++) {
            father[i] = i;
            treeSize[i] = 1;
        }
        for (int i = 0; i < l; i++) {
            //说明不是同一个集合
            if (findFather(father, edges[i].sid) != findFather(father, edges[i].tid)) {
                unionSet(father, treeSize, edges[i].sid, edges[i].tid);
                res += edges[i].w;
                edgeNum++;
                if (edgeNum == l - 1) {
                    break;
                }
            }
        }
        return res;
    }

    //下面两个方法 来源于 并查集 处理
    public static int findFather(int[] father, int s) {
        int root = s, temp;
        // 查找s的最顶层根
        while (father[root] != root) {
            root = father[root];
        }
        // 路径压缩，提高后续查找效率
        while (s != root) {
            temp = father[s];
            father[s] = root;
            s = temp;
        }
        return root;
    }

    //这里可以使用单独的rank[]数组用于保存集合的大小, 而并非root值的大小
    public static void unionSet(int[] father, int[] treeSize, int s, int e) {
        int rootS = findFather(father, s);
        int rootE = findFather(father, e);

        int weight = treeSize[rootS] + treeSize[rootE];
        // 将结点数少的集合作为结点数多的集合的儿子节点
        if (treeSize[rootS] > treeSize[rootE]) {
            father[rootS] = rootE;
            treeSize[rootE] = weight;
        } else {
            father[rootE] = rootS;
            treeSize[rootS] = weight;
        }
    }

    private static Edge[] convertEdge(int[][] ans) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            for (int j = i + 1; j < ans.length; j++) {
                edges.add(new Edge(i, j, ans[i][j]));
            }
        }
        return edges.toArray(new Edge[0]);
    }

}
