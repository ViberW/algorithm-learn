package com.welph.leecode.algorithm.marscode;

/**
 *
 * 小C在学习操作系统时，遇到了最短服务时间优先（SSTF）磁盘调度算法。这个算法的关键在于每次磁头服务完一个请求后，
 * 会选择当前距离最近的未处理请求。如果有多个距离相等的请求，优先选择磁道号较小的那个。磁头会在没有请求时停止，直到新的请求到达。
 * <p>
 * 给定n个磁盘请求，每个请求有一个磁道号和到达时间。磁头每次移动1个磁道需要 m个时间单位，
 * 初始时磁头位于磁道k上。你的任务是计算磁头在SSTF调度算法下，总共移动的磁道数。
 */
public class MinServerTime {

    public static int solution(int n, int m, int k, int[] a, int[] t) {
        // write code here
        return 0;//小C的最短服务时间优先磁盘调度
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 2, 4, new int[]{4, 8, 9, 8, 10}, new int[]{12, 14, 4, 17, 8}) == 14);
        System.out.println(solution(3, 1, 6, new int[]{3, 10, 6}, new int[]{1, 5, 2}) == 10);
        System.out.println(solution(4, 3, 7, new int[]{5, 8, 12, 4}, new int[]{2, 6, 9, 11}) == 17);
    }
}
