package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小S在一个n×m的网格迷宫中，初始位置在左上角 (1,1)，目标是到达右下角 (n,m)。
 * 每个格子可以是黑色（表示为1）或者白色（表示为0）。他希望在移动过程中经过的黑色格子尽可能少。
 * 移动时可以向上、下、左、右四个方向移动，但不能走出迷宫的边界。
 *
 * 请你帮小S计算从起点到终点所需经过的最少黑色格子的数量。
 */
public class MinGridWayForS {

    public static int solution(int n, int m, int[][] grid) {
        //bfs ==> Dijkstra寻路算法 {link DijkstraDemo}
        int[][] blacks = new int[n][m];
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int black1 = blacks[o1 / m][o1 % m];
                int black2 = blacks[o2 / m][o2 % m];
                return black1 - black2 == 0 ? o2.compareTo(o1) : black1 - black2;
            }
        });
        for (int i = 0; i < n; i++) {
            Arrays.fill(blacks[i], Integer.MAX_VALUE);
        }
        blacks[0][0] = grid[0][0];
        queue.add(0);
        int[] move = {-1, 0, 1, 0, -1};
        int moveN;
        int moveM;
        int moveSite;
        int targetSite = (n - 1) * m + (m - 1);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (targetSite == poll) break;
            int ni = poll / m;
            int mi = poll % m;
            for (int i = 0; i < 4; i++) {
                if ((moveN = ni + move[i]) < n && moveN >= 0
                        && (moveM = mi + move[i + 1]) < m && moveM >= 0
                        && blacks[moveN][moveM] > blacks[ni][mi] + grid[moveN][moveM]) {
                    //可以寻路
                    if (queue.contains(moveSite = moveN * m + moveM)) {
                        queue.remove(moveSite); //移除并重新放进去
                    }
                    blacks[moveN][moveM] = blacks[ni][mi] + grid[moveN][moveM];
                    queue.add(moveSite);
                }
            }
        }
        return blacks[n - 1][m - 1];
    }

    public static void main(String[] args) {
//        System.out.println(solution(5, 3, new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 1, 0}, {1, 0, 0}, {1, 0, 0}}) == 1);
//        System.out.println(solution(4, 4, new int[][]{{0, 0, 1, 0}, {1, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}}) == 0);
//        System.out.println(solution(3, 3, new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}) == 0);
        System.out.println(solution(8, 2, new int[][]{{1, 1}, {1, 0}, {0, 0}, {1, 1}, {0, 1}, {1, 0}, {1, 1}, {0, 1}}));
    }
}
