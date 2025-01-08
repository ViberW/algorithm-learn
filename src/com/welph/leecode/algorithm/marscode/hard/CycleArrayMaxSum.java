package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小C有一个长度为 n 的环形整数数组 nums，他希望找到该数组中的非空子数组的最大可能和。
 *
 * 环形数组的特点是它的末端和开头相连，形式上，nums[i] 的下一个元素是 nums[(i + 1) % n]，
 * 而 nums[i] 的前一个元素是 nums[(i - 1 + n) % n]。
 *
 * 你需要帮助小C找到这个环形数组的最大子数组和，
 * 注意每个元素最多只能在子数组中出现一次，子数组是连续的，并且子数组可以跨越数组的末端连接到开头
 */
public class CycleArrayMaxSum {

    //{@link GreedySweet}
    //对solution2简化
    public static int solution(int[] nums) {
        int result = nums[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int limit = n + i;
            int sum = 0;
            for (int j = i; j < limit; j++) {
                //i->j
                sum += nums[j % n];
                result = Math.max(result, sum);
                if (sum < 0) {
                    sum = 0;
                }
            }
        }
        return result;
    }

    public static int solution2(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int[] dp = new int[nums.length];
        int[] dp2 = new int[nums.length]; //以i为结尾的最小值 ....有点问题
        dp[0] = nums[0];
        dp2[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            dp2[i] = Math.min(dp2[i - 1] + nums[i], nums[i]);
            result = Math.max(result, Math.max(dp[i], sum - dp2[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-1, -2, 3, -2}) == 3);
        System.out.println(solution(new int[]{-5, -3, 5}) == 5);
        System.out.println(solution(new int[]{-3, -1, 2, -1}) == 2);
        System.out.println(solution(new int[]{-2, -3, -1}) == -1);
        System.out.println(solution(new int[]{16, -10, 2, -16, 5, -5, 11, 13, -12, 15, 0}) == 43);
        System.out.println(solution(new int[]{5, 6, -3, 14, 9, 13, 12, -13, -9, 8, 5, -10, -15, -8, 11}));
    }
}
