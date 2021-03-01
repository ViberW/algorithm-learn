package com.welph.leecode.part_201_220;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，
 * 使得nums [i] 和nums [j]的差的绝对值小于等于 t ，
 * 且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * 示例1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * <p>
 * 示例 3:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 */
public class Solution220 {

    public static void main(String[] args) {
       /* int[] nums = {1, 5, 9, 1, 5, 9};
        System.out.println(containsNearbyAlmostDuplicate(nums, 2, 3));
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(containsNearbyAlmostDuplicate(nums1, 3, 0));*/
        int[] nums2 = {-2147483648, 2147483647};
        System.out.println(containsNearbyAlmostDuplicate(nums2, 1, 1));
    }

    /**
     * 滑动窗口 k 个长度保存.  记录窗口的最小值和最大值  则判断最小和最大值是否能够匹配
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        Long targetVal = Long.valueOf(t);
        Integer v;
        for (int i = 0; i < nums.length; i++) {
            //比当前值大的最小值
            v = set.ceiling(nums[i]);
            if (v != null && v <= nums[i] + targetVal) return true;
            //比当前值小的最大值
            v = set.floor(nums[i]);
            if (v != null && nums[i] <= v + targetVal) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 官方的桶解法
     * 通过对t相除. 切分数值对应的桶
     * 1. 若是相同值, 则在同一个桶内, 必定小于t
     * 2. 再检查前后两个桶内, 是否存在, 防止有前后两个数据临界点
     * <p>
     * 最终移除掉-k前的数据, 保证始终维持在k的范围内
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            if (d.containsKey(m))
                return true;
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}
