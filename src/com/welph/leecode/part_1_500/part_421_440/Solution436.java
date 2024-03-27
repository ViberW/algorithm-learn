package com.welph.leecode.part_1_500.part_421_440;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * <p>
 * 示例 2：
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1, 0, 1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * <p>
 * 示例 3：
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1, 2, -1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 2 * 104
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * 每个间隔的起点都 不相同
 */
public class Solution436 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRightInterval(new int[][] {
                { 1, 4 }, { 2, 3 }, { 3, 4 }
        })));
        System.out.println(Arrays.toString(findRightInterval(new int[][] {
                { 3, 4 }, { 2, 3 }, { 1, 2 }
        })));
    }

    /**
     * todo 官方有更好的方法, 通过TreeMap保存数据, 相对于二分查找, 更加快速
     */

    /**
     * 二分查找法,根据end_i找到最小的start_i
     * -----
     * 因为start是不相同.
     */
    public static int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] ret = new int[length];
        // 根据start_i进行排序
        Entity[] entities = new Entity[length];
        for (int i = 0; i < length; i++) {
            entities[i] = new Entity(intervals[i][0], i);
        }
        Arrays.sort(entities, Comparator.comparingInt(value -> value.val));
        for (int i = 0; i < length; i++) {
            ret[i] = binarySearch(entities, intervals[i][1], length);
        }
        return ret;
    }

    private static int binarySearch(Entity[] entities, int val, int length) {
        int l = 0;
        int r = length - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            int v = entities[mid].val;
            if (val > v) {
                l++;
            } else if (val == v) {
                return entities[mid].index;
            } else {
                r--;
            }
        }
        return l == length ? -1 : entities[l].index;
    }

    static class Entity {
        int val;
        int index;

        public Entity(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    // 对上面的简化, 但仍不如双指针, 少了点空间
    public int[] findRightInterval2(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 二分查找
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (startIntervals[mid][0] >= intervals[i][1]) {
                    target = startIntervals[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
    }

    /* 官方题解 */
    // 双指针法
    public int[] findRightInterval1(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        int[][] endIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
            endIntervals[i][0] = intervals[i][1];
            endIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(endIntervals, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            // 双指针 依照于end 不断去循环寻找start的值
            while (j < n && endIntervals[i][0] > startIntervals[j][0]) {
                j++;
            }
            if (j < n) {
                ans[endIntervals[i][1]] = startIntervals[j][1];
            } else {
                ans[endIntervals[i][1]] = -1;
            }
        }
        return ans;
    }

}
