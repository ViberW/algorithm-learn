package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [最长上升子序列] (不一定连续)
 * -- 有点类似{@link com.welph.leecode.part_121_140.Solution128}
 * -- 这个要严格满足上升且原元素位置相对不变, 但不要求顺序
 * @since 2021/7/19 18:03
 */
public class LongestRiseSeq_27 {

    public static void main(String[] args) {
        int[] nums = {4, 8, 9, 5, 6, 7, 2, 7};
        System.out.println(longestRiseSeq(nums));
    }

    /**
     * 使用动态规划
     */
    public static int longestRiseSeq(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        int index = 0;
        dp[0] = nums[0];
        int res = 1;
        for (int i = 1; i < length; i++) {
            if (dp[index] < nums[i]) {
                dp[++index] = nums[i];
            } else {
                //二分法查找法. dp本身已经是上升的
                //查找到第一个大于或等于 num[i] 的位置, 并替换.
                index = binarySearch(dp, 0, index, nums[i]);
                dp[index] = nums[i];
            }
            res = Math.max(res, index + 1);
        }
        return res;
    }

    /**
     * 返回需要替换到的目标size
     */
    public static int binarySearch(int[] dp, int l, int r, int v) {
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (dp[mid] < v) {
                l = mid + 1;
            } else if (dp[mid] > v) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        //找到它该放入的index
        return l + (r - l) / 2;
    }
}
