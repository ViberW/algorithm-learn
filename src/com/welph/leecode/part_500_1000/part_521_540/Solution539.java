package com.welph.leecode.part_500_1000.part_521_540;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 * <p>
 * 提示：
 * 2 <= timePoints.length <= 2 * 10^4
 * timePoints[i] 格式为 "HH:MM"
 */
public class Solution539 {

    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00")));
        System.out.println(findMinDifference(Arrays.asList("00:00", "23:59", "00:00")));
        System.out.println(findMinDifference(Arrays.asList("00:00", "04:00", "22:00")));
    }

    /**
     * 快排结合数据比较
     */
    public static int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[timePoints.size()];
        String s;
        for (int i = 0; i < minutes.length; i++) {
            s = timePoints.get(i);
            minutes[i] = ((s.charAt(0) - '0') * 10
                    + (s.charAt(1) - '0')) * 60
                    + (s.charAt(3) - '0') * 10
                    + (s.charAt(4) - '0');//最大值24小时=1440分钟
        }
        int i = quickSort(minutes, 0, minutes.length - 1);
        return Math.min(i, difference(minutes, 0, minutes.length - 1));
    }

    public static int quickSort(int[] minutes, int l, int r) {
        if (l < r) {
            int pivot = partition(minutes, l, r);
            int i = quickSort(minutes, l, pivot - 1);
            int i2 = quickSort(minutes, pivot + 1, r);
            //比较pivot间的最小值
            int min = Math.min(i, i2);
            if (l == pivot) {
                return Math.min(difference(minutes, pivot, pivot + 1), min);
            } else if (r == pivot) {
                return Math.min(difference(minutes, pivot - 1, pivot), min);
            }
            return Math.min(min, Math.min(difference(minutes, pivot, pivot + 1), difference(minutes, pivot - 1, pivot)));
        }
        return Integer.MAX_VALUE;
    }

    private static int difference(int[] minutes, int l, int r) {
        int delta = minutes[r] - minutes[l];
        return Math.min(delta, 1440 - delta);
    }

    private static int partition(int[] minutes, int l, int r) {
        int target = l;
        int pivot = minutes[r];
        for (int i = l; i < r; i++) {
            if (minutes[i] < pivot) {
                swap(minutes, i, target);
                target++;
            }
        }
        swap(minutes, r, target);
        return target;
    }

    private static void swap(int[] minutes, int l, int r) {
        int tmp = minutes[l];
        minutes[l] = minutes[r];
        minutes[r] = tmp;
    }
}
