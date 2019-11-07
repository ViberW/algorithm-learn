package com.welph.leecode.part_41_60;

import java.util.*;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Solution46 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<>());
        List<Integer> peek;
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                peek = result.peek();
                if (peek.size() > i) {
                    break;
                }
                int v = nums[i];
                for (int k = 0; k < peek.size(); k++) {
                    int j = k;
                    result.addLast(new ArrayList<Integer>(peek) {{
                        add(j, v);
                    }});
                }
                peek.add(v);
                result.addLast(new ArrayList<>(peek));
                result.pop();
            }
        }
        return result;
    }
}
