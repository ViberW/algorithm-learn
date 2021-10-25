package com.welph.leecode.part_1_500.part_321_340;

import java.util.*;

/**
 * 给定一个m x n 整数矩阵matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * <p>
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为[1, 2, 6, 9]。
 * <p>
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是[3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 * <p>
 * 提示：
 * m == matrix.lengthz
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 */
public class Solution329 {

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath1(matrix));
        int[][] matrix1 = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(longestIncreasingPath1(matrix1));
        int[][] matrix2 = {{1}};
        System.out.println(longestIncreasingPath1(matrix2));
    }

    //dp[m][n] 代表到{m,n}的递增的位置;
    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        //按照值排序, 记录v,m,n
        int[][] list = new int[m * n][3];
        int size = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list[size++] = new int[]{matrix[i][j], i, j};
            }
        }

        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int[][] dp = new int[m][n];
        //dp[m][n] = max(dp[m][n], dp[r][c] + 1)
        int[] ints;
        int i;
        int j;
        int r;
        int c;
        int v;
        int[][] range = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int k = 0; k < list.length; k++) {
            ints = list[k];
            v = ints[0];
            i = ints[1];
            j = ints[2];
            dp[i][j] = 1;
            for (int[] ran : range) {
                r = i + ran[0];
                c = j + ran[1];
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    if (v > matrix[r][c]) {
                        dp[i][j] = Math.max(dp[i][j], dp[r][c] + 1);
                    }
                }
            }
            ans = Math.max(ans, dp[i][j]);
        }
        return ans;
    }

    /**
     * 拓扑排序的话, 在遍历每个点 上下左右大于当前点, 则标记当前点i,j的出度+1
     * //使用queue, 不断处理, 知道出度为0  ,此处类似BFS处理方式了.
     */
    public static int longestIncreasingPath1(int[][] matrix) {
        int[][] range = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length;
        int n = matrix[0].length;
        int r;
        int c;
        int[][] outDegree = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] ints : range) {
                    r = i + ints[0];
                    c = j + ints[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && matrix[r][c] > matrix[i][j]) {
                        ++outDegree[i][j];
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        //找出所有出度为0的点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegree[i][j] == 0) {
                    queue.offer(new int[]{i, j}); //说明周围没有一个比i,j大的点
                }
            }
        }

        int ans = 0;
        int[] poll;
        int i;
        int j;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size(); //类似BFS的处理 --每一次的循环,代表有X条相同长度的不同路径,知道找到最长的
            for (int k = 0; k < size; k++) {
                poll = queue.poll();
                i = poll[0];
                j = poll[1];
                for (int[] ints : range) {
                    r = i + ints[0];
                    c = j + ints[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && matrix[i][j] > matrix[r][c]) {
                        --outDegree[r][c];
                        if (outDegree[r][c] == 0) {
                            queue.offer(new int[]{r, c});
                        }
                    }
                }
            }
        }
        return ans;
    }
}
