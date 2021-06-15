package com.welph.leecode.algorithm.thinking;

import com.welph.leecode.algorithm.graph.shortestpath.structure.Edge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [差分约束系统]
 * .....................
 * xc1 -xc`1 <= y1
 * xc2 -xc`2 <= y2
 * ...
 * xcn -xc`n <= yn
 * ---------------------
 * 对于任意的表达式: x1-x2<=c 转化为了{ x1<x2+c } => 类似[最短路径问题]
 * .....................
 * @since 2021/6/11 15:17
 */
public class DifferentialConstraint_11 {

    /**
     * 给出一组包含 m 个不等式，有 n 个未知数的形如：
     * xc1 -xc`1 <= y1
     * xc2 -xc`2 <= y2
     * ...
     * xcn -xc`n <= yn
     * 的不等式组，求任意一组满足这个不等式组的解。
     * 输入格式
     * 第一行为两个正整数 n,m，代表未知数的数量和不等式的数量。
     * 接下来 m 行，每行包含三个整数 xcm, xc`m,ym ，代表一个不等式 xcm -xc`m <= ym。
     * 输出格式
     * 一行，n 个数，表示x1,x2....xn 的一组可行解，如果有多组解，请输出任意一组，无解请输出 NO。
     */
    public static void main(String[] args) {
        int m = 3;
        int n = 10;

        for (int i = 0; i < m; i++) {
            //相应的一些值
            add_edge(0, 0, 0, edges, head);
        }

        for (int i = 1; i <= n; ++i)
            add_edge(0, i, 0, edges, head);
        if (SPFA(0, n)) {
            for (int i = 1; i <= n; ++i)
                System.out.println(dis[i]);
        } else
            System.out.println("NO");
    }

    static int cnt_edge = 0;
    static int MAXN = 5005;
    static int MAXM = 10005;
    static Edge[] edges = new Edge[MAXM];
    static int[] head = new int[MAXN];
    static boolean[] inqueue = new boolean[MAXN]; //进入过
    static int cnt[] = new int[MAXN]; //每个点进入次数
    static int dis[] = new int[MAXN]; //结果存储
    static LinkedList<Integer> Q = new LinkedList<>();

    private static boolean SPFA(int s, int n) {
        Arrays.fill(dis, 127);//
        dis[s] = 0;
        Q.push(s);
        while (!Q.isEmpty()) {
            int p = Q.peek();
            if (cnt[p] > n)
                return false;
            Q.pop();
            inqueue[p] = false;
            for (int eg = head[p]; eg != 0; eg = edges[eg].next) {
                int to = edges[eg].to;
                if (edges[eg].w + dis[p] < dis[to]) {
                    dis[to] = edges[eg].w + dis[p];
                    if (!inqueue[to]) {
                        Q.push(to);
                        inqueue[to] = true;
                        cnt[to]++;
                    }
                }
            }
        }
        return true;
    }

    static void add_edge(int from, int to, int w, Edge[] edges, int[] head) {
        edges[++cnt_edge].next = head[from];
        edges[cnt_edge].to = to;
        edges[cnt_edge].w = w;
        head[from] = cnt_edge;
    }
}
