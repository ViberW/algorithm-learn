package com.welph.leecode.part_1_500.part_321_340;

import com.welph.leecode.algorithm.thinking.LongestRiseSeq_27;

/**
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * <p>
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 */
public class Solution334 {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(increasingTriplet(new int[]{5, 5, 5, 5, 6}));
        System.out.println(increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
        System.out.println(increasingTriplet(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2}));
    }

    /**
     * {@link LongestRiseSeq_27} 贪心+二分
     */
    public static boolean increasingTriplet(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return false;
        }
        int[] dp = new int[length];
        int index = 0;
        dp[0] = nums[0];
        for (int i = 1; i < length; i++) {
            if (dp[index] < nums[i]) {
                dp[++index] = nums[i];
            } else {
                dp[binarySearch(dp, 0, index, nums[i])] = nums[i];
            }
            if (index >= 2) {
                return true;
            }
        }
        return false;
    }

    public static int binarySearch(int[] dp, int l, int r, int v) {
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (dp[mid] < v) {
                l = mid + 1;
            } else if (dp[mid] >= v) {
                r = mid - 1;
            }
        }
        return l;
    }

    /* 官方题解 --更适合的方法 */
    //双向遍历
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]); //每个点的最小值
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]); //每个点的最大值
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) { //若存在说明符合
                return true;
            }
        }
        return false;
    }

    //贪心算法
    public boolean increasingTriplet3(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        //first 表示三元中的第一个数   second表示三元中的第二个数
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {//相当于尽可能的去缩小second
                second = num;
            } else {//相当于尽可能的去缩小first
                first = num;
            }
        }
        return false;
    }

}
