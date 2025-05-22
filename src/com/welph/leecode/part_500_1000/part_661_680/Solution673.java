package com.welph.leecode.part_500_1000.part_661_680;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 *
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 提示:
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 */
public class Solution673 {

    /**
     * {@link com.welph.leecode.part_1_500.part_281_300.Solution300}
     */
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }

    //数组是未排序的
    public static int findNumberOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][2]; //记录每个点截至的最长递增子序列的长度和个数
        int max = 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1; //初始化长度为1
            dp[i][1] = 1; //初始化个数为1
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j][0] + 1 > dp[i][0]) { //如果比当前的长度大,则更新长度和个数
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] + 1 == dp[i][0]) { //如果相等,则更新个数
                        dp[i][1] += dp[j][1];
                    }
                }
            }
            if (dp[i][0] > max) {
                max = dp[i][0];
                count = dp[i][1];
            } else if (dp[i][0] == max) {
                count += dp[i][1];
            }
        }
        return count;
    }

    /**
     * 官方题解: 针对上面的优化, 具体类似{@link com.welph.leecode.part_1_500.part_281_300.Solution300}
     *
     * 贪心+前缀和+二分查找
     */

    public static int findNumberOfLIS1(int[] nums) {
        List<List<Integer>> d = new ArrayList<>(); //长度
        List<List<Integer>> cnt = new ArrayList<>(); //该长度的个数
        for (int v : nums) {
            int i = binarySearch1(d.size(), d, v);
            int c = 1;
            if (i > 0) {
                int k = binarySearch2(d.get(i - 1).size(), d.get(i - 1), v);
                c = cnt.get(i - 1).get(cnt.get(i - 1).size() - 1) - cnt.get(i - 1).get(k);
            }
            if (i == d.size()) {
                List<Integer> dList = new ArrayList<>();
                dList.add(v);
                d.add(dList);
                List<Integer> cntList = new ArrayList<>();
                cntList.add(0);
                cntList.add(c);
                cnt.add(cntList);
            } else {
                d.get(i).add(v);
                int cntSize = cnt.get(i).size();
                cnt.get(i).add(cnt.get(i).get(cntSize - 1) + c);
            }
        }

        int size1 = cnt.size(), size2 = cnt.get(size1 - 1).size();
        return cnt.get(size1 - 1).get(size2 - 1);
    }

    public static int binarySearch1(int n, List<List<Integer>> d, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            List<Integer> list = d.get(mid);
            if (list.get(list.size() - 1) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static int binarySearch2(int n, List<Integer> list, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
