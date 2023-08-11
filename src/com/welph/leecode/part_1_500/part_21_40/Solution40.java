package com.welph.leecode.part_1_500.part_21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1:
 * <p>
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例2:
 * <p>
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Solution40 {

    public static void main(String[] args) {
        int[] nums = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        System.out.println(combinationSum2(nums, target));
    }

    /**
     * 和上一题思路差不多，只不过考虑到下一次的索引应该+1，且保证从index开始后的重复数据不再读取
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 注意，这个题目是存在重复数值的数组的且每个值使用一次
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

    /*
     * 官方题解
     */
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2Two(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {// 标记每个数据的个数
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[] { num, 1 });
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {// 说明用完了数值,或者数值已经无法满足
            return;
        }

        dfs(pos + 1, rest);// 放弃当前数值

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {// 使用当前值时, 用到值的最多次数 -- 能够保证不会过多的创建sequence
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }
}
