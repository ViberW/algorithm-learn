package com.welph.leecode.part_1_500.part_201_220;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法
 */
public class Solution209 {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        System.out.println(minSubArrayLen(7, nums));
    }

    /**
     * 使用双指针来表示O(n)
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int l = 0;
        int lastVal = 0;
        int minLen = nums.length + 1;
        for (int r = 0; r < nums.length; r++) {
            int val = lastVal + nums[r];
            if (val > s) {
                // 这里的循环本质上是不会执行很多次的;
                while (l < r && val - nums[l] > s) {
                    val -= nums[l];
                    l++;
                }
                minLen = Math.min(minLen, r - l + 1);
                if (minLen == 1) {
                    break;
                }
            }
            lastVal = val;
        }
        return minLen > nums.length ? 0 : minLen;
    }

    /**
     * O(n log n) 应该使用二分法
     */
    /* 官方题解 */
    /*
     * 前缀和+二分查找
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        // --------------------由于所有数为正数, 使用前缀和 保证了二分的排序要求
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    //和我的方法一样, 只不过看起来舒服很多
    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


}
