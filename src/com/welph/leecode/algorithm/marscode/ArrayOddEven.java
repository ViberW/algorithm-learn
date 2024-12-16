package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 小Q有一个长度为n的数组,他可以进行k次操作,每次操作可以对数组中的任意一个数字执行以下操作:
 * 1.如果选择的数字x是奇数,则将x乘以2,即x=x×2。
 * 2.如果选择的数字x是偶数,则将x乘以2再加1,即x=x×2+1
 *
 * 小Q想知道,经过上次操作后,数组的元素之和最小可以是多少。
 */
public class ArrayOddEven {

    public static int solution(int n, int k, int[] a) {
        if (n == 0) {
            return 0;
        }
        //注意这里不是一次性选择, 而是每种结果后的再次选择
        /*
         * 1, 2, 3, 5, 2
         * 2  2  3  5  2
         * 2  2  3  5  5
         * 2  5  3  5  5  => 20
         */
        //每次选择最小值去处理
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(a[i]);
        }
        while (k-- > 0) {
            Integer poll = queue.poll();
            queue.add((poll & 1) == 0 ? (2 * poll + 1) : (2 * poll));
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 3, new int[]{1, 2, 3, 5, 2}) == 20);
        System.out.println(solution(3, 2, new int[]{7, 8, 9}) == 40);
        System.out.println(solution(4, 4, new int[]{2, 3, 5, 7}) == 33);
    }
}
