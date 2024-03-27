package com.welph.leecode.part_500_1000.part_501_520;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
 * solution.flip(); // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 * solution.flip(); // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
 * solution.flip(); // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
 * solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
 * solution.flip(); // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 * <p>
 * <p>
 * 提示：
 * 1 <= m, n <= 10^4
 * 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
 * 最多调用 1000 次 flip 和 reset 方法。
 */
public class Solution519 {

    public static void main(String[] args) {
        Solution2 obj = new Solution2(3, 1);
        System.out.println(Arrays.toString(obj.flip()));
        System.out.println(Arrays.toString(obj.flip()));
        System.out.println(Arrays.toString(obj.flip()));
        obj.reset();
    }

    static class Solution2 {
        static Random random = new Random();
        int len;
        int m;
        int n;
        Map<Integer, Integer> map = new HashMap<>();

        public Solution2(int m, int n) {
            this.m = m;
            this.n = n;
            this.len = m * n;
        }

        // 仅仅存储len-1和i两个即可
        public int[] flip() {
            int i = random.nextInt(len);
            Integer actual = map.getOrDefault(i, i);
            map.put(i, map.getOrDefault(len - 1, len - 1)); // 相当于是让当前被选中的i, 用最后一位去替代.
            len--;
            return new int[] { actual / n, actual % n };
        }

        public void reset() {
            this.len = m * n;
            map.clear();
        }
    }

    /**
     * 这里试图将boolean 转化为int 存储的是下一个节点的位置?
     * //超出内存.... 因为调用不到那么多的索引
     */
    static class Solution1 {
        static Random random = new Random();
        int[] dp;
        int len;
        int m;
        int n;

        public Solution1(int m, int n) {
            this.m = m;
            this.n = n;
            this.len = m * n;
            dp = new int[len];
            reset();
            // 那么 i/m = i i%n = j
        }

        public int[] flip() {
            // 每次获取就移动
            int i = random.nextInt(len);
            int actual = dp[i];
            // 交换最后一个和当前index位置
            dp[i] = dp[len - 1];
            dp[len - 1] = actual;
            len--;
            return new int[] { actual / n, actual % n };
        }

        public void reset() {
            this.len = m * n;
            for (int i = 0; i < len; i++) {
                dp[i] = i;
            }
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
            // 水塘抽样
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
            return new int[] { mi, ni };
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
