package com.welph.leecode.part_500_1000.part_521_540;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 */
public class Solution525 {

    public static void main(String[] args) {
        System.out.println(findMaxLength1(new int[] { 0, 1 }));
        System.out.println(findMaxLength1(new int[] { 0, 1, 0 }));
        System.out.println(findMaxLength1(new int[] { 0, 1, 0, 1, 1, 0, 0 }));
    }

    /**
     * {前缀和} 和 {hash} 的最好诠释
     */
    public static int findMaxLength1(int[] nums) {
        // 仅仅需要 i,j 范围内的 sum(i,j) * 2 = j-i+1
        int max = 0;
        // 设以j为端点, 找到最长的负数值
        Map<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        Integer preIndex;
        for (int i = 0; i < nums.length; i++) {
            pre = pre + zeroToNegative(nums[i]);
            if (pre == 0 && i >= max) {
                max = i + 1;
            }
            preIndex = map.get(pre);// 找到最早的一段等于pre的地方
            if (preIndex != null) {
                max = Math.max(max, i - preIndex); /// 剔除它及时相等的了
            } else {
                map.put(pre, i);
            }
        }
        return max;
    }

    private static int zeroToNegative(int val) {
        return val == 0 ? -1 : val;
    }

    // 类似hash的替代方案: 前缀和
    public int findMaxLength3(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++)
            if (nums[i] == 0)
                nums[i] = -1;

        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + nums[i];

        Map<Integer, Integer> presum_first_idx = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            if (presum_first_idx.containsKey(presum[i]) == false)
                presum_first_idx.put(presum[i], i);
        }

        int res = 0;
        for (int i = 1; i < n + 1; i++)
            res = Math.max(res, i - presum_first_idx.get(presum[i]));
        return res;
    }

    public static int findMaxLength(int[] nums) {
        // 仅仅需要 i,j 范围内的 sum(i,j) * 2 = j-i+1
        // 超时
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int max = 0;
        int delta;
        for (int i = 0; i < nums.length; i++) {
            if (nums.length - 1 - i < max) {
                break;
            }
            for (int j = nums.length - 1; j > i + max - 1; j--) {
                delta = preSum[j + 1] - preSum[i];
                if (j - i + 1 == delta * 2) {
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }
        return max;
    }
}
