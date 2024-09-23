package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 * <p>
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^5 <= nums[i] <= 10^5
 */
public class Solution665 {

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[] { 4, 2, 3 }));
        System.out.println(checkPossibility(new int[] { 4, 2, 1 }));
    }

    public static boolean checkPossibility(int[] nums) {
        int n = nums.length - 1;
        int negative = 0;
        for (int i = 1; i <= n; i++) {
            if (nums[i] < nums[i - 1]) {
                // 说明有转折
                if (++negative > 1) {
                    return false;
                }
                if (i > 1 && i < n && (nums[i + 1] < nums[i - 1] && nums[i] < nums[i - 2])) {
                    return false;
                }
            }
        }
        return true;
    }

    // 官方题解
    public boolean checkPossibility1(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

}
