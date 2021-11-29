package com.welph.leecode.part_1_500.part_441_460;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution456 {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(find132pattern(new int[]{-1, 3, 2, 0}));
    }

    /**
     * i < j < k
     * nums[i] < nums[k] < nums[j]
     * -----------并不一定是连续的: 凸
     * -----------没有思考出来 todo 官方题解思路
     * //固定j , 此时记录[0,j-1]范围内的最小值作为i
     * 那么只需要从[j+1, n-1]范围内是否存在nums[i] < nums[k] < nums[j]
     */
    public static boolean find132pattern(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        int numi = nums[0];
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = 2; i < length; i++) {
            tree.put(nums[i], tree.getOrDefault(nums[i], 0) + 1);
        }
        for (int j = 1; j < length - 1; j++) {
            if (nums[j] > numi + 1) {
                //查找j的右侧数据是否存在大于numi的最小值,
                Integer numk = tree.ceilingKey(numi + 1);
                if (numk != null && numk < nums[j]) {
                    return true;
                }
            }
            if (nums[j] < numi) {
                numi = nums[j];
            }
            Integer integer = tree.get(nums[j + 1]);
            if (integer == 1) {
                tree.remove(nums[j + 1]);
            } else {
                tree.put(nums[j + 1], integer - 1);
            }
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        //单调栈(减)
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) { //上一次遍历找到的maxK的结果
                return true;
            }
            //这里相当于是将i 放入单调栈中,  maxK 则能够代表 j<k 且nums[k]<nums[j]
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);//保存maxK
            }
        }
        return false;
    }
}
