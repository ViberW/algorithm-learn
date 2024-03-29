package com.welph.leecode.part_1_500.part_141_160;

import java.util.*;

/**
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 */
public class Solution149 {

    public static void main(String[] args) {
        /*
         * int[][] points = {{1, 1}, {2, 2}, {3, 3}};
         * int[][] points1 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
         * System.out.println(maxPoints(points));
         * System.out.println(maxPoints(points1));
         */
        /*
         * int[][] points2 = {{1, 1}, {2, 1}, {2, 2}, {1, 4}, {3, 3}};
         * System.out.println(maxPoints(points2));
         */
        int[][] points3 = { { 560, 248 }, { 0, 16 }, { 30, 250 }, { 950, 187 }, { 630, 277 }, { 950, 187 },
                { -212, -268 }, { -287, -222 }, { 53, 37 }, { -280, -100 }, { -1, -14 }, { -5, 4 }, { -35, -387 },
                { -95, 11 }, { -70, -13 }, { -700, -274 }, { -95, 11 }, { -2, -33 }, { 3, 62 }, { -4, -47 },
                { 106, 98 }, { -7, -65 }, { -8, -71 }, { -8, -147 }, { 5, 5 }, { -5, -90 }, { -420, -158 },
                { -420, -158 }, { -350, -129 }, { -475, -53 }, { -4, -47 }, { -380, -37 }, { 0, -24 }, { 35, 299 },
                { -8, -71 }, { -2, -6 }, { 8, 25 }, { 6, 13 }, { -106, -146 }, { 53, 37 }, { -7, -128 }, { -5, -1 },
                { -318, -390 }, { -15, -191 }, { -665, -85 }, { 318, 342 }, { 7, 138 }, { -570, -69 }, { -9, -4 },
                { 0, -9 }, { 1, -7 }, { -51, 23 }, { 4, 1 }, { -7, 5 }, { -280, -100 }, { 700, 306 }, { 0, -23 },
                { -7, -4 }, { -246, -184 }, { 350, 161 }, { -424, -512 }, { 35, 299 }, { 0, -24 }, { -140, -42 },
                { -760, -101 }, { -9, -9 }, { 140, 74 }, { -285, -21 }, { -350, -129 }, { -6, 9 }, { -630, -245 },
                { 700, 306 }, { 1, -17 }, { 0, 16 }, { -70, -13 }, { 1, 24 }, { -328, -260 }, { -34, 26 }, { 7, -5 },
                { -371, -451 }, { -570, -69 }, { 0, 27 }, { -7, -65 }, { -9, -166 }, { -475, -53 }, { -68, 20 },
                { 210, 103 }, { 700, 306 }, { 7, -6 }, { -3, -52 }, { -106, -146 }, { 560, 248 }, { 10, 6 }, { 6, 119 },
                { 0, 2 }, { -41, 6 }, { 7, 19 }, { 30, 250 } };
        System.out.println(maxPoints(points3));
    }

    // 解决了精度问题呢, 题解没有计算精度 而是保存了关系
    public static int maxPoints1(int[][] points) {
        // todo
        return 0;
    }

    // 每个点进来 都需要保证和其他斜率, 最好保存相同斜率 及其x轴的点为多少.
    public static int maxPoints(int[][] points) {
        int max = 0;
        int length = points.length;
        if (length == 0) {
            return max;
        }
        max = 1;
        Map<Double, Map<Double, Set<Integer>>> map = new HashMap<>();
        Double[] xy = new Double[2];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                int[] point = points[i];
                int[] point1 = points[j];
                calc(xy, point1[0], point1[1], point[0], point[1]);
                Set<Integer> integers = map.computeIfAbsent(xy[0], aDouble -> new HashMap<>())
                        .computeIfAbsent(xy[1], aDouble -> new HashSet<>());
                integers.add(i);
                integers.add(j);
                max = Math.max(max, integers.size());
            }
        }
        return max;
    }

    // 两条不想交的线确定唯一的值
    private static void calc(Double[] xy, int x, int y, int x1, int y1) {
        // 这里假定使用 x == 1的线
        if (x == x1) {
            xy[0] = null; // 当前的x值
            xy[1] = (double) x;
        } else {
            double k = (y1 - y) * 1.0 / (x1 - x);
            xy[0] = y1 - k * x1;
            xy[1] = xy[0] + k;
        }
    }

    /* 官方题解, 找出出现频次最多的斜率 */
    public int maxPoints2(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {//因为一条直线多少个点, 则表示以i在直线上
            //后面的不可能超过ret  或 不再有超过n/2的数据(否则一定是同一直线上)
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;//分别除以最小公倍数
                    y /= gcdXY;
                }
                int key = y + x * 20001; // 表示斜率 只不多为了防止因为浮点问题, 使用整型来表示
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    //最小公倍数 {@link ExpandEuclidean_8}
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

}
