package com.welph.leecode.part_500_1000.part_501_520;

import java.util.Arrays;
import java.util.Random;

/**
 * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，
 * 随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。
 * 所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
 * <p>
 * 尽量最少调用内置的随机函数，并且优化时间和空间复杂度。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
 * int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
 * void reset() 将矩阵中所有的值重置为 0
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Solution", "flip", "flip", "flip", "reset", "flip"]
 * [[3, 1], [], [], [], [], []]
 * 输出
 * [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
 * <p>
 * 解释
 * Solution solution = new Solution(3, 1);
 * solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 * solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
 * solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
 * solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
 * solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 * <p>
 * <p>
 * 提示：
 * 1 <= m, n <= 10^4
 * 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
 * 最多调用 1000 次 flip 和 reset 方法。
 */
public class Solution519 {

    public static void main(String[] args) {
        Solution1 obj = new Solution1(3, 1);
        System.out.println(Arrays.toString(obj.flip()));
        System.out.println(Arrays.toString(obj.flip()));
        System.out.println(Arrays.toString(obj.flip()));
        obj.reset();
    }

    /**
     * 这里试图将boolean 转化为int 存储的是下一个节点的位置?
     */
    static class Solution1 {
        static Random random = new Random();
        int[][] dp;
        int m;
        int n;
        int len;

        public Solution1(int m, int n) {
            this.m = m;
            this.n = n;
            len = m * n + 1;
            dp = new int[len + 1][4];
            reset();
        }

        public int[] flip() {
            //水塘抽样
            int ret = 1;
            int k;
            int t = 0;
            for (int i = dp[0][1]; i < len; i = dp[i][1]) {
                k = random.nextInt(ret);
                if (k == 0) {
                    t = i;
                }
                ret++;
            }
            dp[dp[t][0]][1] = dp[t][1];
            dp[dp[t][1]][0] = dp[t][0];
            return new int[]{dp[t][2], dp[t][3]};
        }

        public void reset() {
            dp[0][1] = 1;
            int k = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[k][0] = k - 1;
                    dp[k][1] = k + 1;
                    dp[k][2] = i;
                    dp[k][3] = j;
                    k++;
                }
            }
            dp[dp.length - 1][0] = k - 1;
        }
    }

    static class Solution {
        static Random random = new Random();
        boolean[][] dp;
        int m;
        int n;

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            dp = new boolean[m][n];
        }

        /**
         * 这里会超时
         */
        public int[] flip() {
            //水塘抽样
            int ret = 1;
            int k;
            int mi = -1;
            int ni = -1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!dp[i][j]) {
                        k = random.nextInt(ret);
                        if (k == 0) {
                            mi = i;
                            ni = j;
                        }
                        ret++;
                    }
                }
            }
            dp[mi][ni] = true;
            return new int[]{mi, ni};
        }

        public void reset() {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = false;
                }
            }
        }
    }
}
