package com.welph.leecode.part_500_1000.part_521_540;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
 * <p>
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
 * <p>
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1) 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 */
public class Solution532 {

    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        System.out.println(findPairs(new int[]{1, 2, 3, 4, 5}, 1));
        System.out.println(findPairs(new int[]{1, 3, 1, 5, 4}, 0));
    }

    /**
     * 最容易想到的就是 先排序  通过双指针 前后判断就好.  这里需要不同的数据
     */
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int total = 0;
        int j;
        for (int i = 0; i < nums.length - 1; ) { //这里是n-1
            j = i + 1;
            total += binarySearch(nums, j, nums.length - 1, nums[i] + k);
            for (; j < nums.length && nums[i] == nums[j]; j++) {
            }
            i = j;
        }
        return total;
    }

    private static int binarySearch(int[] nums, int l, int r, int target) {
        int mid;
        while (l < r) {
            mid = (l + r + 1) / 2;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return nums[l] == target ? 1 : 0;
    }
}
