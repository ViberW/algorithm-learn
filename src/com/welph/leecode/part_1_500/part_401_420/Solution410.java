package com.welph.leecode.part_1_500.part_401_420;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 nums 和一个整数m ，你需要将这个数组分成m个非空的连续子数组。
 * 设计一个算法使得这m个子数组各自和的最大值最小。
 * <p>
 * 示例 1：
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * <p>
 * 示例 3：
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class Solution410 {

    public static void main(String[] args) {
        System.out.println(splitArray(new int[] { 7, 2, 5, 10, 8 }, 2));
    }

    /**
     * todo 没思路
     * --- 看题解了 --- 这都没想到...
     * 方法1: 动态规划 f[i][j]表示前i个数共分成j段数组的最大值的最小值
     * 假定第k个数在第j段的开始,即[0,k-1]被分割为j-1段, [k+1,j]为第j段
     * 此时的f[i][j] = max(f[k][j-1], sum(k+1,j)]) 的最小值
     * 由于f[i][j]必须分成j段数组, 代表i>=j, 对于i<j,则f[i][j]可以设定一个最大值
     * 对于f[0][0] = 0
     */
    public static int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        // 快速计算
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) { // 按理来说这里的j
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

    /**
     * 方法二 ; 有点复杂 好想法
     */
    public int splitArray2(int[] nums, int m) {
        // 单个最大值, 总和值
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];// 总和
            if (left < nums[i]) {
                left = nums[i]; // 单个值的最大值
            }
        }
        // 拆分子数组 至少最大值范围在l到r内
        while (left < right) {
            // 二分的贪心思想
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {// 如果满足, 则尝试不断缩减rgiht
                right = mid;
            } else {
                left = mid + 1;// 否则不断调大中间阈值
            }
        }
        return left;
    }

    public boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {// 每当一段子数组超过x则拆分
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }

}
