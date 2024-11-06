package com.welph.leecode.algorithm.marscode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 小C在学习操作系统时，遇到了最短服务时间优先（SSTF）磁盘调度算法。这个算法的关键在于每次磁头服务完一个请求后，
 * 会选择当前距离最近的未处理请求。如果有多个距离相等的请求，优先选择磁道号较小的那个。磁头会在没有请求时停止，直到新的请求到达。
 * <p>
 * 给定n个磁盘请求，每个请求有一个磁道号和到达时间。磁头每次移动1个磁道需要 m个时间单位，
 * 初始时磁头位于磁道k上。你的任务是计算磁头在SSTF调度算法下，总共移动的磁道数。
 */
public class MinServerTime {

    public static int solution(int n, int m, int k, int[] a, int[] t) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        for (int i = 0; i < t.length; i++) {
            queue.add(new int[]{t[i], i});
        }
        int current = 0;
        int ret = 0;
        while (!queue.isEmpty() || !map.isEmpty()) {
            if (!queue.isEmpty()) {
                int[] p = queue.peek();
                if (map.isEmpty() && p[0] > current) {
                    current = p[0];
                }
                //填充本次判断的能够到达点
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    p = queue.peek();
                    if (p[0] > current) {
                        break;
                    }
                    queue.poll();
                    int ai = a[p[1]];
                    map.put(ai, map.getOrDefault(ai, 0) + 1);
                }
            }
            //计算当前次应该到哪里去
            Integer ceiling = map.ceilingKey(k);
            Integer floor = map.floorKey(k);
            if (ceiling == null || (floor != null && k - floor <= ceiling - k)) {
                ret += k - floor;
                current += (k - floor) * m;
                k = floor;
            } else {
                ret += ceiling - k;
                current += (ceiling - k) * m;
                k = ceiling;
            }
            Integer i = map.get(k);
            if (i == 1) {
                map.remove(k);
            } else {
                map.put(k, i - 1);
            }
        }
        return ret;//小C的最短服务时间优先磁盘调度
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 2, 4, new int[]{4, 8, 9, 8, 10}, new int[]{12, 14, 4, 17, 8}) == 14);
        System.out.println(solution(3, 1, 6, new int[]{3, 10, 6}, new int[]{1, 5, 2}) == 10);
        System.out.println(solution(4, 3, 7, new int[]{5, 8, 12, 4}, new int[]{2, 6, 9, 11}) == 17);
    }
}
