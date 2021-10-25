package com.welph.leecode.part_1_500.part_201_220;

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
        int[] nums = {2, 3, 1, 2, 4, 3};
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
                //这里的循环本质上是不会执行很多次的;
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
     *  O(n log n)  应该使用二分法
     */
}
