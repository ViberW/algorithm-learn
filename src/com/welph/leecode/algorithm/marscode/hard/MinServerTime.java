package com.welph.leecode.algorithm.marscode.hard;

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
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int delta = t[o1] - t[o2];
                return delta == 0 ? o1 - o2 : delta; //若没有请求且之后请求的两个时间一样, 则序列号小的先执行
            }
        });
        for (int i = 0; i < t.length; i++) {
            queue.add(i);
        }
        int current = 0;
        int ret = 0;
        while (!queue.isEmpty() || !map.isEmpty()) {
            if (!queue.isEmpty()) {
                int p = queue.peek();
                if (map.isEmpty() && t[p] > current) {
                    current = t[p];
                    //此时直接使用目标值
                }
                //填充本次判断的能够到达点
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    p = queue.peek();
                    if (t[p] > current) {
                        break;
                    }
                    queue.poll();
                    int ai = a[p];
                    map.put(ai, map.getOrDefault(ai, 0) + 1);
                }
            }
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
//        System.out.println(solution(5, 2, 4, new int[]{4, 8, 9, 8, 10}, new int[]{12, 14, 4, 17, 8}) == 14);
//        System.out.println(solution(3, 1, 6, new int[]{3, 10, 6}, new int[]{1, 5, 2}) == 10);
//        System.out.println(solution(4, 3, 7, new int[]{5, 8, 12, 4}, new int[]{2, 6, 9, 11}) == 17);
        System.out.println(solution(10, 8, 13, new int[]{14, 6, 8, 12, 15, 7, 4, 10, 14, 15}, new int[]{15, 3, 5, 3, 7, 2, 15, 12, 8, 2}) == 20);
    }
}
