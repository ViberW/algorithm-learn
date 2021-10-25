package com.welph.leecode.part_1_500.part_201_220;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * .     偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class Solution213 {

    /**
     * 类同{@link com.welph.leecode.part_1_500.part_181_200.Solution198}
     */
    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(rob(nums));
    }

    /**
     * 还要考虑最后一个和最开始的相连问题
     * //若是最后一个选择的时候, 不包含最开始就可以了
     * <p>
     * 最后一次的判断 =  val2,   val(不包含nums[0])的之间的最大值
     * -- 一次算好包含和不包含的最大值
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        int val1 = nums[0];
        int val2 = Math.max(nums[0], nums[1]);
        int val3 = 0;
        int val4 = nums[1];
        int tmp;
        int i = 2;
        for (; i < len; i++) {
            tmp = val4;
            val4 = Math.max(val3 + nums[i], val4);
            val3 = tmp;

            tmp = val2;
            val2 = i == len - 1 ? Math.max(val4, Math.max(val1, val2))
                    : Math.max(val1 + nums[i], val2);
            val1 = tmp;
        }
        return val2;
    }

    /**
     * 第二种解法就是, 算两遍, 再统一比较最大值
     * 1. 计算包含第一个房间的最大值
     * 2. 不包含第一个房间的最大值
     */
    public static int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(robCross(nums, 0, nums.length - 1),
                robCross(nums, 1, nums.length));
    }

    public static int robCross(int[] nums, int start, int end) {
        int val1 = 0;
        int val2 = 0;
        int tmp;
        for (int i = start; i < end; i++) {
            tmp = val2;
            val2 = Math.max(val1 + nums[i], val2);
            val1 = tmp;
        }
        return val2;
    }
}
