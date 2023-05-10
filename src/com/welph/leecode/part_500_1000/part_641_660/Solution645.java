package com.welph.leecode.part_500_1000.part_641_660;

import java.util.Arrays;

/**
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
 * 导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 */
public class Solution645 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findErrorNums(new int[]{1, 2, 2, 4})));
        System.out.println(Arrays.toString(findErrorNums(new int[]{1, 1})));
        System.out.println(Arrays.toString(findErrorNums(new int[]{3, 2, 2})));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_281_300.Solution287}
     */
    public static int[] findErrorNums(int[] nums) {
        int[] ret = new int[2];
        int val;
        for (int i = 0; i < nums.length; ) {
            val = nums[i];
            if (nums[val - 1] != val) {
                swap(nums, val - 1, i);
            } else if (val != i + 1) {
                ret[0] = val;
                i++;
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ret[1] = i + 1;
                break;
            }
        }
        return ret;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
