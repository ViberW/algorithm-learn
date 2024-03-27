package com.welph.leecode.part_1_500.part_361_380;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i],
 * answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 */
public class Solution368 {

    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[] { 1, 2, 3 }));
        System.out.println(largestDivisibleSubset(new int[] { 1, 2, 4, 8 }));
        System.out.println(largestDivisibleSubset(new int[] { 2, 3, 5 }));
        System.out.println(largestDivisibleSubset(new int[] { 4, 8, 10, 240 }));
    }

    /**
     *
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] dp = new int[len];
        int[] pre = new int[len];
        int m;
        int max = dp[0] = 1;
        int target = 0;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            pre[i] = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) { // 能整除最大的,那么其他比它小的都能整除
                    if ((m = dp[j] + 1) > dp[i]) { // 对dp中的值进行排序.
                        pre[i] = j; // 用于处理最长维度的
                        dp[i] = m;
                        if (m > max) {
                            max = m;
                            target = i;
                        }
                    }
                }
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        boolean first = pre[target] == target;// 这里基本上就相当于target=0的时候
        do {
            res.addFirst(nums[target]);
            target = pre[target];
        } while (pre[target] != target);
        if (!first) {
            res.addFirst(nums[target]);
        }
        return res;
    }

    /* 官方题解 */
    public List<Integer> largestDivisibleSubset2(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums); // 排序是必要的

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = nums[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要 
                // 当然若是有重复的元素,也好处理, 就是再后序遍历中, 直接加即可, 可使用二维数组保存重复个数和长度即可.
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<Integer>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }
        // 相较于上面的方法 这里就减少了pre[]的空间使用了
        for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i]; // 层层递进
                maxSize--; // 这里减减
            }
        }
        return res;
    }

}
