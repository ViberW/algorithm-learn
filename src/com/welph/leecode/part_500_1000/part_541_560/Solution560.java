package com.welph.leecode.part_500_1000.part_541_560;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
public class Solution560 {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
    }

    /**
     * 前缀和  连续子数组
     * pre[i]-pre[j] == k
     */
    public static int subarraySum(int[] nums, int k) {
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pre += nums[i];
        }
        //对于i处来说, 仅仅需要找到preSum[i]+k 是否存在
        int ret = 0;
        int c;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {//即后面是否存在等于该值的数据.
            if ((c = map.getOrDefault(nums[i] - k, 0)) > 0) {
                ret += c;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ret;
    }
}
