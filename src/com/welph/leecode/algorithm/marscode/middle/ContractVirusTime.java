package com.welph.leecode.algorithm.marscode.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在一个封闭的房间中，排列着 n 行 m 列的座位，每个座位间距为 1 米。
 * 房间中每个座位上都坐着一人，部分人戴了口罩，部分人未戴口罩。
 *
 * 已知房间中有一位已经感染病毒的人，病毒可以每秒向相邻座位传播 1 米。
 * 对于戴口罩的人，病毒需要两秒才能成功感染，或者需要在一秒内从两个相邻的感染者处同时被感染。
 *
 * 设计一个算法，计算病毒感染房间内所有人所需的最短时间。
 */
public class ContractVirusTime {

    public static int solution(int row_n, int column_m, int[][] seats, int[] patient) {
        if ((row_n == 0 && column_m == 0) || (patient[0] >= row_n || patient[1] >= column_m)) {
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        queue.offer(patient);
        seats[patient[0]][patient[1]] = 2;
        int time = 0;
        while (!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if (seats[poll[0]][poll[1]] < 0) {
                    seats[poll[0]][poll[1]] = 2;
                    queue.offer(poll);
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int r = poll[0] + steps[k][0];
                    int c = poll[1] + steps[k][1];
                    if (r >= 0 && r < row_n && c >= 0 && c < column_m) {
                        if (seats[r][c] == 2) {
                            continue;
                        } else if (seats[r][c] == 0) {
                            seats[r][c] = 2;
                        } else if (-seats[r][c] == time) {
                            seats[r][c] = 2;
                            continue;
                        } else if (seats[r][c] >= 0) {
                            seats[r][c] = -time;
                        } else {
                            continue;
                        }
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return time - 1;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        int[][] testSeats1 = {{0, 1, 1, 1}, {1, 0, 1, 0}, {1, 1, 1, 1}, {0, 0, 0, 1}};
        int[][] testSeats2 = {{0, 1, 1, 1}, {1, 0, 1, 0}, {1, 1, 1, 1}, {0, 0, 0, 1}};
        int[][] testSeats3 = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] testSeats4 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        int[][] testSeats5 = {{1}};

        System.out.println(solution(4, 4, testSeats1, new int[]{2, 2}) == 6);
        System.out.println(solution(4, 4, testSeats2, new int[]{2, 5}) == 0);
        System.out.println(solution(4, 4, testSeats3, new int[]{2, 2}) == 4);
        System.out.println(solution(4, 4, testSeats4, new int[]{2, 2}) == 6);
        System.out.println(solution(1, 1, testSeats5, new int[]{0, 0}) == 0);
        System.out.println(solution(25, 10, new int[][]{{0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 0, 0, 1, 1, 0, 0, 1}, {1, 1, 1, 0, 0, 0, 0, 0, 1, 0}, {0, 1, 0, 1, 0, 0, 1, 0, 1, 1},
                        {1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 0, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                        {1, 0, 1, 0, 1, 0, 1, 1, 0, 0}, {1, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {0, 1, 1, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 0, 1, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 0, 0, 0, 1}, {1, 0, 1, 0, 0, 1, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                        {0, 1, 0, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
                        {1, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 1, 0, 0}},
                new int[]{23, 7})); //35
    }
}
