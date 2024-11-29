package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 小M在春游时打算携带尽可能多的巧克力板。她拥有n块巧克力板，每块巧克力的边长为ai ，重量为ai*ai。
 * 小M有多个不同大小的背包，每个背包都有一定的最大承重限制。她希望你帮助她计算在每个背包的最大承重范围内，最多可以带走多少块巧克力板。
 *
 * 例如：小M有5块巧克力板，边长分别为1, 2, 2, 4, 5，有5个不同的背包，其最大承重分别为1, 3, 7, 9, 15。对于每个背包，她能装入的巧克力块数分别是1, 1, 2, 3, 3。
 */
public class ChocolateMorePacket {

    //类似 {@link HouseRacing} 田忌赛马
    public static int[] solution(int n, int m, int[] a, int[] queries) {
        //这么简单么...... 前缀和了?
        Arrays.sort(a);
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> queries[o1] - queries[o2]);
        for (int i = 0; i < m; i++) {
            queue.add(i);
        }
        int[] result = new int[m];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i] * a[i];
            //查看
            while (!queue.isEmpty() && queries[queue.peek()] < sum) {
                result[queue.poll()] = i;
            }
        }
        while (!queue.isEmpty()) {
            result[queue.poll()] = n;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(solution(5, 5, new int[]{1, 2, 2, 4, 5}, new int[]{1, 3, 7, 9, 15}),
                new int[]{1, 1, 2, 3, 3}));
        System.out.println(Arrays.equals(solution(4, 3, new int[]{3, 1, 2, 5}, new int[]{5, 10, 20}),
                new int[]{2, 2, 3}));
        System.out.println(Arrays.equals(solution(6, 4, new int[]{1, 3, 2, 2, 4, 6}, new int[]{8, 12, 18, 25}),
                new int[]{2, 3, 4, 4}));
    }

}
