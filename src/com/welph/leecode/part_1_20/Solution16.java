package com.welph.leecode.part_1_20;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1,2,1,-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @author: Admin
 * @date: 2019/5/15
 * @Description: {相关描述}
 */
public class Solution16 {

    public static void main(String[] args) {
        int[] nums = new int[]{-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33};
        int target = 0;
        System.out.println(threeSumClosest(nums, target));

    }

    public static int threeSumClosest(int[] nums, int target) {
        //此处就是使用三指针法了  但是此处不需要排序了
        Arrays.sort(nums);
        int len = nums.length;
        int l;
        int r;
        int min = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        int cur;
        int curMin;
        for (int i = 0; i < len; i++) {
            l = i + 1;
            r = len - 1;
            while (l < r) {
                cur = nums[i] + nums[l] + nums[r];
                curMin = Math.abs(target - (nums[i] + nums[l] + nums[r]));
                if (min >= curMin) {
                    min = curMin;
                    result = cur;
                }
                if (target > cur) {
                    do {
                        l++;
                    } while (l < r && nums[l] == nums[l - 1]);
                } else if (target < cur) {
                    do {
                        r--;
                    }
                    while (r > l && nums[r] == nums[r + 1]);
                } else {
                    return cur;
                }
            }
        }
        return result;
    }
}
