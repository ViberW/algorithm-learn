package com.welph.leecode.part_1_500.part_481_500;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class Solution494 {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays1(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays1(new int[]{1}, 1));
        System.out.println(findTargetSumWays1(new int[]{1, 2, 5, 4, 1}, 3));
    }

    /**
     * 单纯的回溯算法 太慢: 6.93%
     */
    public static int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, 0, target);
    }

    public static int findTargetSumWays(int[] nums, int i, int target) {
        if (i == nums.length) {
            return target == 0 ? 1 : 0;
        }
        return findTargetSumWays(nums, i + 1, target - nums[i])
                + findTargetSumWays(nums, i + 1, target + nums[i]);
    }

    /**
     * 针对上面的改进, 通过存储已经计算过的结果
     * 回溯返回[i,length]的可能的所有结果.  时间提高了  但空间利用率低.
     */
    public static int findTargetSumWays1(int[] nums, int target) {
        Map<Integer, Integer>[] map = new HashMap[nums.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = new HashMap<>();
        }
        return findTargetSumWays1(nums, 0, target, map);
    }

    public static int findTargetSumWays1(int[] nums, int i, int target, Map<Integer, Integer>[] map) {
        if (i == nums.length) {
            return target == 0 ? 1 : 0;
        }
        if (map[i].containsKey(target)) {
            return map[i].get(target);
        }
        int sum = findTargetSumWays1(nums, i + 1, target - nums[i], map)
                + findTargetSumWays1(nums, i + 1, target + nums[i], map);
        map[i].put(target, sum);
        return sum;
    }

    /**
     * >>> 官网题解: 动态规划
     * ---
     * 记数组的元素和为 sum，添加 - 号的元素之和为 neg，则其余添加 + 的元素之和为 sum−neg
     * >> (sum-neg) -neg = target -> neg = (sum-target)/2
     * 因为neg为非负整数. 则sum-target大于0且能被2整除
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) { //sum-target大于0且能被2整除
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];  //存储每种结果的可能性
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
