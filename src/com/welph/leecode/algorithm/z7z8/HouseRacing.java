package com.welph.leecode.algorithm.z7z8;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 田忌赛马
 * -------------------
 * 田忌的同一级别的马都比齐王的同级别马低
 * @since 2021/9/9 14:43
 */
public class HouseRacing {

    public static void main(String[] args) {
        int[] tj = {12, 24, 8, 32};
        int[] qw = {13, 25, 32, 11};
        System.out.println(Arrays.toString(advance(tj, qw)));
    }

    public static int[] advance(int[] tj, int[] qw) {
        int length = tj.length;
        int[] ret = new int[length];
        //对齐王的降序
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < length; i++) {
            queue.add(new int[]{i, qw[i]});
        }
        //对田忌的升序
        Arrays.sort(tj);

        int l = 0, r = length - 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int i = poll[0];
            int maxVal = poll[1];
            if (maxVal < tj[r]) {
                //说明田忌的当前最快马能胜过齐王
                ret[i] = tj[r];
                r--;
            } else {
                //田忌最快马也无法胜过齐王,则使用最慢的马
                ret[i] = tj[l];
                l++;
            }
        }
        return ret;
    }
}
