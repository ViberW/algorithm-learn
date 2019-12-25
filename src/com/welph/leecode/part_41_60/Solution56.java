package com.welph.leecode.part_41_60;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间
 */
public class Solution56 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] dsfa = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        int[][] dfa = {{1, 4}, {1, 5}};
        int[][] merge = merge(dfa);
        System.out.println("---------------");
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        for (int[] arr : intervals) {
            int i = binaryIndex(list, arr[0]);
            if (i >= 0) {
                int[] ints = list.get(i);
                if (ints[1] < arr[1]) {
                    ints[1] = arr[1];
                }
            } else {
                i = -(i + 1);
                if (i >= list.size()) {
                    list.add(arr);
                } else {
                    list.add(i, arr);
                }
            }
        }
        int size = list.size();
        int[][] result = new int[size][2];
        int k = 0;
        for (int i = 0; i < size; i++) {
            int[] ints = list.get(i);
            while (++i < size) {
                if (ints[1] >= list.get(i)[0] && ints[0] <= list.get(i)[1]) {
                    ints[1] = Math.max(ints[1], list.get(i)[1]);
                } else {
                    break;
                }
            }
            result[k] = ints;
            k++;
            i--;
        }
        int[][] ints = new int[k][2];
        System.arraycopy(result, 0, ints, 0, k);
        return ints;
    }

    public static int binaryIndex(List<int[]> list, int value) {
        int r = 0;
        int l = list.size() - 1;
        int mid;
        while (r <= l) {
            mid = (r + l) / 2;
            if (value > list.get(mid)[0]) {
                r = mid + 1;
            } else if (value < list.get(mid)[0]) {
                l = mid - 1;
            } else {
                return mid;
            }
        }
        return -(r + 1);
    }
}
