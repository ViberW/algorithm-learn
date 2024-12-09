package com.welph.leecode.algorithm.marscode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 小F正在探索一个有n个点的地图,其中每个点都有对应的二维坐标(22),y)。
 * 起点是第s个点,终点是第t个点,原本所有点之间都有一条线段表示可以通行,并且长度为欧几里得距离。
 *
 * 但是由于某些意外,起点s和终点t之间的直接通行路径被删除了。
 *
 * 小F希望你帮忙计算从s到t的最短路径,允许经过其他点,但不能直接通过删除的那条线。
 * 需要注意的是,点i,j之间的欧几里得距离计算公式为: sqrt((x_i - x_j)^2 + (y_i - y_j)^2)
 * 你需要输出从起点s到终点t的最短距离,结果需要四舍五入到小数点后两位。
 */
public class ShortRouteAroundPoints {

    //类似:{@link DijkstraDemo}
    public static String solution(int n, int s, int t, int[] x, int[] y) {
        //所有点之间表示都有线段 ,仅仅s和t之间的断开了. 可以使用优先队列
        double[] distances = new double[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(b -> distances[b]));
        Arrays.fill(distances, Double.MAX_VALUE);

        double[][] rangeDistances = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                rangeDistances[i][j] = rangeDistances[j][i] = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
            }
        }
        s--;
        t--;
        distances[s] = 0d;
        queue.add(s);
        double dis;
        //由于s可能等于t
        double distSource = s == t ? Double.MAX_VALUE : 0;
        int duplicate = s == t ? 1 : 0;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            if (curr == t && duplicate-- == 0) {
                break;
            }
            for (int k = 0; k < n; k++) {
                if (curr == s && k == t) {
                    continue;
                }
                if (k != curr) {
                    if ((dis = distances[curr] + rangeDistances[curr][k]) < (k == s && s == t ? distSource : distances[k])) {
                        if (distances[k] != Double.MAX_VALUE) { //已经存在过
                            queue.remove(k);
                        }
                        distances[k] = dis;
                        queue.add(k);
                    }
                }
            }
        }
        return BigDecimal.valueOf(distances[t]).setScale(2, RoundingMode.HALF_UP).toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 1, 5, new int[]{17253, 25501, 28676, 30711, 18651},
                new int[]{15901, 15698, 32041, 11015, 9733}).equals("17333.65"));
        System.out.println(solution(4, 2, 4, new int[]{5000, 12000, 8000, 14000},
                new int[]{3000, 9000, 1000, 4000}).equals("15652.48"));
        System.out.println(solution(6, 3, 6, new int[]{20000, 22000, 24000, 26000, 28000, 30000},
                new int[]{15000, 13000, 11000, 17000, 19000, 21000}).equals("11772.70"));
        System.out.println(solution(7, 4, 5, new int[]{9, 16, 13, 1, 3, 11, 13},
                new int[]{12, 15, 11, 2, 11, 10, 2}));//"18.89"
        System.out.println(solution(10, 2, 2, new int[]{11, 3, 5, 6, 2, 4, 15, 14, 16, 8},
                new int[]{5, 8, 7, 14, 8, 10, 5, 4, 2, 9}));//"2.00"
    }
}
