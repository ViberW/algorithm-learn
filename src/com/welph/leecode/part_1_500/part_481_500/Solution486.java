package com.welph.leecode.part_1_500.part_481_500;

/**
 * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。
 * 每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），
 * 取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
 * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，
 * 也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,2]
 * 输出：false
 * 解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2
 * ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 false 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,5,233,7]
 * 输出：true
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。
 * <p>
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 10^7
 */
public class Solution486 {

    public static void main(String[] args) {
        System.out.println(PredictTheWinner(new int[] { 1, 5, 2 }));
        System.out.println(PredictTheWinner(new int[] { 1, 5, 233, 7 }));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_461_480.Solution464}
     */
    public static boolean PredictTheWinner(int[] nums) {
        // 找完所有的数据后, 查看是否存在1>2的数值; [i][j] 可能最大的1>2 存储最大的相差值.
        int len = nums.length;
        Integer[][][] dp = new Integer[len][len][2];
        return PredictTheWinner(nums, 0, len - 1, dp, 0) >= 0;
    }

    public static int PredictTheWinner(int[] nums, int i, int j, Integer[][][] dp, int one) {
        if (null != dp[i][j][one]) {
            return dp[i][j][one];
        }
        int max;
        if (i == j) {// 都是包含的
            max = one == 0 ? nums[i] : -nums[i];
        } else {
            if (one == 0) { // 现在该选择1号
                max = Math.max(nums[i] + PredictTheWinner(nums, i + 1, j, dp, 1),
                        nums[j] + PredictTheWinner(nums, i, j - 1, dp, 1));
            } else {// 现在该选择2号
                max = Math.min(-nums[i] + PredictTheWinner(nums, i + 1, j, dp, 0),
                        -nums[j] + PredictTheWinner(nums, i, j - 1, dp, 0));
            }
        }
        dp[i][j][one] = max;
        return max;
    }

    /* 官方题解 */

    public boolean PredictTheWinner2(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                // 这里的减 是因为从小扩到大
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }

}