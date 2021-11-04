package com.welph.leecode.part_1_500.part_421_440;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * .   可以认为区间的终点总是大于它的起点。
 * .   区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * <p>
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * <p>
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * <p>
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class Solution435 {

    public static void main(String[] args) {
        /*System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 3}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));*/
        System.out.println(eraseOverlapIntervals(new int[][]{
                {-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49},
                {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}
        }));
        System.out.println(eraseOverlapIntervals1(new int[][]{
                {-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49},
                {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}
        }));
    }

    /**
     * 排序- 相同起点的选择最小值的结尾
     * ---------
     * 灰常的慢, 这个方案不太好
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? Integer.compare(o1[1], o2[1])
                        : Integer.compare(o1[0], o2[0]);
            }
        });
        int reduce = 0;
        int len = intervals.length;
        List<int[]> ints = new ArrayList<>();
        if (len > 0) {
            int end = intervals[0][1];
            for (int i = 1; i < len; i++) {
                if (intervals[i][0] == intervals[i - 1][0]) {
                    reduce++;
                    ints.add(intervals[i]);
                } else {
                    if (intervals[i][1] < end) {
                        end = intervals[i][1];
                        reduce++;
                    } else if (intervals[i][0] >= end) {
                        end = intervals[i][1];
                    } else {
                        reduce++;
                        ints.add(intervals[i]);
                    }
                }
            }
        }
        return reduce;
    }

    /**
     * 看了题解, 根据右边界排序判断, 获取到不重复的区间
     * ----------------------
     * 不同于我上面的方法, 仅仅需要保证右边界的贪心处理
     * ----------------------
     * result(需要移除的区间数) = 总区间数 - 不重复的区间数
     */
    public static int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int count = 1; // 记录非交叉区间的个数
        int end = intervals[0][1]; // 记录区间分割点
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
