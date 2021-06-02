package com.welph.leecode.algorithm.z7z8;

/**
 * -----------------匈牙利算法-------------
 * 用于求二分图的最大匹配数和最小点覆盖数
 */
public class HungarySolution {

    public static void main(String[] args) {
        int m = 4, n = 4;//M, N分别表示左、右侧集合的元素数量
        boolean[][] mn = new boolean[m + 1][n + 1];
        mn[1][2] = true;
        mn[1][4] = true;
        mn[2][2] = true;
        mn[3][1] = true;
        mn[3][3] = true;
        mn[4][4] = true;
        System.out.println(hungarian(mn, m, n));
    }

    /**
     * 增广路
     * 一条交错路，且该交错路的起点和终点都为匹配M的非饱和点。
     * ----匹配M中 匹配M边集的点为饱和点, 否则为非饱和点
     * 1)路径的边数为奇数，第一条边和最后一条边都不属于M
     * 2)将路径中的边的匹配方式取反操作，会得到更大的匹配M'，匹配数加1
     * 3)M为图G的最大匹配等价于不存在M的增广路
     * -------------------------
     * 匈牙利算法核心思想：
     * 1) 初始匹配M为空
     * 2) 找出一条增广路径p，取反操作得到更大的匹配M'代替M
     * 3) 重复步骤2)，直到找不出增广路为止
     */
    public static int hungarian(boolean[][] mn, int m, int n) {
        int ans = 0;
        int[] y = new int[n + 1]; //记录当前右侧元素所对应的左侧元素
        boolean[] visit = new boolean[n + 1]; //记录右侧元素是否已被访问过
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; j++) {
                visit[j] = false;
            }
            if (hungary(mn, visit, y, i)) {
                ans++;
            }
        }
        return ans;
    }

    public static boolean hungary(boolean[][] mn, boolean[] visit, int[] y, int u) {
        for (int i = 1; i < y.length; ++i) {
            if (!visit[i] && mn[u][i]) { //说明允许 u和i匹配
                visit[i] = true;
                if (y[i] == 0 || hungary(mn, visit, y, y[i])) { // --类似回溯. 重新尝试分配看看.
                    //要么没光顾过, 要么之前i选择的另外个u找到合适的了
                    y[i] = u;
                    return true;
                }
            }
        }
        return false;
    }

}
