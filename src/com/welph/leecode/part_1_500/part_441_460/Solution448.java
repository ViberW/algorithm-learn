package com.welph.leecode.part_1_500.part_441_460;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
public class Solution448 {

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));
        System.out.println(findDisappearedNumbers(new int[] { 1, 1 }));
    }

    /**
     * 不使用额外空间, 复杂度为O(n)
     * 根据占据的位置 27.63%
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n;) {
            if (nums[i] == 0) {
                i++;
            } else if (nums[i] != i + 1) {
                if (nums[nums[i] - 1] == nums[i]) {
                    nums[i] = 0;
                    i++;
                } else {
                    swap(nums, nums[i] - 1, i);
                }
            } else {
                i++;
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    public static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    /**
     * 官方: 原地, 仅仅计算小于N的就可以了. 棒棒棒
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
