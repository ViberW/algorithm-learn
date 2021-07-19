package com.welph.leecode.algorithm.thinking;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [双向搜索] :
 * @since 2021/7/19 16:45
 */
public class TwoWaySearch_26 {

    /**
     * 场景: 已知起点和终点, 确定起点到终点的步数;
     */
    public static void main(String[] args) {

    }

    /**
     * 方法一: 双向BFS, 最终相遇后确定步数
     */
    public int twoBFS() {
        Deque<Pair<Integer, Integer>> q1 = new LinkedList<>();
        Deque<Pair<Integer, Integer>> q2 = new LinkedList<>();
        //终点x, 终点y, 结果值 ==>假定起点为(1,1)
        int ans = 0;
        int x = 45;
        int y = 45;
        int r = 44; //终点x
        int c = 44; //终点y
        int[][] dis = new int[x][y];
        int[][] vst = new int[x][y];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        char[][] m = new char[x][y];
        /////////
        boolean flag;
        q1.push(new Pair<>(1, 1));
        dis[1][1] = 1;
        vst[1][1] = 1; //从前搜索

        q1.push(new Pair<>(r, c));
        dis[r][c] = 1;
        vst[r][c] = 2; //从后搜索

        LABEL:
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int x0, y0;
            if (q1.size() > q2.size()) { //每次扩展搜索树小的队列 flag=1扩展前搜的队列，flag=0扩展后搜的队列
                Pair<Integer, Integer> poll = q2.poll();
                x0 = poll.getKey();
                y0 = poll.getValue();
                flag = false;
            } else {
                Pair<Integer, Integer> poll = q1.poll();
                x0 = poll.getKey();
                y0 = poll.getValue();
                flag = true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x0 + dx[i];
                int ny = y0 + dy[i];
                if (nx >= 1 && nx <= r && ny >= 1 && ny <= c && m[nx][ny] == 0) {
                    if (dis[nx][ny] != 0) {
                        dis[nx][ny] = dis[x0][y0] + 1;
                        vst[nx][ny] = vst[x0][y0];
                        if (flag) {
                            q1.push(new Pair<>(nx, ny));
                        } else {
                            q2.push(new Pair<>(nx, ny));
                        }
                    } else {
                        if (vst[x0][y0] + vst[nx][ny] == 3) { //相遇
                            ans = dis[nx][ny] + dis[x0][y0];
                            break LABEL;
                        }
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 单向的BFS 搜索
     */
    public int singleBFS() {
        int INF = 100000;
        int MAX = 101;
        Pair<Integer, Integer> p;
        char[][] map = new char[MAX][MAX]; //记录两个之前时是否存在点
        //初始化map todo ...
        int[][] d = new int[MAX][MAX];//表示起点到各个位置的最短距离
        int sx = 100, sy = 100, gx = 100, gy = 100;//表示起点和终点坐标
        int n = 100; //map的x
        int m = 100; //map的y
        //向 [前后左右] 移动
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Deque<Pair<Integer, Integer>> que = new LinkedList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                d[i][j] = INF;

        que.push(new Pair<>(sx, sy));
        d[sx][sy] = 0;
        while (!que.isEmpty()) {
            p = que.pop();
            if (p.getKey() == gx && p.getValue() == gy)
                break;
            for (int i = 0; i < 4; i++) {
                int nx = p.getKey() + dx[i];
                int ny = p.getValue() + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && d[nx][ny] != 100000 && map[nx][ny] != 0) {
                    que.push(new Pair<>(nx, ny));
                    d[nx][ny] = d[p.getKey()][p.getValue()] + 1;
                }
            }
        }
        return d[gx][gy];
    }
}
