package com.welph.leecode.part_1_500.part_181_200;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * <p>
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法
 */
public class Solution189 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = { -1, -100, 3, 99 };
        rotate(nums1, 2);
        System.out.println(Arrays.toString(nums1));
    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        int i = 0;
        int last = nums[i];
        int pre = 0;
        int count = 0;
        while (count < len) {
            int index = (k + i) % len;
            int tmp = nums[index];
            nums[index] = last;
            last = tmp;
            i = index;
            if (count > 0 && pre == i) {
                i = ++pre;
                last = nums[i];
            }
            count++;
        }
    }

    /* 官方题解 */
    // 使用额外数组
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    // 和我的方法一致, 只是更好看点
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);// 通过找公约数, 减少了遍历的耗时 --- nice
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    // 数组翻转
    /*
     * 例如: 1 2 3 4 5 6 7
     * 翻转[0,n): 7 6 5 4 3 2 1
     * 翻转[0,k): 5 6 7 4 3 2 1
     * 翻转[k,n): 5 6 7 1 2 3 4
     */
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        // 翻转[0,n)
        reverse(nums, 0, nums.length - 1);
        // 翻转[0,k)
        reverse(nums, 0, k - 1);
        // 翻转[k,n)
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

}
