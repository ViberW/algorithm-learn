package com.welph.leecode.algorithm.marscode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 小U掌握了国际象棋中"象"和"马"的跳跃能力,在一个无限大的平面直角坐标系中,
 * 她每一步可以模仿象和马的跳跃方式移动。在每海尔询问中,小U需要计算从初始坐标(21,y1)到(12,y2)
 * 所需的最少步数。
 * 1. 象的跳跃:可以移动到(r+k,y+k)或(r+k,y-k),其中人是任意整数。
 * 2. 马的跳跃:可以移动到(x+ay+6),其中|a+b|=3且 1<|a,旧<2.
 * 你需要在每次询问中计算从起点到终点所需的最少步数
 */
public class ToSourceByChess {
    public static int solution(int x1, int y1, int x2, int y2) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.add(new int[]{x1, y1});
        set.add(x1 + "," + y1);
        int[][] operation = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2},
                {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
        int endAdd = y2 + x2;
        int endSubtract = y2 - x2;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = 0;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int currentAdd = poll[1] + poll[0];
                int currentSubtract = poll[1] - poll[0];
                //尝试走象,这里是斜率相同的情况下, y=x+k y=-x+k 的k相同. 说明一条对角线上
                if (currentAdd == endAdd || endSubtract == currentSubtract) {
                    return step + 1;
                }
                //尝试走日
                for (int k = 0; k < 8; k++) {
                    int newX = operation[k][0] + poll[0];
                    int newY = operation[k][1] + poll[1];
                    if (x2 == newX && y2 == newY) {
                        return step + 1;
                    }
                    String key = newX + "," + newY;
                    if (!set.contains(key)) {
                        queue.add(new int[]{newX, newY});
                        set.add(key);
                    }
                }
                //这一步放后面,防止马能一步到位
                //尝试走象, 两个点的对象线互相垂直, 且垂直点是个正整数
                if ((endAdd - currentSubtract) % 2 == 0 || (currentAdd - endSubtract) % 2 == 0) {
                    min = 2;
                }
            }
            if (min != 0) {
                return step + min;
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(0, 0, 1, 1) == 1);
        System.out.println(solution(0, 0, 2, 1) == 1);
        System.out.println(solution(0, 0, 3, 3) == 1);
        System.out.println(solution(-3, -2, 2, 1) == 2);
        System.out.println(solution(6, 3, 10, 10) == 2);
    }
}
