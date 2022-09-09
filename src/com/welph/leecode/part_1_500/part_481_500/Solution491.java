package com.welph.leecode.part_1_500.part_481_500;

import java.security.Key;
import java.util.*;

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * <p>
 * 示例 1：
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * <p>
 * 示例 2：
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class Solution491 {

    public static void main(String[] args) {
        System.out.println(findSubsequences(new int[]{4, 6, 7, 7}));
        System.out.println(findSubsequences(new int[]{4, 4, 3, 2, 1}));
    }

    /**
     * 每个尾节点  往前寻找到一个最小值. 并且构建list.  同时使用
     * 相同key 的包含多一层的数据  保存最近一次的相同key的值, 加上相同的val的值.
     * [6, [46]]
     * [7,[47][67][467]]
     * [6, [46] [466] [66]
     * 6, 466 4666 666 + 66 + 46
     * <p>
     * 1. n=7  [477][677][4677]  上一次7的索引位置.
     * ----- 时间不理想:  11.08%  空间: 30.63%
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        TreeMap<Integer, List<List<Integer>>> rest = new TreeMap<>();
        rest.put(nums[0], Collections.emptyList());
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            Map<Integer, List<List<Integer>>> sub = rest.headMap(num, true);
            if (!sub.isEmpty()) {
                List<List<Integer>> result = new ArrayList<>();
                sub.forEach((end, list) -> {
                    for (List<Integer> r : list) {
                        result.add(new ArrayList<Integer>(r) {{
                            add(num);
                        }});
                    }
                    result.add(new ArrayList<Integer>(2) {{
                        add(end);
                        add(num);
                    }});
                });
                rest.put(num, result);
            } else {
                rest.put(num, Collections.emptyList());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (List<List<Integer>> value : rest.values()) {
            if (!value.isEmpty()) {
                res.addAll(value);
            }
        }
        return res;
    }
}
