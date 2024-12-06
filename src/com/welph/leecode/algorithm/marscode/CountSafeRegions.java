package com.welph.leecode.algorithm.marscode;

/**
 * 小F被神秘力量带入了一个魔幻世界，这里危机四伏。为了在异世界中生存，
 * 小F需要找到安全区。异世界可以被表示为一个大小为 n x m 的二维数组，每个格子的值代表该位置的危险程度。
 *
 * 小F的能力值为 X，当某个格子的危险程度小于等于 X 时，这个格子是安全的。
 * 如果多个安全的格子相邻（上下左右连通），它们可以构成一个安全区。你需要帮助小F计算出一共有多少个安全区。
 */
public class CountSafeRegions {

    //{@link Solution200} 类似这道题的岛屿联通问题了
    public static int solution(int n, int m, int X, int[][] a) {
        //并查集
        int[] parent = new int[n * m];
        int[] rank = new int[parent.length];
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] <= X) {
                    parent[i * m + j] = i * m + j;
                    result++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] <= X) {
                    a[i][j] = X + 1; //标记已经访问过
                    for (int k = 0; k < 4; k++) {
                        if (i - 1 >= 0 && a[i - 1][j] <= X && union(parent, rank, i * m + j, (i - 1) * m + j)) {
                            result--;
                        }
                        if (i + 1 < n && a[i + 1][j] <= X && union(parent, rank, i * m + j, (i + 1) * m + j)) {
                            result--;
                        }
                        if (j - 1 >= 0 && a[i][j - 1] <= X && union(parent, rank, i * m + j, i * m + j - 1)) {
                            result--;
                        }
                        if (j + 1 < m && a[i][j + 1] <= X && union(parent, rank, i * m + j, i * m + j + 1)) {
                            result--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean union(int[] parent, int[] rank, int x, int y) {
        int rootx = find(parent, x);
        int rooty = find(parent, y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx] += 1;
            }
            return true;
        }
        return false;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    public static void main(String[] args) {
        int[][] a1 = {{2, 3, 3}, {3, 3, 3}, {3, 3, 3}};
        int[][] a2 = {{6, 6}, {6, 4}};
        int[][] a3 = {{1, 2, 2}, {2, 3, 3}, {3, 4, 5}};

        System.out.println(solution(3, 3, 4, a1) == 1);
        System.out.println(solution(2, 2, 5, a2) == 1);
        System.out.println(solution(3, 3, 3, a3) == 1);
        System.out.println(solution(12, 2, 5,
                new int[][]{{6, 2}, {9, 3}, {13, 8}, {8, 8}, {11, 4}, {15, 8},
                        {12, 7}, {9, 8}, {1, 5}, {14, 11}, {9, 9}, {2, 5}}));
    }
}
