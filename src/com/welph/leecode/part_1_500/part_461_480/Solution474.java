package com.welph.leecode.part_1_500.part_461_480;

import com.welph.leecode.algorithm.packet.Packet2D;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于
 * n 的值 3 。
 * <p>
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * <p>
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i]仅由'0' 和'1' 组成
 * 1 <= m, n <= 100
 */
public class Solution474 {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[] { "10", "0001", "111001", "1", "0" }, 5, 3));
    }

    /**
     * 动态规划? 如果包含这个, 则需要其他的仅仅包含少于m-x n-y的组项
     * [0,i-1] 当前为 i 的节点
     * dp[m][n] = dp[m-x][n-y]+1
     * {@link Packet2D} 本质上就是个二维的背包问题
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int mi = 0, ni = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    mi++;
                } else {
                    ni++;
                }
            }
            for (int i = m; i >= mi; i--) {
                for (int j = n; j >= ni; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - mi][j - ni]);
                }
            }
        }
        return dp[m][n];
    }
}
