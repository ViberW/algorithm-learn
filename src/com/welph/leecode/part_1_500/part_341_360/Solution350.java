package com.welph.leecode.part_1_500.part_341_360;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * <p>
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Solution350 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(
                new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int i : nums1) {
            set.put(i, set.getOrDefault(i, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        Integer integer;
        for (int i : nums2) {
            integer = set.get(i);
            if (null != integer) {
                if (integer == 1) {
                    set.remove(i);
                } else {
                    set.put(i, integer - 1);
                }
                res.add(i);
            }
        }
        int[] r = new int[res.size()];
        int k = 0;
        for (Integer re : res) {
            r[k++] = re;
        }
        return r;
    }
}
