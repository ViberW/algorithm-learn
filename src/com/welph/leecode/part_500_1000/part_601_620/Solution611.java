package com.welph.leecode.part_500_1000.part_601_620;

import java.util.Arrays;

/**
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 * <p>
 * 示例 1:
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * <p>
 * 示例 2:
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 * <p>
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class Solution611 {

    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4}));
    }

    /**
     * 三角形定理, 两边之和大于第三边
     * 类似{@link com.welph.leecode.part_1_500.part_1_20.Solution16}
     */
    public static int triangleNumber(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) { //固定一条边
            int k = i + 1;
            for (int j = i + 1; j < nums.length; j++) {//固定第二条边
                while (k + 1 < nums.length && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                count += Math.max(k - j, 0);
            }
        }
        return count;
    }
}
