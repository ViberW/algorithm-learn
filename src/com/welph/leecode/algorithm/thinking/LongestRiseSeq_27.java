package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [最长上升子序列] (不一定连续)
 * -- 有点类似{@link com.welph.leecode.part_1_500.part_121_140.Solution128}
 * -- 这个要严格满足上升且原元素位置相对不变, 但不要求顺序
 * @since 2021/7/19 18:03
 */
public class LongestRiseSeq_27 {

    public static void main(String[] args) {
        int[] nums = {4, 8, 9, 5, 6, 7, 2, 7};
        System.out.println(longestRiseSeq(nums));
    }

    /**
     * 贪心+二分
     */
    public static int longestRiseSeq(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        int index = 0;
        dp[0] = nums[0];
        for (int i = 1; i < length; i++) {
            if (dp[index] < nums[i]) {
                dp[++index] = nums[i];
            } else {
                //这里一定不能去修改index, 因为保证原dp的存在用于后面的校验
                dp[binarySearch(dp, 0, index, nums[i])] = nums[i];
            }
        }
        return index + 1;
    }

    /**
     * 返回需要替换到的目标size
     */
    public static int binarySearch(int[] dp, int l, int r, int v) {
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (dp[mid] <= v) {
                l = mid + 1;
            } else if (dp[mid] > v) {
                r = mid - 1;
            }
        }
        return l;
    }
}
