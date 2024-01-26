package com.welph.leecode.part_1_500.part_341_360;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * <p>
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * 示例 2：
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= envelopes.length <= 5000
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 104
 */
public class Solution354 {

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(maxEnvelopes(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }

    /**
     * 最长单调递增序列?
     * {@link com.welph.leecode.part_1_500.part_281_300.Solution300}
     * {@link com.welph.leecode.part_1_500.part_321_340.Solution334}  --todo 下面的二分法说明
     */
    public static int maxEnvelopes(int[][] envelopes) {
        int[] dp = new int[envelopes.length];
        //对数组进行排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o1[0], o2[0]);
                if (compare == 0) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return compare;
            }
        });

        int ans = dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {  //todo 这块应该是有机会通过[二分法查找]的. ?? 最长的递增子序列成为一个list
                if (compare(envelopes[j], envelopes[i])) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    private static boolean compare(int[] j, int[] i) {
        return i[0] > j[0] && i[1] > j[1];
    }

    /* 官方题解(第一种和上面的动态规划一样) */

    //基于二分法查找动态规划
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]); 
        //这种方式很像 {@link com.welph.leecode.part_1_500.part_281_300.Solution300.lengthOfLIS2()}
        for (int i = 1; i < n; ++i) { 
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
