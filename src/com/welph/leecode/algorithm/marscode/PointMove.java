package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 小M有n个点,每个点的坐标为(2;y)。她可以从一个点出发,平行于坐标轴移动,直到到达另一个点。具体来说,她可以从
 * (1,y1)直接到达(2,y1)或者(21,y2),但无法直接到达(12,y2)。
 *
 * 为了使得任意两个点之间可以互相到达,小M可以选择增加若干个新的点。
 *
 * 你的任务是计算最少需要增加多少个点,才能保证任意两个点之间可以通过平行于坐标轴的路径互相到达
 */
public class PointMove {
    public static int solution(int n, int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int i = o1[0] - o2[0];
                return i == 0 ? o1[1] - o2[1] : i;
            }
        });
        Set<Integer> ySet = new HashSet<>();
        int preX = points[0][0];
        int ret = 0;
        ySet.add(points[0][1]);
        for (int i = 1; i < n; i++) { //扫描线
            if (preX != points[i][0]) {
                ret++;
                int currentX = points[i][0];
                for (; i < n; i++) {
                    if (currentX != points[i][0]) {
                        i--;
                        break;
                    }
                    if (ySet.contains(points[i][1])) {
                        ret--;
                        break;
                    }
                    ySet.add(points[i][1]);
                    while (i + 1 < n && points[i][0] == points[i + 1][0] && points[i][1] == points[i + 1][1]) {
                        i++;
                    }
                }
                preX = currentX;
            } else {
                ySet.add(points[i][1]);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new int[][]{{1, 1}, {2, 2}}) == 1);
        System.out.println(solution(3, new int[][]{{1, 2}, {2, 3}, {4, 1}}) == 2);
        System.out.println(solution(4, new int[][]{{3, 4}, {5, 6}, {3, 6}, {5, 4}}) == 0);
    }
}
