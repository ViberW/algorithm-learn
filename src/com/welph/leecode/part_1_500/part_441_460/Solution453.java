package com.welph.leecode.part_1_500.part_441_460;

/**
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 答案保证符合 32-bit 整数
 */
public class Solution453 {

    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1, 2, 3}));
    }

    /**
     * 数学计算:
     * 最初所有数据的总和: sum
     * 若移动m次, 最终值为 k , 则总和增长 m(n-1)
     * 有表达式: sum + m(n-1) = x*n
     * 假设最小值为 min_val , 则x = min_val+m --因为只有最小值经历了所有的增长
     * 简化为: sum + m(n-1) = (min_val+m)n
     * => m = sum - min_val*n
     */
    public static int minMoves(int[] nums) {
        int sum = 0;
        int min_value = nums[0];
        for (int num : nums) {
            if (num < min_value) {
                min_value = num;
            }
            sum += num;
        }
        return sum - min_value * nums.length;
    }
}
