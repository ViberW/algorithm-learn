package com.welph.leecode.part_301_320;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * <p>
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
public class Solution312 {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));
        int[] nums1 = {1, 5};
        System.out.println(maxCoins(nums1));
    }

    /**
     * 动态规划 dp表示 j-i范围内的最大值.
     */
    public static int maxCoins(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len + 2][len + 2]; //开区间

        int numI;
        int numJ;
        for (int j = 2; j <= len + 1; j++) {
            numJ = getValue(nums, j - 1);
            for (int i = j - 1; i >= 0; i--) {//一定要反着来, 否则dp[k + 1][j]最大值没机会计算了
                numI = getValue(nums, i - 1);
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + numI * getValue(nums, k - 1) * numJ);
                }
            }
        }
        return dp[0][len + 1]; //1-len范围内的因为是开区间
    }

    private static int getValue(int[] nums, int index) {
        if (index < 0) {
            return 1;
        }
        if (index >= nums.length) {
            return 1;
        }
        return nums[index];
    }

}
