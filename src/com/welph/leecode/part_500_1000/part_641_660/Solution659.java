package com.welph.leecode.part_500_1000.part_641_660;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个按 非递减顺序 排列的整数数组 nums 。
 * 请你判断是否能在将 nums 分割成 一个或多个子序列 的同时满足下述 两个 条件：
 * <p>
 * 每个子序列都是一个 连续递增序列（即，每个整数 恰好 比前一个整数大 1 ）。
 * 所有子序列的长度 至少 为 3 。
 * 如果可以分割 nums 并满足上述条件，则返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,3,4,5]
 * 输出：true
 * 解释：nums 可以分割成以下子序列：
 * [1,2,3,3,4,5] --> 1, 2, 3
 * [1,2,3,3,4,5] --> 3, 4, 5
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,3,4,4,5,5]
 * 输出：true
 * 解释：nums 可以分割成以下子序列：
 * [1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
 * [1,2,3,3,4,4,5,5] --> 3, 4, 5
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,4,5]
 * 输出：false
 * 解释：无法将 nums 分割成长度至少为 3 的连续递增子序列。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 * nums 按非递减顺序排列
 */
public class Solution659 {

    public static void main(String[] args) {
        System.out.println(isPossible(new int[]{1, 2, 3, 4, 5}));
        System.out.println(isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(isPossible(new int[]{1, 2, 3, 4, 4, 5}));
    }

    /**
     * 有观看评论的解析
     * [1, 2, 3, 3, 4, 5]
     * 贪心:  这种当初单纯的以为 1,2,3,4,5和3 两段子序列
     * 但要转变思路:
     * 顺序遍历,
     * 扫描到1  前面没有元素, 即没有以0结尾的子序列, 所以它本身要满足条件一定是往后找2,3
     * 2 被消耗了一个
     * 3 被消耗了一个
     * 扫描到3, 前面不存在以2结尾的子序列, 所以它本身要满足一定往后找4,5
     * 4 被消耗了一个
     * 5 被消耗了一个
     * -------------------------
     * 按照上面的思路>  主要的处理在于n遍历, 如何不回头
     */
    public static boolean isPossible(int[] nums) {
        //用map保存合法的子序列的结尾数值, value=响应的数量
        //还要有个地方去保存不合法的数量
        Map<Integer, Integer> legal = new HashMap<>();
        Map<Integer, Integer> one = new HashMap<>();
        Map<Integer, Integer> two = new HashMap<>();
        Integer count;
        for (int num : nums) {
            //尝试先填补之前的未定的子序列
            count = one.get(num - 1);
            if (null != count) {
                if (count == 1) {
                    one.remove(num - 1);
                } else {
                    one.put(num - 1, count - 1);
                }
                two.put(num, two.getOrDefault(num, 0) + 1);
                continue;
            }
            count = two.get(num - 1);
            if (null != count) {
                if (count == 1) {
                    two.remove(num - 1);
                } else {
                    two.put(num - 1, count - 1);
                }
                legal.put(num, legal.getOrDefault(num, 0) + 1);
                continue;
            }
            count = legal.get(num - 1);
            if (null != count) {
                if (count == 1) {
                    legal.remove(num - 1);
                } else {
                    legal.put(num - 1, count - 1);
                }
                legal.put(num, legal.getOrDefault(num, 0) + 1);
            } else {
                one.put(num, one.getOrDefault(num, 0) + 1);
            }
        }
        return one.isEmpty() && two.isEmpty();
    }
}
