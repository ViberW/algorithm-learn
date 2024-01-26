package com.welph.leecode.part_1_500.part_441_460;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) +
 * (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) +
 * (-1) + 0 = 0
 * <p>
 * 示例 2：
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 * <p>
 * 提示：
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class Solution454 {

    public static void main(String[] args) {
        System.out.println(fourSumCount(
                new int[] { 1, 2 },
                new int[] { -2, -1 },
                new int[] { -1, 2 },
                new int[] { 0, 2 }));
    }

    /**
     * 哈希表 计算两两相差的不同值
     * 使用hash表计算, 两两数组的不同值. 之后再遍历
     * -----
     * yoghurt
     */
    public static int fourSumCount(int[] nums1, int[] nums2,
            int[] nums3, int[] nums4) {
        int length = nums1.length;
        Map<Long, Integer> map1 = range(nums1, nums2, length);
        Map<Long, Integer> map2 = range(nums3, nums4, length);
        int ret = 0;
        for (Map.Entry<Long, Integer> entry : map1.entrySet()) {
            Integer integer = map2.get(-entry.getKey());
            if (null != integer) {
                ret += integer * entry.getValue();
            }
        }
        return ret;
    }

    private static Map<Long, Integer> range(int[] nums1, int[] nums2, int n) {
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long val = 0L + nums1[i] + nums2[j];
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
        }
        return map;
    }

    /* 官方题解 */
    // 思路一样 但是由于边遍历边比较 所以时间消耗的比较少
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }
}
