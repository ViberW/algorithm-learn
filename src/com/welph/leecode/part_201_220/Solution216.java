package com.welph.leecode.part_201_220;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。
 * 组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * <p>
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Solution216 {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    /**
     * 1 - 9的数字 且不存在重合
     * 类同 {@link com.welph.leecode.part_21_40.Solution39}
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(n, ans, combine, 1, k);
        return ans;
    }

    public static void dfs(int target, List<List<Integer>> ans, List<Integer> combine, int val, int k) {
        if (val > 10 || combine.size() > k) {
            return;
        }
        if (target == 0) {
            if (combine.size() == k) {
                ans.add(new ArrayList<>(combine));
            }
            return;
        }
        dfs(target, ans, combine, val + 1, k);
        if (target - val >= 0) {
            combine.add(val);
            dfs(target - val, ans, combine, val + 1, k);
            combine.remove(combine.size() - 1);
        }
    }
}
