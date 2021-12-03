package com.welph.leecode.part_1_500.part_461_480;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。
 * 您可以假设数组的长度最多为10000。
 * <p>
 * 例如:
 * 输入:
 * [1,2,3]
 * 输出:
 * 2
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class Solution462 {

    public static void main(String[] args) {
        System.out.println(minMoves2(new int[]{1, 2, 3}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_441_460.Solution453}
     * ---------------------------
     * 这里就需要对数据排序, 并按照中位数的差距
     */
    public static int minMoves2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int mid = nums.length / 2;
        int midVal = nums[mid];
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == mid) {
                continue;
            }
            ret += Math.abs(nums[i] - midVal);
        }
        return ret;
    }

}
