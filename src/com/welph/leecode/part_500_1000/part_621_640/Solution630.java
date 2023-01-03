package com.welph.leecode.part_500_1000.part_621_640;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
 * 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
 * 并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 * <p>
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 * <p>
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 * <p>
 * 提示:
 * 1 <= courses.length <= 10^4
 * 1 <= durationi, lastDayi <= 10^4
 */
public class Solution630 {

    public static void main(String[] args) {
//        System.out.println(scheduleCourse(new int[][]{
//                {100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}
//        }));

//        System.out.println(scheduleCourse(new int[][]{
//                {5, 15}, {3, 19}, {6, 7}, {2, 10}, {5, 16}, {8, 14}, {10, 11}, {2, 19}
//        }));
        System.out.println(scheduleCourse(new int[][]{
                {7, 17}, {3, 12}, {10, 20}, {9, 10}, {5, 20}, {10, 19}
        }));
        //[6, 7]     7
        //[2, 10]    9
        //[5, 15]    14
        //[2, 19]    16
        //[3, 19]    19
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution207}
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution210}
     * todo 好题目
     */
    public static int scheduleCourse(int[][] courses) {
        //构建优先级队列. 若是不满足的可以剔除
        Arrays.sort(courses, Comparator.comparingInt(value -> value[1]));
        //优先是处理时间长的, 需要给剔除
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int total = 0;
        for (int[] course : courses) {
            if (course[0] > course[1]) {
                continue;
            }
            if (total + course[0] <= course[1]) {
                total += course[0];
                queue.add(course);
            } else if (!queue.isEmpty() && queue.peek()[0] > course[0]) {
                total = total + course[0] - queue.poll()[0];
                queue.add(course);
            }
        }
        return queue.size();
    }

    public static int scheduleCourse1(int[][] courses) {
        //排序将关闭时间最晚的放在最后面
        Arrays.sort(courses, Comparator.comparing(a -> a[1]));

        //用于储存当前最优课程队列 首位为当前课程最大用时
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        //当前总计所需时长
        int totalDuration = 0;

        for (int i = 0; i < courses.length; i++) {
            //本次学习需要的时间
            int duration = courses[i][0];
            //本次学习最晚的完成时间
            int lastDay = courses[i][1];
            //学习时间比最晚时间还长 直接忽略
            if (duration > lastDay) {
                continue;
            }
            //当前学习时长加上本次学习时长没超过本次的最晚时间 符合要求加入课程队列
            if (totalDuration + duration <= lastDay) {
                //加上最长时长
                totalDuration += duration;
                //将最大时长放入队列
                priorityQueue.add(courses[i]);
            } else {
                //当前学习时长加上本次学习超过本次的最晚时间
                //判断当前课程队列里面最长的学习时间是否比本次更长
                if (priorityQueue.size() > 0 && (priorityQueue.peek()[0] > duration)) {
                    //如果最长的学习时间比本次更长 将替换课程 为后续的课程提供更长的学习时间
                    totalDuration = totalDuration - priorityQueue.poll()[0] + duration;
                    priorityQueue.add(courses[i]);
                }
            }

        }
        for (int[] ints : priorityQueue) {
            System.out.println(Arrays.toString(ints));
        }
        return priorityQueue.size();
    }
}
