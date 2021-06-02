package com.welph.leecode.algorithm.z7z8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一个逻辑学教授，有三个学生，而且三个学生都非常聪明！
 * 有一天教授给他们出了一个题：教授在每个人脑门上贴了一张纸条
 * 每个人的纸条上都写了一个正整数，且某两个数的和等于第三个数
 * 每个人可以看见另两个数，但看不见自己的
 * 教授问第一个学生：你能猜出自己的数吗？回答：不能。
 * >  问第二个，不能；第三个，不能。
 * 教授再问第二次：
 * >  第一个，不能；第二个，不能；第三个，我猜出来了，是144！
 * 教授很满意的笑了，请问你能猜出另外两个人的数吗？
 */
public class GuessNumber {

    public static void main(String[] args) {
        List<int[]> guess = solve(2, 3, 144);
        for (int[] ints : guess) {
            System.out.println(Arrays.toString(ints));
        }

        guess = solve(2, 3, 198);
        for (int[] ints : guess) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * <a>https://mp.weixin.qq.com/s/Re6XH_05xrepESAfNWqBPQ</>
     * 每一轮, 都由前几轮的结果得出. 最终由前几轮结果统一成一个结果,就是爆出来的答案(最大的)
     * 推理根据 1+1=2 每一轮的结果呈菲波那切数列递增
     *
     * @param round  第几轮次
     * @param person 第几个人
     * @param target 猜出的数字
     */
    private static List<int[]> solve(int round, int person, int target) {
        List<Turn> turns = new ArrayList<>();
        init(turns);
        int last = 3; //最近一次开始的偏移量
        int preLast = 1;  //只取上两次的数据结果的偏移量
        int n = (round - 2) * 3 + person, total; //从init后开始的轮次
        for (int i = 0; i < n; i++) {
            total = turns.size();
            for (int j = preLast; j < total; j++) {
                Turn turn = turns.get(j);
                if (i % 3 == 0) {
                    turns.add(new Turn(turn.y + turn.z, turn.y, turn.z, 4 + i));
                } else if (i % 3 == 1) {
                    turns.add(new Turn(turn.x, turn.x + turn.z, turn.z, 4 + i));
                } else {
                    turns.add(new Turn(turn.x, turn.y, turn.x + turn.y, 4 + i));
                }
            }
            preLast = last;
            last = total;
        }
        //此时说明跑完了, 从最后一次的结果中找出匹配的
        List<int[]> res = new ArrayList<>();
        for (int i = last; i < turns.size(); ++i) {
            int temp = 0;
            switch (person) {
                case 1:
                    temp = turns.get(i).x;
                    break;
                case 2:
                    temp = turns.get(i).y;
                    break;
                case 3:
                    temp = turns.get(i).z;
                    break;
            }
            if (target % temp == 0) { //说明符合结果
                int s = target / temp;
                res.add(new int[]{turns.get(i).x * s, turns.get(i).y * s, turns.get(i).z * s});
            }
        }
        return res;
    }

    private static void init(List<Turn> turns) {
        turns.add(new Turn(2, 1, 1, 1));
        turns.add(new Turn(1, 2, 1, 2));
        turns.add(new Turn(2, 3, 1, 2));
        turns.add(new Turn(1, 1, 2, 3));
        turns.add(new Turn(2, 1, 3, 3));
        turns.add(new Turn(1, 2, 3, 3));
        turns.add(new Turn(2, 3, 5, 3));
    }

    static class Turn {
        int x; //第一个人的数字
        int y; //第二个人的数字
        int z; //第三个人的数字
        int level; //当前第几次[猜]

        public Turn(int x, int y, int z, int level) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.level = level;
        }
    }
}
