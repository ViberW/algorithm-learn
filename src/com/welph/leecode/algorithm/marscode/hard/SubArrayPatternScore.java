package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小T 正在进行一项数据匹配实验，他有两个不同的整数列表 nums1 和 nums2，
 * 他需要从这两个列表中分别选取一些元素组成非空的片段，并且这两个片段的长度必须相同。
 *
 * 小T 想知道，在所有可能的组合中，能够实现的最大匹配得分是多少。
 *
 * 匹配得分的计算方式是，将两个片段中的对应位置的元素相乘后再相加，最终得到一个总分。
 * 你的任务是帮助小T 找到这两个列表中非空片段组合的最大匹配得分，并返回结果。
 */
public class SubArrayPatternScore {

    public static int solution(int[] nums1, int[] nums2) {
        //dp[i][j] 代表nums1到了i 以及nums2到了j 最大分数
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; //有选择, 没选择
        int lowMulti = Integer.MIN_VALUE;
        boolean positive = false;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                int multi = nums1[i - 1] * nums2[j - 1];
                dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i - 1][j]),
                        dp[i - 1][j - 1] + multi);
                if (multi >= 0) {
                    positive = true;
                } else {
                    lowMulti = Math.max(multi, lowMulti);
                }
            }
        }
        return positive ? dp[nums1.length][nums2.length] : lowMulti;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{2, -1, 3, 4}, new int[]{3, -2, 1}) == 13);
//        System.out.println(solution(new int[]{1, -3}, new int[]{-2, 4, 6}) == 6);
//        System.out.println(solution(new int[]{1, 2, -1}, new int[]{2, -3, 3}) == 8);
//        System.out.println(solution(new int[]{-5, 2, -3}, new int[]{4, -2, 1}) == 14);
        System.out.println(solution(new int[]{-1, -2}, new int[]{3, 4}) == -3);
    }
}
