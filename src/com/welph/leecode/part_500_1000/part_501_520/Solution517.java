package com.welph.leecode.part_500_1000.part_501_520;

import java.util.Arrays;

/**
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * <p>
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，
 * 与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * <p>
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，
 * 请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。
 * 如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：machines = [1,0,5]
 * 输出：3
 * 解释：
 * 第一步: 1 0 <-- 5 => 1 1 4
 * 第二步: 1 <-- 1 <-- 4 => 2 1
 * 第三步: 2 1 <-- 3 => 2 2 2
 * <p>
 * 示例 2：
 * 输入：machines = [0,3,0]
 * 输出：2
 * 解释：
 * 第一步: 0 <-- 3 0 => 1 2 0
 * 第二步: 1 2 --> 0 => 1 1 1
 * <p>
 * 示例 3：
 * 输入：machines = [0,2,0]
 * 输出：-1
 * 解释：
 * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
 * <p>
 * 提示：
 * n == machines.length
 * 1 <= n <= 104
 * 0 <= machines[i] <= 105
 */
public class Solution517 {

    public static void main(String[] args) {
        // System.out.println(findMinMoves(new int[]{1, 0, 5}));
        // System.out.println(findMinMoves(new int[]{0, 3, 0}));
        // System.out.println(findMinMoves(new int[]{4, 0, 0, 4}));
        // System.out.println(findMinMoves(new int[]{0, 0, 14, 0, 10, 0, 0, 0}));
        System.out.println(findMinMoves(new int[] { 0, 0, 11, 5 }));
    }

    /**
     * {@link com.welph.leecode.algorithm.z7z8.DivideCards}
     * --- 注意 这里仅仅只能移动一件
     */
    public static int findMinMoves(int[] machines) {
        int total = 0;
        for (int machine : machines) {
            total += machine;
        }
        if (total % machines.length != 0) {
            return -1;
        }
        int avg = total / machines.length;
        int ret = Integer.MIN_VALUE;
        int last = machines[0] - avg;
        for (int i = 0; i < machines.length - 1; i++) {
            // 要么ret最大值, 要么是从0到当前作为一个整体的迁移量最大值, 要么是单独的节点的差值的最大值
            ret = Math.max(Math.max(ret, Math.abs(last)), machines[i + 1] - avg);
            // 这里需要把控好,能够迁移的最大值
            last = machines[i + 1] - avg + last;
        }
        return Math.max(ret, machines[machines.length - 1] - avg);
        // 只能相邻移动 这方式不行,
        /*
         * int last = -1;
         * int sum = 0;
         * int ret = 0;
         * int over = 0;
         * for (int i = 0; i < machines.length; i++) {
         * if (machines[i] == avg) { //这个没问题
         * last++;
         * continue;
         * }
         * sum += machines[i];
         * if (machines[i] > avg) {
         * over = Math.max(machines[i] - avg, over);
         * }
         * if (sum == (i - last) * avg) {
         * ret = Math.max(ret, over);
         * last = i;
         * sum = 0;
         * over = 0;
         * }
         * }
         * return ret;
         */

    }

    /* 官方题解 */

    // 就是贪心
    public int findMinMoves2(int[] machines) {
        int tot = Arrays.stream(machines).sum();
        int n = machines.length;
        if (tot % n != 0) {
            return -1;
        }
        int avg = tot / n;
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;
            sum += num; // 遍历过后, 作为一个整体
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }

}
