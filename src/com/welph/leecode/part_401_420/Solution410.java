package com.welph.leecode.part_401_420;

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
        System.out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    public static int splitArray(int[] nums, int m) {

        return 0;
    }
}