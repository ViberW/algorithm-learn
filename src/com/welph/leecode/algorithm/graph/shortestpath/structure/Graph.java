package com.welph.leecode.algorithm.graph.shortestpath.structure;

import java.util.LinkedList;

public class Graph {
    private LinkedList adj[]; // 邻接表
    private int v; // 顶点个数

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) { // 添加一条边
        this.adj[s].add(new Edge(s, t, w));
    }
}
