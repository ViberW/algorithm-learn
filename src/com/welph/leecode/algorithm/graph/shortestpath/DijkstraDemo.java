package com.welph.leecode.algorithm.graph.shortestpath;

import com.welph.leecode.algorithm.graph.shortestpath.structure.Edge;
import com.welph.leecode.algorithm.graph.shortestpath.structure.Vertex;

import java.util.LinkedList;

public class DijkstraDemo {

    public static void main(String[] args) {

    }

    // 因为Java提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue { // 根据vertex.dist构建小顶堆
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = v;
        }

        public Vertex poll() { // TODO: 留给读者实现...
            return null;
        }

        public void add(Vertex vertex) { // TODO: 留给读者实现...
        }

        // 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
        public void update(Vertex vertex) { // TODO: 留给读者实现..
        }

        public boolean isEmpty() { // TODO: 留给读者实现...
            return false;
        }
    }

    /**
     * @param s   起始顶点
     * @param t   结束顶点
     * @param v   所有节点的长度
     * @param adj 所有节点的出度信息的数组
     */
    public void dijkstra(int s, int t, int v, LinkedList<Edge> adj[]) { // 从顶点s到顶点t的最短路径
        int[] predecessor = new int[v]; // 用来还原最短路径
        Vertex[] vertexes = new Vertex[v];
        for (int i = 0; i < v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue queue = new PriorityQueue(v);// 小顶堆
        boolean[] inqueue = new boolean[v]; // 标记是否进入过队列
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除
            if (minVertex.id == t) break; // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex

                if (minVertex.dist + e.w < nextVertex.dist) { // 更新next的dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;  //--始终保存了最小值,并且更新了到当前顶点的上一个顶点;
                    if (inqueue[nextVertex.id]) {
                        queue.update(nextVertex); // 更新队列中的dist值
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }

            }
        }
        // 输出最短路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }
}
