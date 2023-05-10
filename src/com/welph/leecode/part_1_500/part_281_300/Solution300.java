package com.welph.leecode.part_1_500.part_281_300;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * 进阶：
 * 你可以设计时间复杂度为 O(n^2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到O(n log(n)) 吗?
 */
public class Solution300 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS2(nums));
    }

    /**
     * 获取到其中的子序列, 满足留空的情况下,保证数据的递增性
     * 动态规划 dp[i]
     * dp[i-1] 中最长递增子序列的最大值m
     * //若m<num[i] 则说明dp[i] = dp[i-1]+1
     * //若m>=num[i] 则说明dp[i] = dp[i-1]
     * 但此时需要记录是否在dp[i-1]中有一个次小的递增子序列且满足最大值小于num[i]
     * 若存在,则需要将m = num[i]
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        int max = dp[0] = 1;  //表示以num[i]作为最长上升子序列的结尾的长度
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 官方: 贪心+二分法
     * 维护数组dp[i] 代表长度为i的最长递增子序列的[末尾数值]
     * > 可以看到 dp[i]是关于i的递增的
     * > 反证法: 如果dp[j]>=dp[i] 且 j<i, 那可以考虑从dp[i]的子序列中截取i-j个元素,从而的到dp[j]'
     * >        那么dp[j]' 一定小于 dp[j], 产生了矛盾, 所以dp[i]单调增的
     * -----------
     * 遍历数组, 更新dp[] 和len 值
     * 如果: nums[i]> dp[len]  => len = len+1
     * 否则: dp[1...len] 中找到满足 dp[j-1]<nums[i]<dp[j], 由于匹配则长度+1=i. dp[j] = nums[i]
     * ---------
     * 整理流程:
     * 1. 初始长度len=1, 遍历mums数组
     * 2. 如果 num[i] > dp[len] 则dp[len+1] =num[i], len= len+1
     * 3. 否则 从dp中二分法查找, 找到第一个比nums[i]小的数dp[k] ,并更新dp[k+1] = nums[i]
     */
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int len = 1;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (dp[len] < nums[i]) {
                dp[++len] = nums[i];
            } else {
                //二分法查找目标k
                int k = binarySearch(dp, 1, len, nums[i]);
                dp[k + 1] = nums[i];
            }
        }
        return len;
    }

    private static int binarySearch(int[] dp, int l, int r, int num) {
        int mid;
        while (l < r) {
            mid = (l + r + 1) >> 1;
            if (dp[mid] >= num) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return dp[l] >= num ? -1 : l;
       /* int pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (dp[mid] < num) {
                pos = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return pos;*/
    }
}
