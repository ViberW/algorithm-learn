package com.welph.leecode.algorithm.thinking;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [网络流] 有向图,基于增广路(从源点到汇点的路径，其上所有边的残余容量均大于0)
 * 同时基于(反向边--撤销操作)
 * -- 找到从源点到汇点的最大流(Ford-Fulkerson算法)
 * @since 2021/8/3 10:39
 */
public class NetWorkFlow_28 {

    public static void main(String[] args) {
//        flow_dfs();
//        flow_bfs();
        flow_dinic();
    }

    /**
     * Dinic算法:  DFS/BFS算法的优化; 先使用BFS分层,再使用DFS寻找
     * <p>
     * 分层: 就是预处理出源点到每个点的距离（注意每次循环都要预处理一次，因为有些边可能容量变为0不能再走）
     * ,只往层数高的方向增广，可以保证不走回头路也不绕圈子
     */
    public static void flow_dinic() {
        int[] head = new int[12]; //index为顶点的第一条边 (长度: 2*边个数+2)
        Edge[] edges = new Edge[12]; //第index条边的信息

        //考虑到反向边, 则建立图时要从1开始,  偶数为反向边 奇数为原本边
        int cnt = 1; // 2-反向 3-原本边
        cnt = add(1, 2, 3, head, edges, cnt);
        cnt = add(1, 3, 2, head, edges, cnt);
        cnt = add(2, 4, 3, head, edges, cnt);
        cnt = add(2, 3, 2, head, edges, cnt);
        add(4, 3, 2, head, edges, cnt);

        int s = 1;
        int t = 3;
        int ans = 0;
        int[] lv = new int[5];// lv是每个点的层数
        int[] cur = new int[head.length];//cur用于当前弧优化标记增广起点
        while (dinicBfs(s, lv, cur, head, edges, t)) {
            ans += dinicDfs(s, 6, lv, cur, edges, t);
        }
        System.out.println("FF: " + ans);
    }

    private static boolean dinicBfs(int s, int[] lv, int[] cur,
                                    int[] head, Edge[] edges, int t) {
        Arrays.fill(lv, -1);
        lv[s] = 0; //保证到最后了.
        System.arraycopy(head, 0, cur, 0, head.length);
        Deque<Integer> q = new LinkedList<>();
        q.push(s);
        while (!q.isEmpty()) {
            int p = q.pop();
            for (int eg = head[p]; eg > 0; eg = edges[eg].next) {
                int to = edges[eg].to, vol = edges[eg].w;
                if (vol > 0 && lv[to] == -1) {
                    lv[to] = lv[p] + 1;
                    q.push(to);
                }
            }
        }
        return lv[t] != -1;
    }

    private static int dinicDfs(int p, int flow, int[] lv, int[] cur,
                                Edge[] edges, int t) {
        if (p == t)
            return flow;
        int rmn = flow; // 剩余的流量
        for (int eg = cur[p]; eg > 0 && rmn > 0; eg = edges[eg].next) // 如果已经没有剩余流量则退出
        {
            cur[p] = eg; // 当前弧优化，更新当前弧
            int to = edges[eg].to, vol = edges[eg].w;
            if (vol > 0 && lv[to] == lv[p] + 1) // 往层数高的方向增广
            {
                int c = dinicDfs(to, Math.min(vol, rmn), lv, cur, edges, t); // 尽可能多地传递流量
                rmn -= c; // 剩余流量减少
                edges[eg].w -= c; // 更新残余容量
                edges[eg ^ 1].w += c; // 再次提醒，链式前向星的cnt需要初始化为1（或-1）才能这样求反向边
            }
        }
        return flow - rmn; // 返回传递出去的流量的大小
    }

    //////////////////////////////////////////////////

    /**
     * 基于DFS的FF算法
     */
    public static void flow_dfs() {
        //todo 这两个是使用了 [链式向前星] 存图;
        int size = 5; //边的个数
        int[] head = new int[size * 2 + 2]; //index为顶点的第一条边
        Edge[] edges = new Edge[12]; //第index条边的信息

        //考虑到反向边, 则建立图时要从1开始,  偶数为反向边 奇数为原本边
        int cnt = 1; // 2-反向 3-原本边
        cnt = add(1, 2, 3, head, edges, cnt);
        cnt = add(1, 3, 2, head, edges, cnt);
        cnt = add(2, 4, 3, head, edges, cnt);
        cnt = add(2, 3, 2, head, edges, cnt);
        add(4, 3, 2, head, edges, cnt);

        int ans = 0; //从源到汇的最大流
        int c; //当前次网络流的大小
        boolean[] visit = new boolean[edges.length];
        while ((c = dfs(1, 10, visit, head, edges, 3)) != -1) {
            Arrays.fill(visit, false);
            ans += c;
        }
        System.out.println("FF: " + ans);
    }

