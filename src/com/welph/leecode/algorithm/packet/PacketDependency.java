package com.welph.leecode.algorithm.packet;

import java.util.Arrays;

/**
 * ----------------------------------------------有依赖的背包
 * 
 * 有一个容量有限的背包，容量为w，以及m个待选择的物品
 * 但i物品可能依赖于i1物品的存在才能放入
 * 
 * 有依赖的背包在于------[i和i1不同物品之间的放入背包有依赖关系]-----
 */
public class PacketDependency {
    public static void main(String[] args) {
        // 这里的依赖关系可以类似于树结构来处理
        /*
         * . ........1
         * . ...2.........3
         * . .5...4.....6
         * --------------------
         * 说明: 4号物品的选择依赖2, 而2依赖1, 所以一旦选择4, 则1和2一定也要选择
         * 所以想选择4,则合适的路径树
         * 类似{@link Solution310}
         */
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        int[] d = { -1, 0, 0, 1, 1, 2 };// -1代表根节点
        System.out.println(maximumValue(w, v, d, 5));
    }

    /*
     * 这里的依赖关系先考虑[单依赖]
     * 状态转移方程: f[u][j] = max(f[u][j], f[u][j-k] + f[son][k])
     * ------ f[物品i的值i][背包剩余容量]
     */
    public static int maximumValue(int[] w, int[] v, int[] d, int n) {
        int m = w.length;
        int root = 0;
        int[] edge = new int[m + 1];
        int[] nextedge = new int[m + 1];// 填充-1
        int[] head = new int[m + 1]; // 填充-1
        Arrays.fill(nextedge, -1);
        Arrays.fill(head, -1);
        int idx = 0;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            if (d[i] == -1) {
                root = i;
            } else {
                add(d[i], i, idx, edge, nextedge, head);
                idx++;
            }
        }
        // 注意 这里是只有一个root节点, 否则就是遍历root节点
        // 注意 这里没有一个子节点有多个父节点
        dfs(n, w, v, f, root, edge, nextedge, head);
        return 0;
    }

    /*
     * 从root节点往下寻找
     */
    private static void dfs(int n, int[] w, int[] v, int[][] f,
            int root, int[] edge, int[] nextedge, int[] head) {
        // 以root为根节点,则root必选,一次重量大于w[root]初始化为v[root]
        for (int i = w[root]; i <= n; i++) {
            f[root][i] = v[root];
        }
        for (int i = head[root]; i != -1; i = nextedge[i]) {
            // 子节点
            int s = edge[i];
            // 相当于选择了s的情况下 进行dfs 对子节点是否选择逻辑
            dfs(n, w, v, f, s, edge, nextedge, head);
            for (int j = n; j >= w[root]; j--) {
                for (int k = 0; k <= j - w[root]; k++) {
                    if (f[root][j] < f[root][j - k] + f[s][k]) {// 选取最大值
                        f[root][j] = f[root][j - k] + f[s][k];
                    }
                }
            }
        }
    }

    /**
     * 
     * @param parent   父节点
     * @param cur      当前节点
     * @param edge     边列表,指向的是son子节点
     * @param nextEdge 当前边的后续边Id
     * @param head     父节点最新的边id
     * 
     *                 有点以链式向前星的方式构建树信息
     */
    private static void add(int parent, int cur, int idx,
            int[] edge, int[] nextedge, int[] head) {
        edge[idx] = cur; // 边执行的son节点
        nextedge[idx] = head[parent];// 记录上一条指向parent的边
        head[parent] = idx; // 更新最新的指向parent的边
    }
}
