package com.welph.leecode.part_1_500.part_481_500;

/**
 * 给定一个数组nums，如果i < j且nums[i] > 2*nums[j]我们就将(i, j)称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * <p>
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内
 */
public class Solution493 {

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{1, 3, 2, 3, 1}));
        System.out.println(reversePairs(new int[]{2, 4, 3, 5, 1}));
        System.out.println(reversePairs(new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_301_320.Solution315}
     * {@link com.welph.leecode.part_1_500.part_321_340.Solution327}
     * 时间13.50% 空间95.85%
     */
    public static int reversePairs(int[] nums) {
        //先参照Solution315的解决方案处理
        int[][] rep = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            rep[i][0] = i;
            rep[i][1] = nums[i];
        }
        return reversePairs(rep, 0, nums.length);
    }

    private static int reversePairs(int[][] rep, int l, int r) {
        if (r - l < 2) {
            return 0;
        }
        int mid = (l + r) / 2;
        int ret = 0;
        ret += reversePairs(rep, l, mid);
        ret += reversePairs(rep, mid, r);
        ret += merge(rep, l, mid, r);
        return ret;
    }

    private static int merge(int[][] rep, int l, int mid, int r) {
        int ret = 0;
        int[][] result = new int[r - l][];
        int i = 0;
        int ml = l;
        int mr = mid;
        while (ml < mid && mr < r) {
            if (rep[ml][1] > 2L * rep[mr][1]) { //注意这里面的数据越界问题
                ret += (r - mr);
                ml++;
            } else {
                mr++;
            }
        }
        ml = l;
        mr = mid;
        while (ml < mid && mr < r) {
            if (rep[ml][1] > rep[mr][1]) {
                result[i++] = rep[ml];
                ml++;
            } else {
                result[i++] = rep[mr];
                mr++;
            }
        }
        for (; ml < mid; ml++) {
            result[i++] = rep[ml];
        }
        for (; mr < r; mr++) {
            result[i++] = rep[mr];
        }
        System.arraycopy(result, 0, rep, l, result.length);
        return ret;
    }
}