    private static int add(int from, int to, int w, int[] head, Edge[] edges, int cnt) {
        //反向边
        edges[++cnt] = new Edge();
        edges[cnt].w = 0;
        edges[cnt].to = from;
        edges[cnt].next = head[to];
        head[to] = cnt;
        //原本的边
        edges[++cnt] = new Edge();
        edges[cnt].w = w;    //新增一条编号为cnt+1的边，边权为w
        edges[cnt].to = to;    //该边的终点为to
        edges[cnt].next = head[from];  //把下一条边，设置为当前起点的第一条边
        head[from] = cnt;  //该边成为当前起点新的第一条边
        return cnt;
    }

    private static int dfs(int p, int flow, boolean[] visit,
                           int[] head, Edge[] edges, int t) {
        if (p == t)
            return flow; // 到达终点，返回这条增广路的流量
        visit[p] = true;
        for (int eg = head[p]; eg > 0; eg = edges[eg].next) {
            int to = edges[eg].to, vol = edges[eg].w, c;
            // 返回的条件是残余容量大于0、未访问过该点且接下来可以达到终点（递归地实现）
            // 传递下去的流量是边的容量与当前流量中的较小值
            if (vol > 0 && !visit[to] && (c = dfs(to, Math.min(vol, flow), visit, head, edges, t)) != -1) {
                edges[eg].w -= c;
                edges[eg ^ 1].w += c; //反向边
                // 链式前向星取反向边的一种简易方法: 建图时要把cnt置为1，且要保证反向边紧接着正向边建立
                return c;
            }
        }
        return -1; // 无法到达终点
    }

    static class Edge {
        int next; //下一个边
        int to; //目标点
        int w; //边权重
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * 基于BFS的FF算法,又名EK算法
     */
    public static void flow_bfs() {
        //todo 这两个是使用了 [链式向前星] 存图;
        int[] head = new int[12]; //index为顶点的第一条边
        Edge[] edges = new Edge[12]; //第index条边的信息

        //考虑到反向边, 则建立图时要从1开始,  偶数为反向边 奇数为原本边
        int cnt = 1; // 2-反向 3-原本边
        cnt = add(1, 2, 3, head, edges, cnt);
        cnt = add(1, 3, 2, head, edges, cnt);
        cnt = add(2, 4, 3, head, edges, cnt);
        cnt = add(2, 3, 2, head, edges, cnt);
        add(4, 3, 2, head, edges, cnt);

        int[] flows = new int[10];
        int s = 1;
        int t = 3;
        int ans = 0; //从源到汇的最大流
        int[] last = new int[5];
        while (bfs(s, flows, head, edges, t, last)) {
            ans += flows[t];
            // 从汇点原路返回更新残余容量
            for (int i = t; i != s; i = edges[last[i] ^ 1].to) {
                edges[last[i]].w -= flows[t];
                edges[last[i] ^ 1].w += flows[t];
            }
        }
        System.out.println("FF: " + ans);
    }

    private static boolean bfs(int s, int[] flows,
                               int[] head, Edge[] edges, int t, int[] last) {
        Arrays.fill(last, -1);

        Deque<Integer> q = new LinkedList<>();
        q.push(s);
        flows[s] = 0;
        while (!q.isEmpty()) {
            int p = q.pop();
            if (p == t) // 到达汇点，结束搜索
                break;
            for (int eg = head[p]; eg > 0; eg = edges[eg].next) {
                int to = edges[eg].to, vol = edges[eg].w;
                if (vol > 0 && last[to] == -1) // 如果残余容量大于0且未访问过（所以last保持在-1）
                {
                    last[to] = eg;
                    flows[to] = flows[p] == 0 ? vol : Math.min(flows[p], vol);
                    q.push(to);
                }
            }
        }
        return last[t] != -1;
    }

}
