package com.welph.leecode.part_321_340;

import java.util.Arrays;

/**
 * 给定长度分别为m和n的两个数组，其元素由0-9构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n)个数字拼接成一个新的数，
 * 要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为k的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * <p>
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * <p>
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 */
public class Solution321 {

    public static void main(String[] args) {
        /*int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        System.out.println(Arrays.toString(maxNumber1(nums1, nums2, 5)));
        int[] nums3 = {6, 7};
        int[] nums4 = {6, 0, 4};
        System.out.println(Arrays.toString(maxNumber1(nums3, nums4, 5)));
        int[] nums5 = {3, 9};
        int[] nums6 = {8, 9};
        System.out.println(Arrays.toString(maxNumber1(nums5, nums6, 3)));*/

        int[] nums7 = {7, 6, 1, 9, 3, 2, 3, 1, 1};
        int[] nums8 = {4, 0, 9, 9, 0, 5, 5, 4, 7};
        System.out.println(Arrays.toString(maxNumber1(nums7, nums8, 9)));
    }

    /**
     * 单调栈,合并
     */
    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int len1 = nums1.length;
        int len2 = nums2.length;
        for (int i = 0; i <= k; i++) {
            if (i <= len1 && k - i <= len2) {
                int[] ints1 = buildMaxArr(nums1, i);
                int[] ints2 = buildMaxArr(nums2, k - i);

                int[] merge = merge(ints1, ints2, k);
                if (gt(merge, 0, res, 0)) {
                    res = merge;
                }
            }
        }
        return res;
    }

    private static int[] merge(int[] ints1, int[] ints2, int k) {
        int[] res = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++)
            res[r] = gt(ints1, i, ints2, j) ? ints1[i++] : ints2[j++];
        return res;
    }

    private static boolean gt(int[] ints1, int i, int[] ints2, int j) {
        while (i < ints1.length && j < ints2.length && ints1[i] == ints2[j]) {
            i++;
            j++;
        }
        return j == ints2.length || (i < ints1.length && ints1[i] > ints2[j]);
    }

    /**
     * 要取expect个字符单调增
     */
    private static int[] buildMaxArr(int[] nums, int expect) {
        if (expect == 0) {
            return new int[0];
        }
        int[] res = new int[expect];
        int pop = nums.length - expect; //不需要的字符的数量
        int index = -1;
        for (int num : nums) {
            while (pop > 0 && index >= 0 && res[index] < num) {
                pop--;
                index--;
            }
            if (index == expect - 1) {
                pop--;
                continue;
            }
            res[++index] = num;
        }
        return res;
    }

    /////////////////////////////////

    /**
     * nums1 : 从l1~r1 范围内, 从(r1-(K-(r2-l2)))向前找到最大值
     * nums2 : 从l2~r2 范围内,  从(r2-(K-(r1-l1)))向前找到最大值
     * //找到两者之间的最大值.作为下一次的数据 ---------
     * todo 这种方案 失败!!!!  [{3, 9}{8, 9}] 导致可能得到的结果为939 而不是989
     * //todo 这个方法应该还要考验max值相等的时候的向前判断,,,,,
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int l1 = 0;
        int r1 = nums1.length;
        int l2 = 0;
        int r2 = nums2.length;
        int mk = k;
        int max1 = -1;
        int max2 = -1;
        for (int i = 0; i < k; i++) {
            //若是能想办法每次计算好就不错了
            max1 = max1 == -1 ? maxNumber(nums1, l1, r1 - (Math.max(0, mk - (r2 - l2) - 1))) : max1;
            max2 = max2 == -1 ? maxNumber(nums2, l2, r2 - (Math.max(0, mk - (r1 - l1) - 1))) : max2;
            if (max2 == -1 || (max1 != -1 && nums1[max1] > nums2[max2])) {
                l1 = max1 + 1;
                //todo 有些问题
                res[i] = nums1[max1];
                max1 = -1;
            } else if (max1 == -1 || nums1[max1] < nums2[max2]) {
                l2 = max2 + 1;
                res[i] = nums2[max2];
                max2 = -1;
            } else {
                //若是前面较大的值. 则说明应该选择当前范围;较小的,
            }
            mk--;
        }
        return res;
    }

    private static int maxNumber(int[] nums, int l, int r) {
        int max = -1;
        int res = -1;
        for (int i = l; i < r; i++) {
            if (max < nums[i]) {
                res = i;
                max = nums[i];
            }
        }
        return res;
    }
}
