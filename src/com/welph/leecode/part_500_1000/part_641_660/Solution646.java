package com.welph.leecode.part_500_1000.part_641_660;

import java.util.Arrays;

/**
 * 给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。
 * 找出并返回能够形成的 最长数对链的长度 。
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * <p>
 * 示例 1：
 * 输入：pairs = [[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4] 。
 * 示例 2：
 * 输入：pairs = [[1,2],[7,8],[4,5]]
 * 输出：3
 * 解释：最长的数对链是 [1,2] -> [4,5] -> [7,8] 。
 * <p>
 * 提示：
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 */
public class Solution646 {

    public static void main(String[] args) {
        System.out.println(findLongestChain(new int[][]{
                {1, 2}, {2, 3}, {3, 4}
        }));

        System.out.println(findLongestChain(new int[][]{
                {1, 2}, {7, 8}, {4, 5}
        }));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_281_300.Solution300}
     * {@link com.welph.leecode.part_1_500.part_481_500.Solution491}
     * ----------------
     * 这里不严格按照pair的顺序来, 服了 [任意顺序]
     */
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> {
            int i = o1[0] - o2[0];
            return i == 0 ? (o1[1] - o2[1]) : i;
        });
        //排序
        int n = pairs.length;
        if (n == 0) {
            return 0;
        }
        int len = 1;
        int[] dp = new int[n + 1];
        dp[1] = pairs[0][1];
        for (int i = 1; i < n; i++) {
            if (dp[len] < pairs[i][0]) {
                dp[++len] = pairs[i][1];
            } else {
                //二分法查找目标k
                int k = binarySearch(dp, 1, len, pairs[i][0]);
                if (dp[k + 1] > pairs[i][1]) {
                    dp[k + 1] = pairs[i][1];
                }
            }
        }
        return len;
    }

    private static int binarySearch(int[] dp, int l, int r, int num) {
        int pos = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (dp[mid] < num) {
                pos = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return pos;
    }
}
