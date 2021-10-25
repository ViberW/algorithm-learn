package com.welph.leecode.part_1_500.part_21_40;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Solution31 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        //由右向左遍历，找到第一个比有边值小的位置 ，之后进行二分法查找当前位置到最右边的比当前值大中的最小值，之前的升序
        int len = nums.length;
        int i = len - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                int i1 = binarySearch(nums, i + 1, len - 1, nums[i]);
                swap(nums, i, i1);
                break;
            }
        }
        reverse(nums, i + 1, len - 1);
    }

    private static int binarySearch(int[] nums, int l, int r, int value) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (nums[mid] > value) {
                l = mid + 1;
            } else if (nums[mid] < value) {
                r = mid - 1;
            } else {
                break;
            }
        }
        mid = (l + r) / 2;
        while (nums[mid] <= value) {
            mid--;
        }
        return mid;
    }


    private static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
