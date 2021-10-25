package com.welph.leecode.part_1_500.part_61_80;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class Solution75 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        int len = nums.length;
        int red = -1;
        int blue = len;
        int v;
        for (int i = 0; i < blue; i++) {
            v = nums[i];
            if (v == 0) {
                do {
                    red++;
                } while (red < i && nums[red] == 0);
                if (red < i) {
                    nums[i] = nums[red];
                    nums[red] = v;
                    if (nums[i] == 2) {
                        i--;
                    }
                }
            } else if (v == 2) {
                do {
                    blue--;
                } while (blue > i && nums[blue] == 2);
                if (blue > i) {
                    nums[i] = nums[blue];
                    nums[blue] = v;
                    if (nums[i] == 0) {
                        i--;
                    }
                }
            }
        }
    }

    public static void sortColors2(int[] nums) {
        int len = nums.length;
        int red = -1;
        int blue = len;
        int v;
        for (int i = 0; i < blue; i++) {
            v = nums[i];
            if (v == 0) {
                if (++red != i) {
                    nums[i] = nums[red];
                    nums[red] = v;
                } else {
                    red = i;
                }
            } else if (v == 2) {
                do {
                    blue--;
                } while (blue > i && nums[blue] == 2);
                if (blue > i) {
                    nums[i] = nums[blue];
                    nums[blue] = v;
                    if (nums[i] == 0) {
                        i--;
                    }
                }
            }
        }
    }
}
