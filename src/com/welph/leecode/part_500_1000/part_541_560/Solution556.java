package com.welph.leecode.part_500_1000.part_541_560;

import com.welph.leecode.algorithm.binaryquery.BinaryQuery;

/**
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，
 * 并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * <p>
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 * <p>
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class Solution556 {

    public static void main(String[] args) {
        System.out.println(nextGreaterElement(12));
        System.out.println(nextGreaterElement(230241));//230412   //230142
        System.out.println(nextGreaterElement(12443322));//13222344   //12223344
    }

    /**
     * {@link com.welph.leecode.algorithm.greedy.MoveK}
     * {@link com.welph.leecode.part_1_500.part_481_500.Solution496}
     * 5241321 找到递增的下一个最大值 比1的2 交换, 并 将后面的进行升序排列
     */
    public static int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int i = chars.length - 1;
        for (; i > 0; i--) {
            if (chars[i] > chars[i - 1]) {
                break;
            }
        }
        if (i == 0) {
            return -1;
        }
        //找到比i-1临近最大的一个值
        int t = firstGreate(chars, i, chars.length - 1, chars[i - 1]);
        swap(chars, i - 1, t);
        //双指针排序字段
        int l = i;
        int r = chars.length - 1;
        while (l < r) {
            swap(chars, l, r);
            l++;
            r--;
        }
        Long aLong = Long.valueOf(String.valueOf(chars));
        return aLong > Integer.MAX_VALUE ? -1 : aLong.intValue();
    }

    private static void swap(char[] chars, int l, int r) {
        char tmp = chars[l];
        chars[l] = chars[r];
        chars[r] = tmp;
    }

    private static int firstGreate(char[] chars, int l, int r, char target) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (target < chars[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return chars[l] <= target ? l - 1 : l;
    }
}
