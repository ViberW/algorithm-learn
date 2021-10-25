package com.welph.leecode.part_1_500.part_21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class Solution40 {

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSum2(nums, target));
    }

    /**
     * 和上一题思路差不多，只不过考虑到下一次的索引应该+1，且保证从index开始后的重复数据不再读取
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //注意，这个题目是存在重复数值的数组的且每个值使用一次
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
        int index;
        int curr;
        do {
            integers = list.pollFirst();
            value = values.pollFirst();
            index = indexs.pollFirst();
            if (null == integers) {
                break;
            }
            for (int i = index; i < len; i++) {
                if (i != index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
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
                    indexs.add(i + 1);
                }
            }
        } while (!list.isEmpty());
        return result;
    }
}
