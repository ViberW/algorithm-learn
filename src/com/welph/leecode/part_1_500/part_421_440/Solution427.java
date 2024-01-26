package com.welph.leecode.part_1_500.part_421_440;

/**
 * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
 * 你需要返回能表示矩阵的 四叉树 的根结点。
 * <p>
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 */
public class Solution427 {

    public static void main(String[] args) {
        Node node = construct(
                new int[][] { { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 },
                        { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 } });
        System.out.println(node);
    }

    /**
     * 就单纯的中间划分, 分治就可以了
     */
    public static Node construct(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return constructNode(grid, 0, m, 0, n);
    }

    static Node constructNode(int[][] grid, int lx, int rx, int ly, int ry) {
        Node root = new Node();
        if (rx - lx == 1) {
            root.isLeaf = true;
            root.val = grid[lx][ly] == 1;
        } else {
            int mx = (rx + lx) / 2;
            int my = (ry + ly) / 2;
            Node topLeft = constructNode(grid, lx, mx, ly, my);
            Node topRight = constructNode(grid, lx, mx, my, ry);
            Node bottomLeft = constructNode(grid, mx, rx, ly, my);
            Node bottomRight = constructNode(grid, mx, rx, my, ry);
            root.isLeaf = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                    && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val;
            if (root.isLeaf) {
                root.val = topLeft.val;
            } else {
                root.val = true;
                root.topLeft = topLeft;
                root.topRight = topRight;
                root.bottomLeft = bottomLeft;
                root.bottomRight = bottomRight;
            }
        }
        return root;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    /* 官方题解 (上面的方法时间和空间已经是100%) */

    // 递归 时间复杂度较高
    public Node construct2(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    public Node dfs(int[][] grid, int r0, int c0, int r1, int c1) {
        boolean same = true;
        for (int i = r0; i < r1; ++i) {
            for (int j = c0; j < c1; ++j) {
                if (grid[i][j] != grid[r0][c0]) { // 比较每个值是否都一样?
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }

        if (same) {
            return new Node(grid[r0][c0] == 1, true);
        }
        // 拆分递归, 时间复杂度在上面的n平方的比较
        Node ret = new Node(
                true,
                false,
                dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1));
        return ret;
    }

    // 递归+二维前缀和
    public Node construct3(int[][] grid) {
        int n = grid.length;
        // 从(0->i, 0->j)的二维前缀和
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs(grid, pre, 0, 0, n, n);
    }

    public Node dfs(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        // 若前缀和等于0或面积,则代表叶子节点
        if (total == 0) {
            return new Node(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new Node(true, true);
        }

        Node ret = new Node(
                true,
                false,
                dfs(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1));
        return ret;
    }

    public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
    }

}
