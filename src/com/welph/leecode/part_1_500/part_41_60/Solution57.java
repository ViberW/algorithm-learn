package com.welph.leecode.part_1_500.part_41_60;

import java.util.Arrays;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
 */
public class Solution57 {

    public static void main(String[] args) {
       /* int[][] arr = {{1, 2}, {4, 5}, {6, 9}};
        int[] newInterval = {3, 5};
        int[][] insert = insert(arr, newInterval);
        for (int[] a : insert) {
            System.out.println(Arrays.toString(a));
        }*/

        int[][] arr1 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval1 = {4, 8};
        int[][] insert1 = insert(arr1, newInterval1);
        for (int[] a : insert1) {
            System.out.println(Arrays.toString(a));
        }

        int[][] arr2 = {{1, 5}};
        int[] newInterval2 = {0, 3};
        int[][] insert2 = insert(arr2, newInterval2);
        for (int[] a : insert2) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        //一次二分法找到最开始的位置点，再从断电找对应位置，最终合并
        int lv = newInterval[1];
        int rv = newInterval[0];
        int r = 0;
        int l = intervals.length - 1;
        int mid;
        while (r <= l) {
            mid = (r + l) / 2;
            if (intervals[mid][0] > lv) {
                l = mid - 1;
            } else if (intervals[mid][1] < rv) {
                r = mid + 1;
            } else {
                int i = binaryIndex(intervals, rv, r, mid, 1);
                int i1 = binaryIndex(intervals, lv, mid, l, 0);
                int[][] result = new int[intervals.length - (i1 - i)][2];
                System.arraycopy(intervals, 0, result, 0, i);
                newInterval[0] = Math.min(rv, intervals[i][0]);
                newInterval[1] = Math.max(lv, intervals[i1][1]);
                result[i] = newInterval;
                System.arraycopy(intervals, i1 + 1, result, i + 1, intervals.length - i1 - 1);
                return result;
            }
        }
        int[][] result = new int[intervals.length + 1][2];
        System.arraycopy(intervals, 0, result, 0, r);
        result[r] = newInterval;
        System.arraycopy(intervals, r, result, r + 1, intervals.length - r);
        return result;
    }

    private static int binaryIndex(int[][] intervals, int value, int r, int l, int add) {
        int mid;
        while (r <= l) {
            mid = (r + l) / 2;
            if (intervals[mid][0] > value) {
                l = mid - 1;
            } else if (intervals[mid][1] < value) {
                r = mid + 1;
            } else {
                return mid;
            }
        }
        return ((l + r + add) / 2);
    }
}
