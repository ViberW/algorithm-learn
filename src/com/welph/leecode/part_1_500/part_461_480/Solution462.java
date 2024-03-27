package com.welph.leecode.part_1_500.part_461_480;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

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
 * [1,2,3] => [2,2,3] => [2,2,2]
 */
public class Solution462 {

    public static void main(String[] args) {
        System.out.println(minMoves2(new int[] { 1, 2, 3 }));
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

    /* 下面是官方题解中使用快速排序的算法 */
    Random random = new Random();

    public int minMoves2_1(int[] nums) {
        int n = nums.length, x = quickSelect(nums, 0, n - 1, n / 2), ret = 0;
        for (int i = 0; i < n; ++i) {
            ret += Math.abs(nums[i] - x);
        }
        return ret;
    }

    public int quickSelect(int[] nums, int left, int right, int index) {
        int q = randomPartition(nums, left, right);
        if (q == index) {
            return nums[q];
        } else {
            return q < index ? quickSelect(nums, q + 1, right, index) : quickSelect(nums, left, q - 1, index);
        }
    }

    public int randomPartition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(nums, i, right);
        return partition(nums, left, right);
    }

    public int partition(int[] nums, int left, int right) {
        int x = nums[right], i = left - 1;
        for (int j = left; j < right; ++j) {
            if (nums[j] <= x) {
                ++i;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
