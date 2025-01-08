package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小T 和小M 负责调度一组作业，这些作业的执行时长已被记录在一个列表中。
 * 现在他们有 k 名员工需要参与完成这些作业。
 * 为了提升工作效率，小T 和小M 需要合理分配作业，以确保每个员工的最大工作时长尽可能小。
 *
 * 每项作业只能分配给一名员工，并且每位员工至少要负责一项作业。
 * 每位员工的总工作时长是其所分配的所有作业时长之和。
 * 小T 和小M 希望你能帮助他们找到最佳的作业分配方案，以最小化所有员工中的最大工作时长。
 *
 * 请计算在最佳分配方案下的最小最大工作时长。
 */
public class WorkHourLowest {

    //{@link Solution644}
    public static int solution(int[] jobs, int k) {
        int minTime = 0;
        int maxTime = 0;
        for (int i = 0; i < jobs.length; i++) {
            minTime = Math.max(minTime, jobs[i]);
            maxTime += jobs[i];
        }
        //二分法
        int mid;
        int[] times = new int[k];
        while (minTime < maxTime) {
            mid = (maxTime + minTime) / 2;
            if (check(jobs, times, k, mid, 0)) {
                maxTime = mid;
            } else {
                minTime = mid + 1;
            }
            Arrays.fill(times, 0);
        }
        return minTime;
    }

    private static boolean check(int[] jobs, int[] times, int k, int time, int index) {
        //问题回到: jobs是否能够满足每个员工不超过time  回溯
        if (index == jobs.length) {
            return true;
        }
        int job = jobs[index];
        for (int i = 0; i < k; i++) {
            if (times[i] + job > time) {
                continue;
            }
            times[i] += job;
            if (check(jobs, times, k, time, index + 1)) {
                return true;
            }
            times[i] -= job;
            if (times[i] == 0) { //后面和当前的结果是一样的.
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 3, 1}, 3) == 5);
        System.out.println(solution(new int[]{4, 5, 6, 8, 9}, 2) == 17);
        System.out.println(solution(new int[]{12, 8, 6, 3}, 3) == 12);
    }
}
