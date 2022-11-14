package com.welph.leecode.part_500_1000.part_521_540;

import java.util.Random;

/**
 * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。
 * 选取下标 i 的 概率 为 w[i] / sum(w) 。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * <p>
 * 示例 1：
 * 输入：
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * <p>
 * 示例 2：
 * 输入：
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 * <p>
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 * <p>
 * 提示：
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex 将被调用不超过 104 次
 */
public class Solution528 {

    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{1, 3});
        System.out.println(obj.pickIndex());
        System.out.println(obj.pickIndex());
        System.out.println(obj.pickIndex());
    }

    /**
     * 前缀和
     */
    static class Solution {
        static Random random = new Random();
        int[] preSum;
        int total;

        public Solution(int[] w) {
            preSum = new int[w.length + 1];
            for (int i = 0; i < w.length; i++) {
                preSum[i + 1] = preSum[i] + w[i];
            }
            total = preSum[w.length];
        }

        public int pickIndex() {
            int i = random.nextInt(total);
            //找到对应的节点信息. 二分查对应的位置
            int l = 1;
            int r = preSum.length - 1;
            int mid;
            while (l <= r) {
                mid = (l + r) / 2;
                if (preSum[mid] < i) {
                    l = mid + 1;
                } else if (preSum[mid] > i) {
                    r = mid - 1;
                } else {
                    return mid;
                }
            }
            return l - 1;
        }
    }
}
