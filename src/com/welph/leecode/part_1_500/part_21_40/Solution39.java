package com.welph.leecode.part_1_500.part_21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class Solution39 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(nums, target));
        System.out.println(combinationSum2(nums, target));
    }

    /**
     * 回溯算法
     */
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        //注意，数字可以是无限次数选取，这个数组不一定是有序的
        //先假设都已经排好序了
        Arrays.sort(candidates);
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<List<Integer>> list = new LinkedList<>();
        LinkedList<Integer> values = new LinkedList<>();
        LinkedList<Integer> indexs = new LinkedList<>();
        list.add(new ArrayList<>());
        values.add(0);
        indexs.add(0);
        List<Integer> integers;
        List<Integer> news;
        Integer value;
        Integer index;
        int curr;
        do {
            integers = list.pollFirst();
            value = values.pollFirst();
            index = indexs.pollFirst();
            if (null == integers) {
                break;
            }
            for (int i = index; i < len; i++) {
                curr = value + candidates[i];
                if (curr > target) {
                    break;
                } else if (curr == target) {
                    news = new ArrayList<>(integers);
                    news.add(candidates[i]);
                    result.add(news);
                } else {
                    news = new ArrayList<>(integers);
                    news.add(candidates[i]);
                    list.add(news);
                    values.add(curr);
                    indexs.add(i);
                }
            }
        } while (!list.isEmpty());
        return result;
    }

    /**
     * 用实体感觉还是慢了，速度不快
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(new ArrayList<>(), 0, 0));
        Info info;
        Info copy;
        int curr;
        do {
            info = list.pollFirst();
            if (null == info) {
                break;
            }
            for (int i = info.index; i < len; i++) {
                curr = info.value + candidates[i];
                if (curr > target) {
                    break;
                } else if (curr == target) {
                    copy = info.copy();
                    copy.list.add(candidates[i]);
                    result.add(copy.list);
                } else {
                    copy = info.copy();
                    copy.list.add(candidates[i]);
                    list.add(copy);
                    copy.value = curr;
                    copy.index = i;
                }
            }
        } while (!list.isEmpty());
        return result;
    }


    public static class Info {
        List<Integer> list;
        Integer value;
        Integer index;

        public Info(List<Integer> list, Integer value, Integer index) {
            this.list = list;
            this.value = value;
            this.index = index;
        }

        public Info copy() {
            return new Info(this.list == null ? new ArrayList<>() : new ArrayList<>(this.list),
                    this.value, this.index);
        }
    }
}
