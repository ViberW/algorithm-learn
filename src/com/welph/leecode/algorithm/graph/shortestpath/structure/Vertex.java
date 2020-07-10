package com.welph.leecode.algorithm.graph.shortestpath.structure;

public class Vertex {
    public int id; // 顶点编号ID
    public int dist; // 从起始顶点到这个顶点的距离
    public int f;
    public int x, y; // 新增：顶点在地图中的坐标（x, y）

    public Vertex(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.dist = Integer.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
        this.x = x;
        this.y = y;
    }
}
